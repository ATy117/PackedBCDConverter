import javafx.stage.Stage;

public class Controller {

    public Controller(Stage primaryStage){
        Model model = new Model();
        View view = new View(primaryStage, this, model);
    }

    public void input(String input, String exponent){
        
    }
}
