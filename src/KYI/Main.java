package KYI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FRANKLIN GOTHIC MEDIUM, BOOKSHELF, podmienky pre rovnkych userov, reset hesla, pridat update na usera/viac panov pre storage :potraviny, palenky atd,PRIDAT BUYING PRICE DO STORAGE LEBO NEBUDEME VEDIET KOLKO ZA TO ZAPLATIL
        Parent root = FXMLLoader.load(getClass().getResource("owner/owner.fxml"));
        primaryStage.setTitle("KnowYourIncome");
        primaryStage.getIcons().add(new Image("Logo/icon.png"));
        primaryStage.setMinWidth(900);
      //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
