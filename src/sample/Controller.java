package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import sample.dataModel.ToDoItems;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<ToDoItems> itemsList;
    @FXML
    private ListView<ToDoItems> toDoListView;
    @FXML
    private TextArea detailsOfItem;
    @FXML
    private Label dueDeadline;

    public void initialize(){
        ToDoItems list1 = new ToDoItems("Mail birthday card", "Buy the birthday card for Manan",
                LocalDate.of(2021, Month.JANUARY,12));
        ToDoItems list2 = new ToDoItems("Visit to the doctor", "Take all the require documents",
                LocalDate.of(2021, Month.FEBRUARY,12));
        ToDoItems list3 = new ToDoItems("Bring the grocery", "Buy 5kg rice and flour for home",
                LocalDate.of(2021, Month.MARCH,12));
        ToDoItems list4 = new ToDoItems("Deposit fees", "Deposit the fees in the bank",
                LocalDate.of(2021, Month.APRIL,12));
        ToDoItems list5 = new ToDoItems("Pick the sister from station", "She is comming on the occasion",
                LocalDate.of(2021, Month.MAY,12));

        itemsList = new ArrayList<>();
        itemsList.add(list1);
        itemsList.add(list2);
        itemsList.add(list3);
        itemsList.add(list4);
        itemsList.add(list5);


        toDoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItems>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItems> observableValue, ToDoItems selectionMode, ToDoItems t1) {
                if(t1 != null){
                    ToDoItems item = toDoListView.getSelectionModel().getSelectedItem();
                    detailsOfItem.setText(item.getDetails());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d yyyy");
                    dueDeadline.setText(dtf.format(item.getDueDate()));
                }
            }
        });

        toDoListView.getItems().addAll(itemsList);
        toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoListView.getSelectionModel().selectFirst();

//        ToDoItems item = (ToDoItems) toDoListView.getSelectionModel().getSelectedItem();
//        detailsOfItem.setText(item.getDetails());
//        dueDeadline.setText(item.getDueDate().toString());
    }

    public void handleMouseClick(){
        ToDoItems item = toDoListView.getSelectionModel().getSelectedItem();
        detailsOfItem.setText(item.getDetails());
        dueDeadline.setText(item.getDueDate().toString());
    }
}
