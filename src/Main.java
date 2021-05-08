import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("assets/main.fxml"));
        primaryStage.setTitle("Page Replacement Algorithms");
        primaryStage.setScene(new Scene(root, 633, 487));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("assets/icon.png"));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
