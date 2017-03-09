package com.kingrogue.preorder.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * Created by Tim G on 18-Feb-17.
 */
public class DataController {

    private ObservableList<Product> products;
    private ObservableList<Order> orders;
    private ObservableList<Customer> customers;
    private ObservableList<Activity> activities;
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

        this.products = FXCollections.observableArrayList();
        this.orders = FXCollections.observableArrayList();
        this.customers = FXCollections.observableArrayList();
        this.activities = FXCollections.observableArrayList();

        this.loadProducts();
        this.loadCustomers();
        this.loadOrders();
        this.loadActivities();

        System.out.println("Loaded all files");
    }

    private void loadProducts(){
        System.out.println("Loading Products");
        ObservableList<Product> products = FXCollections.observableArrayList();
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
            new File(this.location).mkdirs();
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
            return; //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                addProduct(new Integer(lineList[0]),lineList[1]);
            }
        }
        return;
    }

    private void loadOrders(){
        System.out.println("Loading Orders");
        ObservableList<Order> orders = FXCollections.observableArrayList();
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
            new File(this.location).mkdirs();
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
            return; //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                addOrder(new Integer(lineList[0]), new Integer(lineList[1]), new Integer(lineList[2]), new Integer(lineList[3]), new Integer(lineList[4]), LocalDate.parse(lineList[5],dtf));
            }
        }
        return;
    }

    private void loadCustomers(){
        System.out.println("Loading Customers");
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
            new File(this.location).mkdirs();
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
            return; //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                addCustomer(new Integer(lineList[0]), lineList[1], new Integer(lineList[2]));
            }
        }
        return;
    }

    private void loadActivities(){
        System.out.println("Loading Activities");
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
            new File(this.location).mkdirs();
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
            return; //if file was empty
        }else{
            String metadata = contents.get(0); //turn each line of the file into a product object
            contents.remove(0);
            for (int i = 0 ; i<contents.size() ; i++) {
                String[] lineList = contents.get(i).split(",");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                addActivity(new Integer(lineList[0]), new Integer(lineList[1]), new Boolean(lineList[2]), new Boolean(lineList[3]), new Integer(lineList[4]), LocalDate.parse(lineList[5],dtf));
            }
        }
        return;
    }

    public void addProduct(int id, String name){
        if (id == -1){
            id = this.productIdCount;
        }
        this.products.add(new Product(id,name));
        this.productIdCount += 1;
    }

    public void addOrder(int id, int receiptNo, int customerID, int productID, int quantity, LocalDate date){ //adding a new order to the system
        boolean newOrderBool = false;
        if (id == -1){
            newOrderBool = true;
            id = this.orderIdCount;
        }
        Order newOrder = new Order(id, receiptNo, customerID, productID, quantity, date); //new order

        newOrder.setQuantitySupplied(0);
        newOrder.setQuantityOwed(quantity);

        Product product = this.getProduct(productID); //updating the relevant product details
        product.setNumberOfOrders( product.getNumberOfOrders() + 1 );
        product.setQuantityOnOrder( product.getQuantityOnOrder() + quantity );
        newOrder.setProductName(product.getName());//getting product name

        Customer customer = this.getCustomer(customerID); //updating the relevant customer details
        customer.setNoOrders( customer.getNoOrders() + 1 );
        newOrder.setCustomerName(customer.getName());

        this.orders.add(newOrder);
        this.orderIdCount += 1;
        if (newOrderBool){
            this.addActivity(id, -1, true, false, 0, date);
        }

    }

    public void addCustomer(int id, String name, int phone){ //creating a new customer
        if (id == -1){
            id = this.customerIdCount;
        }
        Customer newCustomer = new Customer(id, name, phone);
        customers.add(newCustomer);
        this.customerIdCount += 1;
    }

    public void addActivity(int orderID, int activityNo, boolean createdOrder, boolean cancelledOrder, int quantitySupplied, LocalDate date){
        if (activityNo == -1){
            activityNo = this.getNextActivityNumber(orderID);
        }

        Order order = getOrder(orderID);
        order.setQuantitySupplied(order.getQuantitySupplied() + quantitySupplied);
        order.setQuantityOwed(order.getQuantityOwed() - quantitySupplied);

        Activity activity = new Activity(orderID, activityNo, createdOrder, cancelledOrder, quantitySupplied, date);
        activityIdCount += 1;
    }

    public Order getOrder(int id){
        for(Iterator<Order> i = orders.iterator(); i.hasNext();){
            Order order = i.next();
            if(order.getID() == id){
                return order;
            }
        }
        return null;
    }
    public Product getProduct(int id){
        for(Iterator<Product> i = products.iterator(); i.hasNext();){
            Product product = i.next();
            if( product.getId() == id){
                return product;
            }
        }
        return null;
    }
    public Customer getCustomer(int id){
        for(Iterator<Customer> i = customers.iterator(); i.hasNext();){
            Customer customer = i.next();
            if(customer.getID() == id){
                return customer;
            }
        }
        return null;
    }
    public Activity getActivity(int orderID, int activityNo){
        for(Iterator<Activity> i = activities.iterator(); i.hasNext();){
            Activity activity = i.next();
            if(activity.getOrderId() == orderID && activity.getActivityNo() == activityNo){
                return activity;
            }
        }
        return null;
    }
    public int getNextActivityNumber(int orderID){
        int max = 0;
        for(Iterator<Activity> i = activities.iterator(); i.hasNext() ;){
            Activity activity = i.next();
            if (activity.getOrderId() == orderID){
                if (activity.getActivityNo() > max){
                    max = activity.getActivityNo();
                }
            }
        }
        return (max + 1); // max + 1 because we want the next activity number
    }

    public ObservableList<Order> getOrders() {
        return orders;
    }
    public ObservableList<Product> getProducts(){
        return products;
    }
    public ObservableList<Customer> getCustomers(){
        return customers;
    }
    public ObservableList<Activity> getActivities(){
        return activities;
    }
}