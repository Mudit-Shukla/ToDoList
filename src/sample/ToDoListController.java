package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.ToDoData;
import sample.datamodel.TodoItem;

import java.time.LocalDate;

public class ToDoListController {

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailDescriptionField;

    @FXML
    private DatePicker deadlineField;

    @FXML
    public TodoItem processResult(){
        String shortDescription = shortDescriptionField.getText();
        String detailDescription = detailDescriptionField.getText();
        LocalDate deadline = deadlineField.getValue();
        TodoItem newItem = new TodoItem(shortDescription, detailDescription, deadline);
        ToDoData.getInstance().addToDoItem(newItem);
        return newItem;
    }

}
