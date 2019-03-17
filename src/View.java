import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class View {
    @FXML public AnchorPane mainAnchorPane;
    @FXML public JFXTextField inputTextField;
    @FXML public JFXTextField expTextField;
    @FXML public JFXTextField signField;
    @FXML public JFXTextField combiField;
    @FXML public JFXTextField ecbField;
    @FXML public JFXTextField mcbFieldOne;
    @FXML public JFXTextField mcbFieldTwo;
    @FXML public JFXButton generateBtn;

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
        stage.show();
    }

    private void init(){

        signField.setEditable(false);
        combiField.setEditable(false);
        mcbFieldOne.setEditable(false);
        mcbFieldTwo.setEditable(false);
        ecbField.setEditable(false);

    }

    public void generate(){
        controller.input(inputTextField.getText(), expTextField.getText());
    }

    public void update() {
        signField.setText(model.getSignBit()+"");
        combiField.setText(model.getCombi());
        ecbField.setText(model.getExponent());
        mcbFieldOne.setText(model.getMantissa1());
        mcbFieldTwo.setText(model.getMantissa2());

    }
}
