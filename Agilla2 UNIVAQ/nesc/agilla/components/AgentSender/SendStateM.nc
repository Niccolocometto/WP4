// $Id: SendStateM.nc,v 1.12 2006/03/27 00:38:21 chien-liang Exp $

/* Agilla - A middleware for wireless sensor networks.
 * Copyright (C) 2006, Washington University in Saint Louis 
 * By Chien-Liang Fok.
 * 
 * Washington University states that Agilla is free software; 
 * you can redistribute it and/or modify it under the terms of 
 * the current version of the GNU Lesser General Public License 
 * as published by the Free Software Foundation.
 * 
 * Agilla is distributed in the hope that it will be useful, but 
 * THERE ARE NO WARRANTIES, WHETHER ORAL OR WRITTEN, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO, IMPLIED WARRANTIES OF 
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR USE.
 *
 * YOU UNDERSTAND THAT AGILLA IS PROVIDED "AS IS" FOR WHICH NO 
 * WARRANTIES AS TO CAPABILITIES OR ACCURACY ARE MADE. THERE ARE NO 
 * WARRANTIES AND NO REPRESENTATION THAT AGILLA IS FREE OF 
 * INFRINGEMENT OF THIRD PARTY PATENT, COPYRIGHT, OR OTHER 
 * PROPRIETARY RIGHTS.	THERE ARE NO WARRANTIES THAT SOFTWARE IS 
 * FREE FROM "BUGS", "VIRUSES", "TROJAN HORSES", "TRAP DOORS", "WORMS", 
 * OR OTHER HARMFUL CODE.	
 *
 * YOU ASSUME THE ENTIRE RISK AS TO THE PERFORMANCE OF SOFTWARE AND/OR 
 * ASSOCIATED MATERIALS, AND TO THE PERFORMANCE AND VALIDITY OF 
 * INFORMATION GENERATED USING SOFTWARE. By using Agilla you agree to 
 * indemnify, defend, and hold harmless WU, its employees, officers and 
 * agents from any and all claims, costs, or liabilities, including 
 * attorneys fees and court costs at both the trial and appellate levels 
 * for any loss, damage, or injury caused by your actions or actions of 
 * your officers, servants, agents or third parties acting on behalf or 
 * under authorization from you, as a result of using Agilla. 
 *
 * See the GNU Lesser General Public License for more details, which can 
 * be found here: http://www.gnu.org/copyleft/lesser.html
 */

#include "AgillaOpcodes.h"
#include "Timer.h"

/**
 * Sends the state of an agent.
 *
 * @author Chien-Liang Fok
 */
