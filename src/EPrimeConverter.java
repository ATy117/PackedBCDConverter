public class EPrimeConverter {
    public static String convert(int exponent){
        String tenBit = "";
        if (exponent < -101 || exponent > 90){
            return "11000000";
        }
        int prime = exponent + 101;

        int n;
        for (n = 128; n >= 1; n = n/2 ){
            if (prime >= n){
                tenBit = tenBit.concat("1");
                prime -= n;
            } else {
                tenBit = tenBit.concat("0");
            }
        }
        return tenBit;
    }
}
