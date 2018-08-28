// $Id: ReceiveOpStackM.nc,v 1.2 2006/01/06 01:16:30 chien-liang Exp $

/* Agilla - A middleware for wireless sensor networks.
 * Copyright (C) 2004, Washington University in Saint Louis 
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

#include "Agilla.h"
#include "MigrationMsgs.h"

/**
 * Receives the operand stack of an incomming agent.
 *
 * @author Chien-Liang Fok
 */
module ReceiveOpStackM {
	uses {
	interface ReceiverCoordinatorI as CoordinatorI;
	interface OpStackI;
	interface Receive as Rcv_OpStack;
	interface AMSend as Send_OpStack_Ack;
	interface Receive as SerialRcv_OpStack;
	interface AMSend as SerialSend_OpStack_Ack;
	interface MessageBufferI;
	interface Packet;
	}	
}
implementation {

	void sendOpStackAck(uint16_t addr, AgillaAgentID *id, uint8_t acpt, uint8_t startAddr) 
	{
	message_t* msg = call MessageBufferI.getMsg();
	if (msg != NULL) 
	{
		//struct AgillaAckOpStackMsg *aMsg = (struct AgillaAckOpStackMsg *)(call Packet.getPayload(msg, sizeof(AgillaAckOpStackMsg))); 
		AgillaAckOpStackMsg *aMsg = (AgillaAckOpStackMsg *)(call Packet.getPayload(msg, sizeof(AgillaAckOpStackMsg)));
		aMsg->id = *id;
		aMsg->accept = acpt;
		aMsg->startAddr = startAddr;
		if(addr == AM_UART_ADDR){
		if (call SerialSend_OpStack_Ack.send(addr, msg, sizeof(AgillaAckOpStackMsg)) != SUCCESS)
			call MessageBufferI.freeMsg(msg);
		}
		else
		{
		 if (call Send_OpStack_Ack.send(addr, msg, sizeof(AgillaAckOpStackMsg)) != SUCCESS)
			 call MessageBufferI.freeMsg(msg);
		}
	}
	}

	event message_t* Rcv_OpStack.receive(message_t* m, void* payload, uint8_t len) {
	AgillaOpStackMsg* osMsg = (AgillaOpStackMsg*)payload;
	if (call CoordinatorI.isArriving(&osMsg->id) == SUCCESS && 
		call CoordinatorI.stopTimer(&osMsg->id) == SUCCESS)
	{
		AgillaAgentInfo* inBuf = call CoordinatorI.getBuffer(&osMsg->id);
		if (inBuf != NULL) {	
		if (call OpStackI.saveMsg(inBuf->context, osMsg) == SUCCESS) {
			if (inBuf->context->opStack.byte[inBuf->context->opStack.sp-1] != AGILLA_TYPE_INVALID) {
			inBuf->integrity |= AGILLA_RECEIVED_OPSTACK;	
			#if DEBUG_AGENT_RECEIVER
				dbg("DBG_USR1", "ReceiveOpStackM: Rcv_OpStack.receive: Received opstack for agent %i\n", osMsg->id.id);					
			#endif			
			}
		} else {
			#if DEBUG_AGENT_RECEIVER
			dbg("DBG_USR1", "ReceiveOpStackM: Rcv_OpStack.receive(): Duplicate OpStack message.\n");
			#endif	
		}
		
		sendOpStackAck(inBuf->reply, &osMsg->id, AGILLA_ACCEPT, osMsg->startAddr);				
		
		if (inBuf->integrity == AGILLA_AGENT_READY) {
			#if DEBUG_AGENT_RECEIVER
			dbg("DBG_USR1", "ReceiveOpStackM: Rcv_OpStack.receive: Received agent %i, starting FIN timer.\n", osMsg->id.id);
			#endif	 
			call CoordinatorI.startFinTimer(&osMsg->id);
		} else
			call CoordinatorI.startAbortTimer(&osMsg->id);	// have not received entire agent			 
		return m;
		} else {
		#if DEBUG_AGENT_RECEIVER
		dbg("DBG_USR1", "ReceiveOpStackM: Rcv_Heap.receive: ERROR: Could not get incomming buffer for agent %i.\n", osMsg->id.id);
		#endif			
		}
	}	
	return m;	
	}	// Rcv_OpStack.receive

	//RECEIVE SERIAL
	event message_t* SerialRcv_OpStack.receive(message_t* m, void* payload, uint8_t len) {
	AgillaOpStackMsg* osMsg = (AgillaOpStackMsg*)payload;
	if (call CoordinatorI.isArriving(&osMsg->id) == SUCCESS && 
		call CoordinatorI.stopTimer(&osMsg->id) == SUCCESS)
	{
		AgillaAgentInfo* inBuf = call CoordinatorI.getBuffer(&osMsg->id);
		if (inBuf != NULL) {	
		if (call OpStackI.saveMsg(inBuf->context, osMsg) == SUCCESS) {
			if (inBuf->context->opStack.byte[inBuf->context->opStack.sp-1] != AGILLA_TYPE_INVALID) {
			inBuf->integrity |= AGILLA_RECEIVED_OPSTACK;	
			#if DEBUG_AGENT_RECEIVER
				dbg("DBG_USR1", "ReceiveOpStackM: Rcv_OpStack.receive: Received opstack for agent %i\n", osMsg->id.id);					
			#endif			
			}
		} else {
			#if DEBUG_AGENT_RECEIVER
			dbg("DBG_USR1", "ReceiveOpStackM: Rcv_OpStack.receive(): Duplicate OpStack message.\n");
			#endif	
		}
		
		sendOpStackAck(inBuf->reply, &osMsg->id, AGILLA_ACCEPT, osMsg->startAddr);				
		
		if (inBuf->integrity == AGILLA_AGENT_READY) {
			#if DEBUG_AGENT_RECEIVER
			dbg("DBG_USR1", "ReceiveOpStackM: Rcv_OpStack.receive: Received agent %i, starting FIN timer.\n", osMsg->id.id);
			#endif	 
			call CoordinatorI.startFinTimer(&osMsg->id);
		} else
			call CoordinatorI.startAbortTimer(&osMsg->id);	// have not received entire agent			 
		return m;
		} else {
		#if DEBUG_AGENT_RECEIVER
		dbg("DBG_USR1", "ReceiveOpStackM: Rcv_Heap.receive: ERROR: Could not get incomming buffer for agent %i.\n", osMsg->id.id);
		#endif			
		}
	}	
	return m;	
	}	// Rcv_OpStack.receive
	
	event void Send_OpStack_Ack.sendDone(message_t* m, error_t success) {
	call MessageBufferI.freeMsg(m);
	//return SUCCESS; 
	}

	event void SerialSend_OpStack_Ack.sendDone(message_t* m, error_t success) {
	call MessageBufferI.freeMsg(m);
	//return SUCCESS; 
	}
}
