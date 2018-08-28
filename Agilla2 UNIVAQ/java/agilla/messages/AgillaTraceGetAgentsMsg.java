/**
 * This class is automatically generated by mig. DO NOT EDIT THIS FILE.
 * This class implements a Java interface to the 'AgillaTraceGetAgentsMsg'
 * message type.
 */

package agilla.messages;

public class AgillaTraceGetAgentsMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 28;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 148;

    /** Create a new AgillaTraceGetAgentsMsg of size 28. */
    public AgillaTraceGetAgentsMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new AgillaTraceGetAgentsMsg of the given data_length. */
    public AgillaTraceGetAgentsMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaTraceGetAgentsMsg with the given data_length
     * and base offset.
     */
    public AgillaTraceGetAgentsMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaTraceGetAgentsMsg using the given byte array
     * as backing store.
     */
    public AgillaTraceGetAgentsMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaTraceGetAgentsMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public AgillaTraceGetAgentsMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaTraceGetAgentsMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public AgillaTraceGetAgentsMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaTraceGetAgentsMsg embedded in the given message
     * at the given base offset.
     */
    public AgillaTraceGetAgentsMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaTraceGetAgentsMsg embedded in the given message
     * at the given base offset and length.
     */
    public AgillaTraceGetAgentsMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <AgillaTraceGetAgentsMsg> \n";
      try {
        s += "  [timestamp.high32=0x"+Long.toHexString(get_timestamp_high32())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [timestamp.low32=0x"+Long.toHexString(get_timestamp_low32())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [agentID=0x"+Long.toHexString(get_agentID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [nodeID=0x"+Long.toHexString(get_nodeID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [qid=0x"+Long.toHexString(get_qid())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [num_agents=0x"+Long.toHexString(get_num_agents())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [agent_id.id=";
        for (int i = 0; i < 2; i++) {
          s += "0x"+Long.toHexString(getElement_agent_id_id(i) & 0xffff)+" ";
        }
        s += "]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [loc.x=";
        for (int i = 0; i < 2; i++) {
          s += "0x"+Long.toHexString(getElement_loc_x(i) & 0xffff)+" ";
        }
        s += "]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [loc.y=";
        for (int i = 0; i < 2; i++) {
          s += "0x"+Long.toHexString(getElement_loc_y(i) & 0xffff)+" ";
        }
        s += "]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: timestamp.high32
    //   Field type: long
    //   Offset (bits): 0
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'timestamp.high32' is signed (false).
     */
    public static boolean isSigned_timestamp_high32() {
        return false;
    }

    /**
     * Return whether the field 'timestamp.high32' is an array (false).
     */
    public static boolean isArray_timestamp_high32() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'timestamp.high32'
     */
    public static int offset_timestamp_high32() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'timestamp.high32'
     */
    public static int offsetBits_timestamp_high32() {
        return 0;
    }

    /**
     * Return the value (as a long) of the field 'timestamp.high32'
     */
    public long get_timestamp_high32() {
        return (long)getUIntBEElement(offsetBits_timestamp_high32(), 32);
    }

    /**
     * Set the value of the field 'timestamp.high32'
     */
    public void set_timestamp_high32(long value) {
        setUIntBEElement(offsetBits_timestamp_high32(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'timestamp.high32'
     */
    public static int size_timestamp_high32() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'timestamp.high32'
     */
    public static int sizeBits_timestamp_high32() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: timestamp.low32
    //   Field type: long
    //   Offset (bits): 32
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'timestamp.low32' is signed (false).
     */
    public static boolean isSigned_timestamp_low32() {
        return false;
    }

    /**
     * Return whether the field 'timestamp.low32' is an array (false).
     */
    public static boolean isArray_timestamp_low32() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'timestamp.low32'
     */
    public static int offset_timestamp_low32() {
        return (32 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'timestamp.low32'
     */
    public static int offsetBits_timestamp_low32() {
        return 32;
    }

    /**
     * Return the value (as a long) of the field 'timestamp.low32'
     */
    public long get_timestamp_low32() {
        return (long)getUIntBEElement(offsetBits_timestamp_low32(), 32);
    }

    /**
     * Set the value of the field 'timestamp.low32'
     */
    public void set_timestamp_low32(long value) {
        setUIntBEElement(offsetBits_timestamp_low32(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'timestamp.low32'
     */
    public static int size_timestamp_low32() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'timestamp.low32'
     */
    public static int sizeBits_timestamp_low32() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: agentID
    //   Field type: int
    //   Offset (bits): 64
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'agentID' is signed (false).
     */
    public static boolean isSigned_agentID() {
        return false;
    }

    /**
     * Return whether the field 'agentID' is an array (false).
     */
    public static boolean isArray_agentID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'agentID'
     */
    public static int offset_agentID() {
        return (64 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'agentID'
     */
    public static int offsetBits_agentID() {
        return 64;
    }

    /**
     * Return the value (as a int) of the field 'agentID'
     */
    public int get_agentID() {
        return (int)getUIntElement(offsetBits_agentID(), 16);
    }

    /**
     * Set the value of the field 'agentID'
     */
    public void set_agentID(int value) {
        setUIntElement(offsetBits_agentID(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'agentID'
     */
    public static int size_agentID() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'agentID'
     */
    public static int sizeBits_agentID() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: nodeID
    //   Field type: int
    //   Offset (bits): 80
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'nodeID' is signed (false).
     */
    public static boolean isSigned_nodeID() {
        return false;
    }

    /**
     * Return whether the field 'nodeID' is an array (false).
     */
    public static boolean isArray_nodeID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'nodeID'
     */
    public static int offset_nodeID() {
        return (80 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'nodeID'
     */
    public static int offsetBits_nodeID() {
        return 80;
    }

    /**
     * Return the value (as a int) of the field 'nodeID'
     */
    public int get_nodeID() {
        return (int)getUIntElement(offsetBits_nodeID(), 16);
    }

    /**
     * Set the value of the field 'nodeID'
     */
    public void set_nodeID(int value) {
        setUIntElement(offsetBits_nodeID(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'nodeID'
     */
    public static int size_nodeID() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'nodeID'
     */
    public static int sizeBits_nodeID() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: qid
    //   Field type: int
    //   Offset (bits): 96
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'qid' is signed (false).
     */
    public static boolean isSigned_qid() {
        return false;
    }

    /**
     * Return whether the field 'qid' is an array (false).
     */
    public static boolean isArray_qid() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'qid'
     */
    public static int offset_qid() {
        return (96 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'qid'
     */
    public static int offsetBits_qid() {
        return 96;
    }

    /**
     * Return the value (as a int) of the field 'qid'
     */
    public int get_qid() {
        return (int)getUIntElement(offsetBits_qid(), 16);
    }

    /**
     * Set the value of the field 'qid'
     */
    public void set_qid(int value) {
        setUIntElement(offsetBits_qid(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'qid'
     */
    public static int size_qid() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'qid'
     */
    public static int sizeBits_qid() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: num_agents
    //   Field type: int
    //   Offset (bits): 112
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'num_agents' is signed (false).
     */
    public static boolean isSigned_num_agents() {
        return false;
    }

    /**
     * Return whether the field 'num_agents' is an array (false).
     */
    public static boolean isArray_num_agents() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'num_agents'
     */
    public static int offset_num_agents() {
        return (112 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'num_agents'
     */
    public static int offsetBits_num_agents() {
        return 112;
    }

    /**
     * Return the value (as a int) of the field 'num_agents'
     */
    public int get_num_agents() {
        return (int)getUIntElement(offsetBits_num_agents(), 16);
    }

    /**
     * Set the value of the field 'num_agents'
     */
    public void set_num_agents(int value) {
        setUIntElement(offsetBits_num_agents(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'num_agents'
     */
    public static int size_num_agents() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'num_agents'
     */
    public static int sizeBits_num_agents() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: agent_id.id
    //   Field type: int[]
    //   Offset (bits): 0
    //   Size of each element (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'agent_id.id' is signed (false).
     */
    public static boolean isSigned_agent_id_id() {
        return false;
    }

    /**
     * Return whether the field 'agent_id.id' is an array (true).
     */
    public static boolean isArray_agent_id_id() {
        return true;
    }

    /**
     * Return the offset (in bytes) of the field 'agent_id.id'
     */
    public static int offset_agent_id_id(int index1) {
        int offset = 0;
        if (index1 < 0 || index1 >= 2) throw new ArrayIndexOutOfBoundsException();
        offset += 128 + index1 * 16;
        return (offset / 8);
    }

    /**
     * Return the offset (in bits) of the field 'agent_id.id'
     */
    public static int offsetBits_agent_id_id(int index1) {
        int offset = 0;
        if (index1 < 0 || index1 >= 2) throw new ArrayIndexOutOfBoundsException();
        offset += 128 + index1 * 16;
        return offset;
    }

    /**
     * Return the entire array 'agent_id.id' as a int[]
     */
    public int[] get_agent_id_id() {
        int[] tmp = new int[2];
        for (int index0 = 0; index0 < numElements_agent_id_id(0); index0++) {
            tmp[index0] = getElement_agent_id_id(index0);
        }
        return tmp;
    }

    /**
     * Set the contents of the array 'agent_id.id' from the given int[]
     */
    public void set_agent_id_id(int[] value) {
        for (int index0 = 0; index0 < value.length; index0++) {
            setElement_agent_id_id(index0, value[index0]);
        }
    }

    /**
     * Return an element (as a int) of the array 'agent_id.id'
     */
    public int getElement_agent_id_id(int index1) {
        return (int)getUIntBEElement(offsetBits_agent_id_id(index1), 16);
    }

    /**
     * Set an element of the array 'agent_id.id'
     */
    public void setElement_agent_id_id(int index1, int value) {
        setUIntBEElement(offsetBits_agent_id_id(index1), 16, value);
    }

    /**
     * Return the total size, in bytes, of the array 'agent_id.id'
     */
    public static int totalSize_agent_id_id() {
        return (32 / 8);
    }

    /**
     * Return the total size, in bits, of the array 'agent_id.id'
     */
    public static int totalSizeBits_agent_id_id() {
        return 32;
    }

    /**
     * Return the size, in bytes, of each element of the array 'agent_id.id'
     */
    public static int elementSize_agent_id_id() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of each element of the array 'agent_id.id'
     */
    public static int elementSizeBits_agent_id_id() {
        return 16;
    }

    /**
     * Return the number of dimensions in the array 'agent_id.id'
     */
    public static int numDimensions_agent_id_id() {
        return 1;
    }

    /**
     * Return the number of elements in the array 'agent_id.id'
     */
    public static int numElements_agent_id_id() {
        return 2;
    }

    /**
     * Return the number of elements in the array 'agent_id.id'
     * for the given dimension.
     */
    public static int numElements_agent_id_id(int dimension) {
      int array_dims[] = { 2,  };
        if (dimension < 0 || dimension >= 1) throw new ArrayIndexOutOfBoundsException();
        if (array_dims[dimension] == 0) throw new IllegalArgumentException("Array dimension "+dimension+" has unknown size");
        return array_dims[dimension];
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: loc.x
    //   Field type: int[]
    //   Offset (bits): 0
    //   Size of each element (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'loc.x' is signed (false).
     */
    public static boolean isSigned_loc_x() {
        return false;
    }

    /**
     * Return whether the field 'loc.x' is an array (true).
     */
    public static boolean isArray_loc_x() {
        return true;
    }

    /**
     * Return the offset (in bytes) of the field 'loc.x'
     */
    public static int offset_loc_x(int index1) {
        int offset = 0;
        if (index1 < 0 || index1 >= 2) throw new ArrayIndexOutOfBoundsException();
        offset += 160 + index1 * 32;
        return (offset / 8);
    }

    /**
     * Return the offset (in bits) of the field 'loc.x'
     */
    public static int offsetBits_loc_x(int index1) {
        int offset = 0;
        if (index1 < 0 || index1 >= 2) throw new ArrayIndexOutOfBoundsException();
        offset += 160 + index1 * 32;
        return offset;
    }

    /**
     * Return the entire array 'loc.x' as a int[]
     */
    public int[] get_loc_x() {
        int[] tmp = new int[2];
        for (int index0 = 0; index0 < numElements_loc_x(0); index0++) {
            tmp[index0] = getElement_loc_x(index0);
        }
        return tmp;
    }

    /**
     * Set the contents of the array 'loc.x' from the given int[]
     */
    public void set_loc_x(int[] value) {
        for (int index0 = 0; index0 < value.length; index0++) {
            setElement_loc_x(index0, value[index0]);
        }
    }

    /**
     * Return an element (as a int) of the array 'loc.x'
     */
    public int getElement_loc_x(int index1) {
        return (int)getUIntBEElement(offsetBits_loc_x(index1), 16);
    }

    /**
     * Set an element of the array 'loc.x'
     */
    public void setElement_loc_x(int index1, int value) {
        setUIntBEElement(offsetBits_loc_x(index1), 16, value);
    }

    /**
     * Return the total size, in bytes, of the array 'loc.x'
     */
    public static int totalSize_loc_x() {
        return (64 / 8);
    }

    /**
     * Return the total size, in bits, of the array 'loc.x'
     */
    public static int totalSizeBits_loc_x() {
        return 64;
    }

    /**
     * Return the size, in bytes, of each element of the array 'loc.x'
     */
    public static int elementSize_loc_x() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of each element of the array 'loc.x'
     */
    public static int elementSizeBits_loc_x() {
        return 16;
    }

    /**
     * Return the number of dimensions in the array 'loc.x'
     */
    public static int numDimensions_loc_x() {
        return 1;
    }

    /**
     * Return the number of elements in the array 'loc.x'
     */
    public static int numElements_loc_x() {
        return 2;
    }

    /**
     * Return the number of elements in the array 'loc.x'
     * for the given dimension.
     */
    public static int numElements_loc_x(int dimension) {
      int array_dims[] = { 2,  };
        if (dimension < 0 || dimension >= 1) throw new ArrayIndexOutOfBoundsException();
        if (array_dims[dimension] == 0) throw new IllegalArgumentException("Array dimension "+dimension+" has unknown size");
        return array_dims[dimension];
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: loc.y
    //   Field type: int[]
    //   Offset (bits): 16
    //   Size of each element (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'loc.y' is signed (false).
     */
    public static boolean isSigned_loc_y() {
        return false;
    }

    /**
     * Return whether the field 'loc.y' is an array (true).
     */
    public static boolean isArray_loc_y() {
        return true;
    }

    /**
     * Return the offset (in bytes) of the field 'loc.y'
     */
    public static int offset_loc_y(int index1) {
        int offset = 16;
        if (index1 < 0 || index1 >= 2) throw new ArrayIndexOutOfBoundsException();
        offset += 160 + index1 * 32;
        return (offset / 8);
    }

    /**
     * Return the offset (in bits) of the field 'loc.y'
     */
    public static int offsetBits_loc_y(int index1) {
        int offset = 16;
        if (index1 < 0 || index1 >= 2) throw new ArrayIndexOutOfBoundsException();
        offset += 160 + index1 * 32;
        return offset;
    }

    /**
     * Return the entire array 'loc.y' as a int[]
     */
    public int[] get_loc_y() {
        int[] tmp = new int[2];
        for (int index0 = 0; index0 < numElements_loc_y(0); index0++) {
            tmp[index0] = getElement_loc_y(index0);
        }
        return tmp;
    }

    /**
     * Set the contents of the array 'loc.y' from the given int[]
     */
    public void set_loc_y(int[] value) {
        for (int index0 = 0; index0 < value.length; index0++) {
            setElement_loc_y(index0, value[index0]);
        }
    }

    /**
     * Return an element (as a int) of the array 'loc.y'
     */
    public int getElement_loc_y(int index1) {
        return (int)getUIntBEElement(offsetBits_loc_y(index1), 16);
    }

    /**
     * Set an element of the array 'loc.y'
     */
    public void setElement_loc_y(int index1, int value) {
        setUIntBEElement(offsetBits_loc_y(index1), 16, value);
    }

    /**
     * Return the total size, in bytes, of the array 'loc.y'
     */
    public static int totalSize_loc_y() {
        return (64 / 8);
    }

    /**
     * Return the total size, in bits, of the array 'loc.y'
     */
    public static int totalSizeBits_loc_y() {
        return 64;
    }

    /**
     * Return the size, in bytes, of each element of the array 'loc.y'
     */
    public static int elementSize_loc_y() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of each element of the array 'loc.y'
     */
    public static int elementSizeBits_loc_y() {
        return 16;
    }

    /**
     * Return the number of dimensions in the array 'loc.y'
     */
    public static int numDimensions_loc_y() {
        return 1;
    }

    /**
     * Return the number of elements in the array 'loc.y'
     */
    public static int numElements_loc_y() {
        return 2;
    }

    /**
     * Return the number of elements in the array 'loc.y'
     * for the given dimension.
     */
    public static int numElements_loc_y(int dimension) {
      int array_dims[] = { 2,  };
        if (dimension < 0 || dimension >= 1) throw new ArrayIndexOutOfBoundsException();
        if (array_dims[dimension] == 0) throw new IllegalArgumentException("Array dimension "+dimension+" has unknown size");
        return array_dims[dimension];
    }

}
