import javafx.stage.Stage;

public class Controller {
    private Model model;

    public Controller(Stage primaryStage){
        Model model = new Model();
        this.model = model;
        View view = new View(primaryStage, this, this.model);
    }

    public void input(String input, String exponent){
        String remark = "normal";
        double temporaryDouble = 0000000f;
        int temporaryExponent = 0;

        try {
            temporaryDouble = Double.parseDouble(input);
            temporaryExponent = Integer.parseInt(exponent);
        }catch (NumberFormatException e){
            remark = "nan";
            System.out.println(remark);
            process("nan", "nan", remark);
            return;
        }

        if (temporaryDouble > 0.0) {
            if (temporaryDouble > 9999999.9d) {
                while (temporaryDouble > 9999999d) {
                    temporaryDouble = temporaryDouble / 10.0d;
                    temporaryExponent++;
                }
            } else if (temporaryDouble < 1000000d) {
                while (temporaryDouble < 1000000d) {
                    temporaryDouble = temporaryDouble * 10.0d;
                    temporaryExponent--;
                }
            }
        } else if (temporaryDouble < 0.0){
            if (temporaryDouble < -9999999.9d){
                while (temporaryDouble < -9999999.9d){
                    temporaryDouble = temporaryDouble / 10.0d;
                    temporaryExponent++;
                }
            } else if (temporaryDouble > -1000000d){
                while (temporaryDouble > -1000000d){
                    temporaryDouble = temporaryDouble * 10.0d;
                    temporaryExponent--;
                }
            }
        }

        System.out.println(temporaryDouble);
        System.out.println(temporaryExponent);

        if (temporaryExponent > 90){
            remark = "positiveinfinity";
            process("positiveinfinity", "positiveinfinity", remark);
        } else if (temporaryExponent < -101){
            remark = "negativeinfinity";
            process("negativeinfinity", "negativeinfinity", remark);
        }

    }

    public void process(String finalInput, String finalExponent, String remark){
        if (remark.equals("nan")){
            model.setSignBit(0);
            model.setCombi("11111");
            model.setExponent("010101");
            model.setMantissa1("0101010101");
            model.setMantissa2("0101010101");
            model.Notify();
        } else if (remark.equals("positiveinfinity")){
            model.setSignBit(0);
            model.setCombi("11110");
            model.setExponent("111111");
            model.setMantissa1("1111111111");
            model.setMantissa2("1111111111");
            model.Notify();
        } else if (remark.equals("negativeinifinity")){
            model.setSignBit(1);
            model.setCombi("11110");
            model.setExponent("000000");
            model.setMantissa1("0000000000");
            model.setMantissa2("0000000000");
            model.Notify();
        }

    }
}
