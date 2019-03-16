public class EPrimeConverter {
    public static String convert(int exponent){
        String tenBit = "";
        if (exponent < -398 || exponent > 369){
            return "1100000000";
        }
        int prime = exponent + 398;

        int n;
        for (n = 512; n >= 1; n = n/2 ){
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
