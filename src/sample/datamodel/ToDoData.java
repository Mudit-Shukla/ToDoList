package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private static String fileName = "TodolistItems.txt";

    private ObservableList<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    public static ToDoData getInstance(){
        return instance;
    }

    private ToDoData(){
        formatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
    }

    public void addToDoItem(TodoItem item){
        todoItems.add(item);
    }

    public void setTodoItems(ObservableList<TodoItem> todoItems){
        this.todoItems = todoItems;
    }

    public ObservableList<TodoItem> getTodoItems(){
        return todoItems;
    }

    public void loadToDoItems() throws IOException{

        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try{
            while ((input = br.readLine()) != null){
                String[] item = input.split("\t");
                String shortDescription = item[0];
                String detailDescription = item[1];
                String deadline = item[2];

                LocalDate date = LocalDate.parse(deadline, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, detailDescription, date);
                todoItems.add(todoItem);
            }

        }finally {
            if(br != null)
                br.close();
        }

    }

    public void storeToDoItems() throws IOException{

        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<TodoItem> iterator = todoItems.iterator();
            while (iterator.hasNext()){
                TodoItem item = iterator.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        }finally {
            if(bw != null)
                bw.close();
        }

    }
}
