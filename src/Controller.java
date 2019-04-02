import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {
    private Model model;

    public Controller(Stage primaryStage){
        Model model = new Model();
        this.model = model;
        View view = new View(primaryStage, this, this.model);
    }

    public void input(String input, String exponent){
        String remark = "normal";
        BigDecimal temporaryDouble;
        int temporaryExponent = 0;

        try {
            //temporaryDouble = Double.parseDouble(input);
            temporaryDouble = new BigDecimal(input);
            temporaryExponent = Integer.parseInt(exponent);
        }catch (NumberFormatException e){
            remark = "nan";
            process("nan", "nan", remark);
            return;
        }
        if(temporaryDouble.compareTo(new BigDecimal(0)) == 0){
            remark = "withinrange";
            process("0000000", 0 + "", remark);
            return;
        }
        if (temporaryDouble.compareTo(new BigDecimal(0))==1) {
            if (temporaryDouble.compareTo(new BigDecimal(9999999)) == 1 ) {
                while (temporaryDouble.compareTo(new BigDecimal(9999999)) == 1 && temporaryExponent > -101) {
                    temporaryDouble = temporaryDouble.divide(new BigDecimal(10));
                    temporaryExponent++;
                }
            } else if (temporaryDouble.compareTo(new BigDecimal(1000000)) == -1 ) {
                while (temporaryDouble.compareTo(new BigDecimal(1000000)) == -1 && temporaryExponent > -101) {
                    temporaryDouble = temporaryDouble.multiply(new BigDecimal(10.0));
                    temporaryExponent--;
                }
            }
        } else if (temporaryDouble.compareTo(new BigDecimal(0.0)) == -1){
            if (temporaryDouble.compareTo(new BigDecimal(-9999999)) == -1){
                while (temporaryDouble.compareTo(new BigDecimal(-9999999)) == -1 && temporaryExponent > -101){
                    temporaryDouble = temporaryDouble.divide(new BigDecimal(10));
                    temporaryExponent++;
                }
            } else if (temporaryDouble.compareTo(new BigDecimal(-1000000)) == 1){
                while (temporaryDouble.compareTo(new BigDecimal(-1000000)) == 1 && temporaryExponent > -101){
                    temporaryDouble = temporaryDouble.multiply(new BigDecimal(10));
                    temporaryExponent--;
                }
            }
        }

        if (temporaryExponent < -101){
            while (temporaryExponent < -101){
                temporaryDouble = temporaryDouble.divide(new BigDecimal(10.0));
                temporaryExponent++;
            }
        }

        if (temporaryDouble.compareTo(new BigDecimal(9999999)) == 1){
            while (temporaryDouble.compareTo(new BigDecimal(9999999)) == 1) {
                temporaryDouble = temporaryDouble.divide(new BigDecimal(10.0));
                temporaryExponent++;
            }
        }

        if (temporaryDouble.compareTo(new BigDecimal(-9999999)) == -1 ){
            while (temporaryDouble.compareTo(new BigDecimal(-9999999)) == -1 ){
                temporaryDouble = temporaryDouble.divide(new BigDecimal(10));
                temporaryExponent++;
            }
        }

        if (temporaryExponent > 90 && temporaryDouble.compareTo(new BigDecimal(0)) == 1){
            remark = "positiveinfinity";
            process("positiveinfinity", "positiveinfinity", remark);
        } else if (temporaryExponent > 90 && temporaryDouble.compareTo(new BigDecimal(0)) == -1){
            remark = "negativeinfinity";
            process("negativeinfinity", "negativeinfinity", remark);
        } else if (temporaryExponent == -101 && ((temporaryDouble.compareTo(new BigDecimal(1)) == -1 && temporaryDouble.compareTo(new BigDecimal(0))== 1) ||
                                                (temporaryDouble.compareTo(new BigDecimal(-1)) == 1 && temporaryDouble.compareTo(new BigDecimal(0)) == -1))) {
            remark = "withinrange";
            process("0000000", 0 + "", remark);
        } else if (temporaryExponent < -101) {
            remark = "withinrange";
            process("0000000", 0 + "", remark);
        }
        else {
            remark = "withinrange";
            //BigDecimal bd = new BigDecimal(temporaryDouble);
            temporaryDouble = temporaryDouble.setScale(0, RoundingMode.HALF_EVEN);
            //double finalDouble = bd.doubleValue();
            int finalInt = temporaryDouble.intValue();
            process(finalInt+ "", temporaryExponent + "", remark);
        }

    }

    public void process(String finalInput, String finalExponent, String remark){
        if (remark.equals("nan")){
            model.setSignBit(0);
            model.setCombi("11111");
            model.setExponent("010101");
            model.setMantissa1("0000000000");
            model.setMantissa2("0000000000");
            String finalHex = Hex32Converter.convert(model.getSignBit() +
                    model.getCombi() +
                    model.getExponent() +
                    model.getMantissa1() +
                    model.getMantissa2());

            model.setFinalHex(finalHex.toUpperCase());
            model.Notify();
        } else if (remark.equals("positiveinfinity")){
            model.setSignBit(0);
            model.setCombi("11110");
            model.setExponent("111111");
            model.setMantissa1("1111111111");
            model.setMantissa2("1111111111");
            String finalHex = Hex32Converter.convert(model.getSignBit() +
                    model.getCombi() +
                    model.getExponent() +
                    model.getMantissa1() +
                    model.getMantissa2());

            model.setFinalHex(finalHex.toUpperCase());
            model.Notify();
        } else if (remark.equals("negativeinfinity")){
            model.setSignBit(1);
            model.setCombi("11110");
            model.setExponent("000000");
            model.setMantissa1("0000000000");
            model.setMantissa2("0000000000");
            String finalHex = Hex32Converter.convert(model.getSignBit() +
                    model.getCombi() +
                    model.getExponent() +
                    model.getMantissa1() +
                    model.getMantissa2());

            model.setFinalHex(finalHex.toUpperCase());
            model.Notify();
        } else {
            model.setFinalInput(finalInput);
            model.setFinalExponent(finalExponent);

            int wholeNumber = Integer.parseInt(finalInput);
            int exponent = Integer.parseInt(finalExponent);

            if (wholeNumber >= 0) {
                model.setSignBit(0);
            } else {
                model.setSignBit(1);
                wholeNumber = wholeNumber * -1;
            }


            String finalInput1 = wholeNumber + "";
            if(wholeNumber == 0){
                finalInput1 = "0000000";
            }

            String padding = "";
            if (finalInput1.length() < 7){
                while(padding.length() != (7-finalInput1.length())){
                    padding = padding.concat("0");
                }
            }

            finalInput1 = padding+finalInput1;

            String MSD = finalInput1.substring(0,1);


            String MSDbits = FourBitConverter.convert(Integer.parseInt(MSD));
            String exponentBits = EPrimeConverter.convert(exponent, wholeNumber);

            if (Integer.parseInt(MSD) < 8){
                String combi = "";
                combi = combi + exponentBits.substring(0,2) + MSDbits.substring(1);
                model.setCombi(combi);
            } else {
                String combi = "11";
                combi = combi + exponentBits.substring(0,2) + MSDbits.substring(3);
                model.setCombi(combi);
            }

            model.setExponent(exponentBits.substring(2));


            String first = finalInput1.substring(1,2);
            String second = finalInput1.substring(2,3);
            String third = finalInput1.substring(3,4);

            String fourth = finalInput1.substring(4,5);
            String fifth = finalInput1.substring(5,6);
            String sixth = finalInput1.substring(6,7);

            int[] m1 = DensleyPackedConverter.toDensleyPackedBCD(Integer.parseInt(first),
                                                                        Integer.parseInt(second),
                                                                        Integer.parseInt(third));

            int[] m2 = DensleyPackedConverter.toDensleyPackedBCD(Integer.parseInt(fourth),
                                                                         Integer.parseInt(fifth),
                                                                         Integer.parseInt(sixth));

            String mantissa1 = "";
            for (int i: m1){
                mantissa1= mantissa1.concat(i+"");
            }

            String mantissa2 = "";
            for (int i: m2){
                mantissa2 = mantissa2.concat(i + "");
            }


            model.setMantissa1(mantissa1);
            model.setMantissa2(mantissa2);

            String finalHex = Hex32Converter.convert(model.getSignBit() +
                                                                 model.getCombi() +
                                                                 model.getExponent() +
                                                                 model.getMantissa1() +
                                                                 model.getMantissa2());

            model.setFinalHex(finalHex.toUpperCase());
            model.Notify();

        }

    }
}
