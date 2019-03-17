public class DensleyPackedConverter {
    public static int[] toNibble(int num){
        int[] nibble = new int[4];
        for(int i=3 ; i>=0 ; i--){
            nibble[i] = num%2;
            num=num/2;
        }
        return nibble;
    }
    public static int[] toDensleyPackedBCD(int num1, int num2, int num3){
        //a b c d e f g h i j k  m
        //0 1 2 3 4 5 6 7 8 9 10 11
        int[] packedBCD = new int[12];
        int[] densBCD = new int[10];

        int[] nibble1 = toNibble(num1);
        int[] nibble2 = toNibble(num2);
        int[] nibble3 = toNibble(num3);

        for(int i=0 ; i<4 ; i++){
            packedBCD[i] = nibble1[i];
        }

        for(int i=4 ; i<8 ; i++){
            packedBCD[i] = nibble2[i-4];
        }

        for(int i=8 ; i<12 ; i++){
            packedBCD[i] = nibble3[i-8];
        }

        String aei = Integer.toString(nibble1[0]) + Integer.toString(nibble2[0]) + Integer.toString(nibble3[0]);

        if(aei.equals("000")){
            densBCD[0] = packedBCD[1];
            densBCD[1] = packedBCD[2];
            densBCD[2] = packedBCD[3];
            densBCD[3] = packedBCD[5];
            densBCD[4] = packedBCD[6];
            densBCD[5] = packedBCD[7];
            densBCD[6] = 0;
            densBCD[7] = packedBCD[9];
            densBCD[8] = packedBCD[10];
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("001")){
            densBCD[0] = packedBCD[1];
            densBCD[1] = packedBCD[2];
            densBCD[2] = packedBCD[3];
            densBCD[3] = packedBCD[5];
            densBCD[4] = packedBCD[6];
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 0;
            densBCD[8] = 0;
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("010")){
            densBCD[0] = packedBCD[1];
            densBCD[1] = packedBCD[2];
            densBCD[2] = packedBCD[3];
            densBCD[3] = packedBCD[9];
            densBCD[4] = packedBCD[10];
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 0;
            densBCD[8] = 1;
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("100")){
            densBCD[0] = packedBCD[9];
            densBCD[1] = packedBCD[10];
            densBCD[2] = packedBCD[3];
            densBCD[3] = packedBCD[5];
            densBCD[4] = packedBCD[6];
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 1;
            densBCD[8] = 0;
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("110")){
            densBCD[0] = packedBCD[9];
            densBCD[1] = packedBCD[10];
            densBCD[2] = packedBCD[3];
            densBCD[3] = 0;
            densBCD[4] = 0;
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 1;
            densBCD[8] = 1;
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("101")){
            densBCD[0] = packedBCD[5];
            densBCD[1] = packedBCD[6];
            densBCD[2] = packedBCD[3];
            densBCD[3] = 0;
            densBCD[4] = 1;
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 1;
            densBCD[8] = 1;
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("011")){
            densBCD[0] = packedBCD[1];
            densBCD[1] = packedBCD[2];
            densBCD[2] = packedBCD[3];
            densBCD[3] = 1;
            densBCD[4] = 0;
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 1;
            densBCD[8] = 1;
            densBCD[9] = packedBCD[11];
        }else if(aei.equals("111")){
            densBCD[0] = 0;
            densBCD[1] = 0;
            densBCD[2] = packedBCD[3];
            densBCD[3] = 1;
            densBCD[4] = 1;
            densBCD[5] = packedBCD[7];
            densBCD[6] = 1;
            densBCD[7] = 1;
            densBCD[8] = 1;
            densBCD[9] = packedBCD[11];
        }
        return densBCD;
    }
}
