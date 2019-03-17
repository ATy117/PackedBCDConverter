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
            if (temporaryDouble > 9999999f) {
                while (temporaryDouble > 9999999f) {
                    temporaryDouble = temporaryExponent / 10.0f;
                    temporaryExponent++;
                }
                System.out.println(temporaryDouble);
                System.out.println(temporaryExponent);
            } else if (temporaryDouble < 1000000f) {
                while (temporaryDouble < 1000000f) {
                    temporaryDouble = temporaryDouble * 10.0f;
                    temporaryExponent--;
                }
                System.out.println(temporaryDouble);
                System.out.println(temporaryExponent);
            }
        } 







    }

    public void process(String finalInput, String finalExponent, String remark){
        if (remark.equals("nan")){
            model.setSignBit(0);
            model.setCombi("11111");
            model.setExponent("000000");
            model.setMantissa1("0000000000");
            model.setMantissa2("0000000000");
            model.Notify();
        }

    }
}
