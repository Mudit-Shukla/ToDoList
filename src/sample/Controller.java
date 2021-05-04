package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import sample.datamodel.ToDoData;
import sample.datamodel.TodoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class Controller {

    List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> toDoListView;
    @FXML
    private TextArea descriptionOfItem;
    @FXML
    private Label deadline;
    @FXML
    private BorderPane mainBorderPane;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");



    public void initialize() {
//        TodoItem item1 = new TodoItem("item1", "details of item1", LocalDate.of(2021, Month.MARCH, 20));
//        TodoItem item2 = new TodoItem("item2", "details of item2", LocalDate.of(2021, Month.APRIL, 20));
//        TodoItem item3 = new TodoItem("item3", "details of item3", LocalDate.of(2021, Month.MAY, 20));
//        TodoItem item4 = new TodoItem("item4", "details of item4", LocalDate.of(2021, Month.JULY, 20));
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//
//        ToDoData.getInstance().setTodoItems(todoItems);

        toDoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                if (t1 != null) {
                    TodoItem item = toDoListView.getSelectionModel().getSelectedItem();
                    descriptionOfItem.setText(item.getDetails());
                    deadline.setText(formatter.format(item.getDeadline()));
                }
            }
        });

        toDoListView.getItems().setAll(ToDoData.getInstance().getTodoItems());
        toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoListView.getSelectionModel().selectFirst();

    }

    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        try{
            Parent root = FXMLLoader.load(getClass().getResource("todolistDialogPane.fxml"));
            dialog.getDialogPane().setContent(root);

        }catch (Exception e){
            System.out.println("Could not load the New item dialog window");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Ok pressed");
        }else{
            System.out.println("Cancel presses");
        }

    }

    @FXML
    public void handleMouseClick() {
        TodoItem item = toDoListView.getSelectionModel().getSelectedItem();
        descriptionOfItem.setText(item.getDetails());
        deadline.setText(item.getDeadline().toString());

    }

}
