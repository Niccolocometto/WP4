/**
 * This class is automatically generated by mig. DO NOT EDIT THIS FILE.
 * This class implements a Java interface to the 'AgillaResetMsg'
 * message type.
 */

package agilla.messages;

public class AgillaResetMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 4;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 33;

    /** Create a new AgillaResetMsg of size 4. */
    public AgillaResetMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new AgillaResetMsg of the given data_length. */
    public AgillaResetMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaResetMsg with the given data_length
     * and base offset.
     */
    public AgillaResetMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaResetMsg using the given byte array
     * as backing store.
     */
    public AgillaResetMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaResetMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public AgillaResetMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaResetMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public AgillaResetMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaResetMsg embedded in the given message
     * at the given base offset.
     */
    public AgillaResetMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new AgillaResetMsg embedded in the given message
     * at the given base offset and length.
     */
    public AgillaResetMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <AgillaResetMsg> \n";
      try {
        s += "  [from=0x"+Long.toHexString(get_from())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [address=0x"+Long.toHexString(get_address())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: from
    //   Field type: int, unsigned
    //   Offset (bits): 0
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'from' is signed (false).
     */
    public static boolean isSigned_from() {
        return false;
    }

    /**
     * Return whether the field 'from' is an array (false).
     */
    public static boolean isArray_from() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'from'
     */
    public static int offset_from() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'from'
     */
    public static int offsetBits_from() {
        return 0;
    }

    /**
     * Return the value (as a int) of the field 'from'
     */
    public int get_from() {
        return (int)getUIntBEElement(offsetBits_from(), 16);
    }

    /**
     * Set the value of the field 'from'
     */
    public void set_from(int value) {
        setUIntBEElement(offsetBits_from(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'from'
     */
    public static int size_from() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'from'
     */
    public static int sizeBits_from() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: address
    //   Field type: int, unsigned
    //   Offset (bits): 16
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'address' is signed (false).
     */
    public static boolean isSigned_address() {
        return false;
    }

    /**
     * Return whether the field 'address' is an array (false).
     */
    public static boolean isArray_address() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'address'
     */
    public static int offset_address() {
        return (16 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'address'
     */
    public static int offsetBits_address() {
        return 16;
    }

    /**
     * Return the value (as a int) of the field 'address'
     */
    public int get_address() {
        return (int)getUIntBEElement(offsetBits_address(), 16);
    }

    /**
     * Set the value of the field 'address'
     */
    public void set_address(int value) {
        setUIntBEElement(offsetBits_address(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'address'
     */
    public static int size_address() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'address'
     */
    public static int sizeBits_address() {
        return 16;
    }

}