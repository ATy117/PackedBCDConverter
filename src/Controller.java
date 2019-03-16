import javafx.stage.Stage;

public class Controller {

    public Controller(Stage primaryStage){
        View view = new View(primaryStage, this);
    }

    public void input(String input){
        System.out.println("Input of user: " + input);
    }
}
