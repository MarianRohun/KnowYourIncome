package KYI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Functions {
    public static void newScene(Stage oldStage, FXMLLoader loader, String title) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        oldStage.setTitle(title);
        oldStage.setScene(scene);
        oldStage.show();
    }

}
