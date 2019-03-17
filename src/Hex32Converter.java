public class Hex32Converter {
    public static String convert(String completeBits){
        String finalHex = "";

        int start = 0;
        int end = 4;

        for (int n = 0; n < 32; n+=4){
            String nibble = completeBits.substring(start+n, end+n);
            int decimal = Integer.parseInt(nibble,2);
            String hexString = Integer.toString(decimal, 16);
            finalHex =finalHex.concat(hexString);
        }


        return finalHex;
    }
}
