package sample.dataModel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class ToDoData {
    private static ToDoData instance = new ToDoData();
    private static String fileName = "ToDoList.txt";

    private List<ToDoItems> toDoItems;
    private DateTimeFormatter formatter;

    public static ToDoData getInstance(){
        return instance;
    }

    private ToDoData(){
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<ToDoItems> getToDoItems(){
        return toDoItems;
    }

    public void setToDoItems(List<ToDoItems> toDoItems){
        this.toDoItems = toDoItems;
    }

    public void loadToDoItems() throws IOException {
        toDoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while((input = br.readLine()) != null){
                  String[] itemPieces = input.split("\t");
                  String shortDescription = itemPieces[0];
                  String description = itemPieces[1];
                  String dueDeadline = itemPieces[2];

                LocalDate date = LocalDate.parse(dueDeadline, formatter);
                ToDoItems toDoItem = new ToDoItems(shortDescription, description, date);
                toDoItems.add(toDoItem);
            }
        }finally {
            if(br != null)
                br.close();
        }
    }

    public void storeToDoItems() throws IOException{
        Path path = Paths.get(fileName);
        BufferedWriter br = Files.newBufferedWriter(path);
        try{
            Iterator<ToDoItems> iterator = toDoItems.iterator();
            while(iterator.hasNext()){
                ToDoItems toDoItem = iterator.next();
                br.write(String.format("%s\t%s\t%s",
                        toDoItem.getShortDescription(),
                        toDoItem.getDetails(),
                        toDoItem.getDueDate().format(formatter)));
            }
        }finally {
            if(br != null)
                br.close();
        }

    }
}