module SendStateM {
	provides {
	interface StdControl;
	interface Init;
	interface PartialAgentSenderI as SendState;
	}
	uses {
	interface MessageBufferI;
	interface HeapMgrI;
	interface RxnMgrI;
	
	interface AMSend as Send_State;
	interface Receive as Rcv_Ack;
	interface AMSend as SerialSend_State;
	interface Receive as SerialRcv_Ack;

	//interface Timer as Ack_Timer;	 
	interface Timer<TMilli> as Ack_Timer;
	interface ErrorMgrI as Error;
	
	interface Leds;
	interface Packet;
	}
}
implementation {
	enum {
	IDLE = 0,
	SENDING,
	WAITING,
	};
	
	uint8_t _state, _numTimeouts;	
	
	AgillaAgentContext* _context;
	AgillaAgentID _id;
	uint8_t _op;
	uint16_t _dest, _final_dest;	// the final destination
	
	task void doSend();
	
	command error_t Init.init() {
	_state = IDLE; 

	return SUCCESS;
	}

	command error_t StdControl.start() {
	return SUCCESS;
	}

	command error_t StdControl.stop() {
	return SUCCESS;
	}	

	inline void signalDone(error_t success) {
	_state = IDLE; 
	signal SendState.sendDone(_context, success);				
	}	

	command error_t SendState.send(AgillaAgentContext* context, AgillaAgentID id,
	uint8_t op, uint16_t dest, uint16_t final_dest)
	{		
	if (_state == IDLE) 
	{
		if (post doSend() == SUCCESS)
		{		
		_state = SENDING;
		_numTimeouts = 0;

		_context = context;
		_id = id;
		_op = op;
		_dest = dest;	
		_final_dest = final_dest;
		return SUCCESS;
		} else 
		{
		dbg("DBG_USR1", "SendStateM: ERROR: could not post task doSend().\n");
		}
	}
	return FAIL;
	}

	task void doSend() 
	{
	message_t* msg = call MessageBufferI.getMsg();
	if (msg != NULL) 
	{
		//struct AgillaStateMsg *sMsg = (struct AgillaStateMsg *)msg->data;
		AgillaStateMsg *sMsg = (AgillaStateMsg *)(call Packet.getPayload(msg, sizeof(AgillaStateMsg)));
	
		sMsg->replyAddr = TOS_NODE_ID;
		sMsg->dest = _final_dest;	// note that this may be multiple hops away
		sMsg->id = _id;
		sMsg->op = _op;
		sMsg->codeSize = _context->codeSize;

		if (_op == IOPwmove || _op == IOPwclone) {		
		sMsg->sp = 0;	
		sMsg->pc = 0;	
		if (_op == IOPwmove)
			sMsg->condition = 0;	
		else
			sMsg->condition = 2;	// distinguishes which agent is the clone
		sMsg->numHpMsgs = 0;
		sMsg->numRxnMsgs = 0;	 
		sMsg->desc.value = 0;
		} else {
		sMsg->sp = _context->opStack.sp;	
		sMsg->pc = _context->pc;	
		sMsg->condition = _context->condition;	
		sMsg->numHpMsgs = call HeapMgrI.numHeapMsgs(_context);
		sMsg->numRxnMsgs = call RxnMgrI.numRxns(&_context->id);		
		sMsg->desc = _context->desc;
		}	
	
		#if DEBUG_AGENT_SENDER
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->dest = %i\n", sMsg->dest);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->replyAddr = %i\n", sMsg->replyAddr);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->id.id = %i\n", sMsg->id.id);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->op = %i\n", sMsg->op);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->codeSize = %i\n", sMsg->codeSize);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->pc = %i\n", sMsg->pc);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->sp = %i\n", sMsg->sp);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->condition = %i\n", sMsg->condition);
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->numHpMsgs = %i\n", sMsg->numHpMsgs);		
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->numRxnMsgs = %i\n", sMsg->numRxnMsgs);	
		dbg("DBG_USR1", "SendStateM: task doSend(): sMsg->desc.string = %i\n", sMsg->desc.string);	
		#endif
	
		if (_dest == AM_UART_ADDR){
		if (call SerialSend_State.send(_dest, msg, sizeof(AgillaStateMsg)) == SUCCESS)
		{	
			_state = WAITING;
			#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: task doSend(): Sent state\n");
			#endif
		} else 
		{	
			// The send was not successful, so free the message buffer.
			call MessageBufferI.freeMsg(msg);		 
			dbg("DBG_USR1", "SendStateM: doSend(): ERROR: Failed to send state, ACK timer should timeout.\n");		
		}		
		} else{
		if (call Send_State.send(_dest, msg, sizeof(AgillaStateMsg)) == SUCCESS)
		{	
			_state = WAITING;
			#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: task doSend(): Sent state\n");
			#endif
		} else 
		{	
			// The send was not successful, so free the message buffer.
			call MessageBufferI.freeMsg(msg);		 
			dbg("DBG_USR1", "SendStateM: doSend(): ERROR: Failed to send state, ACK timer should timeout.\n");		
		}
		} 
	}
	// If the MessageBuffer did not have any free messages, start the timer 
	// anyway.	When the timer fires, SendStateM will try again.
	call Ack_Timer.startOneShot(AGILLA_SNDR_RXMIT_TIMER);

	#if DEBUG_AGENT_SENDER
		dbg("DBG_USR1", "SendStateM: task doSend(): Timer started.\n");
	#endif	
	}
	
	/**
	 * This is executed whenever an ACK message times out.
	 */
	event void Ack_Timer.fired()
	{	
	if (_state == WAITING) 
	{
		_numTimeouts++;	

		#if DEBUG_AGENT_SENDER
		dbg("DBG_USR1", "SendStateM: Ack_Timer.fired(): TIME OUT #%i!\n", _numTimeouts);
		#endif	

		if (_numTimeouts < AGILLA_SNDR_MAX_RETRANSMITS)
		post doSend();
		else {
		#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: Ack_Timer.fired(): max timeouts reached.\n");
		#endif	
		signalDone(FAIL);	
		}
	}
	 // return SUCCESS;
	}	
	
	/**
	 * This is signalled when an ACK message is received.
	 */
	event message_t* Rcv_Ack.receive(message_t* m, void* payload, uint8_t len)
	{
	AgillaAckStateMsg* aMsg = (AgillaAckStateMsg*)payload;
	if (_state == WAITING)
	{
		if (aMsg->id.id == _id.id) 
		{
		call Ack_Timer.stop();
		if (aMsg->accept)	//OK PERCH� accept VALE O AGILLA_ACCEPT (=1) O AGILLA_AREJECT (=0)
			signalDone(SUCCESS);
		else 
		{	 
			#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: Rcv_Ack.receive: The ACK was rejected.\n");
			#endif				
			signalDone(REJECT);
		}
		} else 
		{
		#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: Rcv_Ack.receive: The ACK was not for this agent.\n");
		#endif				
		}
	}
	return m;
	}
	
	event message_t* SerialRcv_Ack.receive(message_t* m, void* payload, uint8_t len)
	{
	AgillaAckStateMsg* aMsg = (AgillaAckStateMsg*)payload;
	if (_state == WAITING)
	{
		if (aMsg->id.id == _id.id) 
		{
		call Ack_Timer.stop();
		if (aMsg->accept)	//OK PERCH� accept VALE O AGILLA_ACCEPT (=1) O AGILLA_AREJECT (=0)
			signalDone(SUCCESS);
		else 
		{	 
			#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: Rcv_Ack.receive: The ACK was rejected.\n");
			#endif				
			signalDone(REJECT);
		}
		} else 
		{
		#if DEBUG_AGENT_SENDER
			dbg("DBG_USR1", "SendStateM: Rcv_Ack.receive: The ACK was not for this agent.\n");
		#endif				
		}
	}
	return m;
	}
	
	event void Send_State.sendDone(message_t* m, error_t success)
	{ 
	// Do not do anything here because even if the message send failed, 
	// the Ack timer will timeout.	
	call MessageBufferI.freeMsg(m);	
	//return SUCCESS; 
	}
	
	event void SerialSend_State.sendDone(message_t* m, error_t success)
	{ 
	// Do not do anything here because even if the message send failed, 
	// the Ack timer will timeout.	
	call MessageBufferI.freeMsg(m);	
	//return SUCCESS; 
	}
}
