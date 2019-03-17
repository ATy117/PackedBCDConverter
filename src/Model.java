public class Model {
    int signBit;
    String combi;
    String exponent;
    String mantissa1;
    String mantissa2;
    String finalHex;

    View view;

    public void attach(View view){
        this.view = view;
    }

    public void Notify(){
        view.update();
    }

    public int getSignBit() {
        return signBit;
    }

    public void setSignBit(int signBit) {
        this.signBit = signBit;
    }

    public String getCombi() {
        return combi;
    }

    public void setCombi(String combi) {
        this.combi = combi;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }

    public String getMantissa1() {
        return mantissa1;
    }

    public void setMantissa1(String mantissa1) {
        this.mantissa1 = mantissa1;
    }

    public String getMantissa2() {
        return mantissa2;
    }

    public void setMantissa2(String mantissa2) {
        this.mantissa2 = mantissa2;
    }

    public String getFinalHex() {
        return finalHex;
    }

    public void setFinalHex(String finalHex) {
        this.finalHex = finalHex;
    }
}
