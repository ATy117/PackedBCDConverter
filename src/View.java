import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class View {
    @FXML public AnchorPane mainAnchorPane;
    @FXML public TextField inputTextField;
    @FXML public TextField expTextField;
    @FXML public Label signField;
    @FXML public Label combiField;
    @FXML public Label ecbField;
    @FXML public Label mcbFieldOne;
    @FXML public Label mcbFieldTwo;
    @FXML public Button generateBtn;
    @FXML public Label finalHexLabel;
    @FXML public Label remarksLabel;

    public Stage stage;
    public Controller controller;
    private Model model;

    public View(Stage primaryStage, Controller controller, Model model){
        this.stage = primaryStage;
        this.controller = controller;
        this.model = model;

        model.attach(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewTemplate.fxml"));
        loader.setController(this);

        Parent root = null;
        try{
            root= (Parent) loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }

        init();

        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.setTitle("IEEE 2008 32-Bit Floating Point Converter");
        stage.setResizable(false);
        stage.show();
    }

    private void init(){

        remarksLabel.setMaxWidth(Double.MAX_VALUE);
        remarksLabel.setAlignment(Pos.CENTER);

    }

    public void generate(){
        controller.input(inputTextField.getText(), expTextField.getText());
    }

    public void update() {
        signField.setText(model.getSignBit()+"");
        combiField.setText(model.getCombi());
        ecbField.setText(model.getExponent());
        mcbFieldOne.setText(model.getMantissa1().substring(0,3) + " " + model.getMantissa1().substring(3,6)+ " " + model.getMantissa1().substring(6));
        mcbFieldTwo.setText(model.getMantissa2().substring(0,3) + " " + model.getMantissa2().substring(3,6)+ " " + model.getMantissa2().substring(6));
        finalHexLabel.setText("0x" + model.getFinalHex());

        if (model.getCombi().equals("11110") && model.getSignBit() == 1){
            remarksLabel.setText("Number Considered Negative Infinity");
        } else if (model.getCombi().equals("11110") && model.getSignBit() == 0) {
            remarksLabel.setText("Number Considered Positive Infinity");
        } else if (model.getCombi().equals("11111")){
            remarksLabel.setText("Not a Number (NaN)");
        } else if (model.getFinalInput().equals("0000000")){
            remarksLabel.setText("Number Represented as 0");
        } else {
            remarksLabel.setText("Adjusted Input: " + model.getFinalInput() + " Adjusted Exp: " + model.getFinalExponent());
        }
    }
}
