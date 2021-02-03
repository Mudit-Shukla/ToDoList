package sample;

import sample.dataModel.ToDoItems;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<ToDoItems> itemsList;

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
    }
}
