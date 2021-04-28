package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.datamodel.ToDoData;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws IOException {
        try {
            ToDoData.getInstance().loadToDoItems();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws IOException {
        try {
            ToDoData.getInstance().storeToDoItem();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
