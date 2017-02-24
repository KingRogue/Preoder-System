package com.kingrogue.preorder.model;

import javafx.beans.property.SimpleObjectProperty;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim G on 18-Feb-17.
 */
public class DataController {

    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private ArrayList<Activity> activities;
    private String location;
    private int productIdCount = 0;
    private int orderIdCount = 0;
    private int customerIdCount = 0;
    private int activityIdCount = 0;

    public DataController() {
        String location = System.getProperty("user.home");
        this.location = location.concat("/AppData/Roaming/PreorderSystem"); //appdata on  local user
        System.out.println(location);
        File dir = new File(location);
        boolean firstRun = dir.mkdirs(); //will be true if first time running on computer
        this.products = loadProducts();
        this.orders = loadOrders();
        this.customers = loadCustomers();
        this.activities = loadActivities();

        System.out.println("Loaded all files");
    }

    private ArrayList<Product> loadProducts(){
        System.out.println("Loading Products");
        ArrayList<Product> products = new ArrayList<Product>();
        BufferedReader reader = null;
        ArrayList<String> contents = new ArrayList<String>();

        try{
            reader = new BufferedReader (new FileReader(this.location + "/products.txt")); //open products file
            String contentLine = reader.readLine();
            int c;
            while (contentLine != null){ //go through each line of the file and add to a list
                contents.add(contentLine);
                contentLine = reader.readLine();
            }
            reader.close();
        }catch (FileNotFoundException e){ //no file exists, so need to create it
            File file = new File(this.location + "/products.txt");
            try{
                file.createNewFile();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(contents);
        if (contents.isEmpty()){
            return new ArrayList<Product>(); //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                products.add(new Product(new Integer(lineList[0]),lineList[1]));
                this.productIdCount += 1;
            }
        }
        return products;
    }

    private ArrayList<Order> loadOrders(){
        System.out.println("Loading Orders");
        ArrayList<Order> orders = new ArrayList<Order>();
        BufferedReader reader = null;
        ArrayList<String> contents = new ArrayList<String>();

        try{
            reader = new BufferedReader (new FileReader(this.location + "/orders.txt")); //open products file
            String contentLine = reader.readLine();
            int c;
            while (contentLine != null){ //go through each line of the file and add to a list
                contents.add(contentLine);
                contentLine = reader.readLine();
            }
            reader.close();
        }catch (FileNotFoundException e){ //no file exists, so need to create it
            File file = new File(this.location + "/orders.txt");
            try{
                file.createNewFile();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(contents);
        if (contents.isEmpty()){
            return new ArrayList<Order>(); //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                orders.add(new Order( new Integer(lineList[0]), new Integer(lineList[1]), new Integer(lineList[2]), new Integer(lineList[3]), new Integer(lineList[4]), LocalDate.parse(lineList[5],dtf)));
                this.orderIdCount += 1;
            }
        }
        return orders;
    }

    private ArrayList<Customer> loadCustomers(){
        System.out.println("Loading Customers");
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader reader = null;
        ArrayList<String> contents = new ArrayList<String>();

        try{
            reader = new BufferedReader (new FileReader(this.location + "/customers.txt")); //open products file
            String contentLine = reader.readLine();
            int c;
            while (contentLine != null){ //go through each line of the file and add to a list
                contents.add(contentLine);
                contentLine = reader.readLine();
            }
            reader.close();
        }catch (FileNotFoundException e){ //no file exists, so need to create it
            File file = new File(this.location + "/customers.txt");
            try{
                file.createNewFile();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(contents);
        if (contents.isEmpty()){
            return new ArrayList<Customer>(); //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                customers.add(new Customer(new Integer(lineList[0]), lineList[1], new Integer(lineList[2])));
                this.customerIdCount += 1;
            }
        }
        return customers;
    }

    private ArrayList<Activity> loadActivities(){
        System.out.println("Loading Activities");
        ArrayList<Activity> activities = new ArrayList<Activity>();
        BufferedReader reader = null;
        ArrayList<String> contents = new ArrayList<String>();

        try{
            reader = new BufferedReader (new FileReader(this.location + "/activities.txt")); //open products file
            String contentLine = reader.readLine();
            int c;
            while (contentLine != null){ //go through each line of the file and add to a list
                contents.add(contentLine);
                contentLine = reader.readLine();
            }
            reader.close();
        }catch (FileNotFoundException e){ //no file exists, so need to create it
            File file = new File(this.location + "/activities.txt");
            try{
                file.createNewFile();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(contents);
        if (contents.isEmpty()){
            return new ArrayList<Activity>(); //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                activities.add(new Activity(new Integer(lineList[0]), new Integer(lineList[1]), new Boolean(lineList[2]), new Boolean(lineList[3]), new Integer(lineList[4])));
                this.activityIdCount += 1;
            }
        }
        return activities;
    }

    private void updateObservables(){

    }

    public void addProduct(){

    }
    public void addOrder(){

    }
    public void addCustomer(){

    }
    public void addActivity(){

    }

    public static void main(String[] args) {

    }
}