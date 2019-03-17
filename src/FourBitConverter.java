public class FourBitConverter {
    public static String convert (int decimal){
        String fourBit = "";
        int number = decimal;
        int n;
        for (n = 8; n >= 1; n = n/2 ){
            if (number >= n){
                fourBit = fourBit.concat("1");
                number -= n;
            } else {
                fourBit = fourBit.concat("0");
            }
        }
        return fourBit;
    }
}
