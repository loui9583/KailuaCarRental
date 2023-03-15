package controller;

import db.CustomerDb;
import input.UserInput;

import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerController {
    CustomerDb customerDb = new CustomerDb();
    public void showCustomerInfo() throws SQLException{
        System.out.println("[1]See All Customer info [2]See specific customer info");
        int choice = UserInput.getInt();
        if (choice==1) {
             System.out.println("Order customers by:\n[1]ID. [2]Name  [3]Phone  [4]Email   [5]Address   [6]Zip    [7]City     [8]Driver License Number    [9]Driver Since");
            int chooseOrdering = UserInput.getInt();
            String orderBy="";
            switch (chooseOrdering){
                case 1 -> orderBy="customer_id";
                case 2 -> orderBy="name";
                case 3 -> orderBy="phone";
                case 4 -> orderBy="email";
                case 5 -> orderBy="address";
                case 6 -> orderBy="zip";
                case 7 -> orderBy="city";
                case 8 -> orderBy="driver_license_number";
                case 9 -> orderBy="driver_since_date";

                default -> {System.out.println("You didn't enter a valid value. Default sorting by id."); orderBy="customer_id";}
            }
            //Passer den orderBy ind i showAllCustomers metoden, så at vores SQL statement bliver "SELECT .......... ORDER BY orderBy
            customerDb.showAllCustomersInfo(orderBy);
        }
        else {
            System.out.println("Enter customer ID");
            int customer_id = UserInput.getInt();
            customerDb.showSpecificCustomerInfo(customer_id);
        }

    }
    public void addCustomer() throws SQLException {
        System.out.println("Enter name:");
        String name = UserInput.getString();
        System.out.println("Enter phone number:");
        int phone = UserInput.getInt();
        System.out.println("Enter email");
        String email = UserInput.getString();
        customerDb.addCustomer(name, phone, email);
    }
    public void removeCustomer(){
        try {
            System.out.println("Enter the ID of the customer you want to remove:");

            customerDb.removeCustomer(UserInput.getInt());
        } catch (Exception e) {
            System.out.println("Error: Customer ID not found, or customer has an active rental contract.");
            {
            }
        }
    }
    public void updateCustomer() throws SQLException{
        System.out.println("Please write the ID of the customer you want to update");
        int customer_id = UserInput.getInt();
        System.out.println("Please select what you want to be updated:");
        System.out.println("License Number[1] Driver Since[2] Name[3] Phone[4] Email[5] Address[6] Zip[7] City[8]");
        int choice = UserInput.getInt();
        String value="";
        LocalDate date = null;
        if (choice!=2){
        System.out.println("Please enter the new value");
        value = UserInput.getString();}
        else {
            System.out.println("Enter year");
            int year = UserInput.getInt();
            System.out.println("Enter month");
            int month = UserInput.getInt();
            System.out.println("Enter day");
            int day = UserInput.getInt();
            date = LocalDate.of(year, month, day);
        }


        String sql= "";
        switch (choice) {
            case 1 ->
                    sql = "UPDATE driver SET driver_license_number = " + value + "   WHERE customer_id = " + customer_id;
            case 2 ->
                    sql = "UPDATE driver SET driver_since_date =   '" + date + "'   WHERE customer_id = " + customer_id;
            case 3 -> sql = "UPDATE customer SET name = '" + value + "'         WHERE customer_id = " + customer_id;
            case 4 -> sql = "UPDATE customer SET phone = " + value + "          WHERE customer_id = " + customer_id;
            case 5 -> sql = "UPDATE customer SET email = '" + value + "'         WHERE customer_id = " + customer_id;
            case 6 -> sql = "UPDATE address SET address  = '" + value + "'          WHERE customer_id = " + customer_id;
            case 7 -> sql = "UPDATE address SET zip      = " + value + "          WHERE customer_id = " + customer_id;
            case 8 -> sql = "UPDATE address SET city = '" + value + "'          WHERE customer_id = " + customer_id;
            default -> System.out.println("You didn't enter a number between 1-8. Please try again");
        }
        customerDb.updateCustomer(sql);
    }
    public void updateCustomer(int customer_id) throws SQLException{
        System.out.println("Please select what you want to be updated:");
        System.out.println("License Number[1] Driver Since[2] Name[3] Phone[4] Email[5] Address[6] Zip[7] City[8]");
        int choice = UserInput.getInt();
        String value="";
        LocalDate date = null;

        //Hvis vi ikke skal opdatere driver_since
        if (choice!=2){
            System.out.println("Please enter the new value");
            value = UserInput.getString();}
        //Hvis vi skal opdatere driver since
        else {
            date = UserInput.buildDate("");
        }
        String sql= "";
        switch (choice) {
            case 1 ->
                    sql = "UPDATE driver SET driver_license_number = " + value + "  WHERE customer_id = " + customer_id;
            case 2 ->
                    sql = "UPDATE driver SET driver_since_date =   '" + date + "'   WHERE customer_id = " + customer_id;
            case 3 -> sql = "UPDATE customer SET name = '" + value + "'             WHERE customer_id = " + customer_id;
            case 4 -> sql = "UPDATE customer SET phone = " + value + "              WHERE customer_id = " + customer_id;
            case 5 -> sql = "UPDATE customer SET email = '" + value + "'            WHERE customer_id = " + customer_id;
            case 6 -> sql = "UPDATE address SET address  = '" + value + "'          WHERE customer_id = " + customer_id;
            case 7 -> sql = "UPDATE address SET zip      = " + value + "            WHERE customer_id = " + customer_id;
            case 8 -> sql = "UPDATE address SET city = '" + value + "'              WHERE customer_id = " + customer_id;
            default -> System.out.println("You didn't enter a number between 1-8. Please try again");
        }
        customerDb.updateCustomer(sql);
    }
    public int getCustomerId() throws SQLException {
            return customerDb.getLatestCustomerId();
    }
}
