//This function receives a signed integer, and returns its representation
//in binary as a string
String getBitStirngRepresentation(int n)
{
    String s = "";
    while(n != 0) {
        if(n % 2 == 0)
        {
            s = "0" + s;
        }
        else {
            s = "1" + s;
        }
        n = n/2;
    }
    return s;
}