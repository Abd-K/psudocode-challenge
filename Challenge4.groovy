//We like to monitor where users leave their mouse on our site, and send it as a
//stream to our servers.  This class represents a single mouse position from a user
//at a point in time
​class DataPacket{
    float mouseXPosition;
    float mouseYPosition;
    String userName;
 
    public DataPacket(String userName, float mouseXPosition, float mouseYPosition) {
        this.mouseXPosition = mouseXPosition;
        this.mouseYPosition = mouseYPosition;
        this.userName = userName;
    }
 
    void updateMousePosition(float xPosition, float yPosition) {
        this.mouseXPosition = xPosition;
        this.mouseYPosition = yPosition;
        }
 

    byte[] serialize() {
        def array = [];
        array += floatToBytes(mouseXPosition) as ArrayList;
        array += floatToBytes(mouseYPosition) as ArrayList;
        array += userName.getBytes() as ArrayList;
        array as byte[];
    }
 
    static byte[] floatToBytes(float input) {
        int byteValue = Float.floatToRawIntBits(input);
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(byteValue & 0xff);
        bytes[1] = (byte)((byteValue >> 8) & 0xff);
        bytes[2] = (byte)((byteValue >> 16) & 0xff);
        bytes[3] = (byte)((byteValue >> 24) & 0xff);
        return bytes;
    }
 
    static byte[] intToBytes(int input) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(input & 0xff);
        bytes[1] = (byte)((input >> 8) & 0xff);
        bytes[2] = (byte)((input >> 16) & 0xff);
        bytes[3] = (byte)((input >> 24) & 0xff);
        return bytes;
    }
}