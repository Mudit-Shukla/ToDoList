//package sample;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import sample.datamodel.ToDoData;
//import sample.datamodel.TodoItem;
//
//import java.time.LocalDate;
//
//public class ToDoListController {
//
//    @FXML
//    private TextField shortDescriptionField;
//
//    @FXML
//    private TextArea detailDescriptionField;
//
//    @FXML
//    private DatePicker deadlineField;
//
//    public void processResult(){
//        String shortDescription = shortDescriptionField.getText().trim();
//        String detailDescription = detailDescriptionField.getText().trim();
//        LocalDate deadline = deadlineField.getValue();
//        ToDoData.getInstance().addToDoItem(new TodoItem(shortDescription, detailDescription, deadline));
//    }
//
//}
