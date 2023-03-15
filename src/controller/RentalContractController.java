package controller;

import db.CustomerDb;
import db.RentalContractDb;
import input.UserInput;

import java.sql.SQLException;
import java.time.LocalDate;

public class RentalContractController {
    RentalContractDb rentalContractDb = new RentalContractDb();
    public void printRentalContract() throws SQLException {
        System.out.println("Show all rental contracts[1]    [2]Show specific rental contract");
        int choice = UserInput.getInt();
        if (choice==1) {
            System.out.println("Order Rental Contracts by:\n[1]Customer ID. [2]Customer Name.  [3]Car ID.  [4]Car Brand   [5]Total Price    [6]Rental Contract ID");
            int chooseOrdering = UserInput.getInt();
            String orderBy;
            switch (chooseOrdering){
                case 1 -> orderBy="customer_id";
                case 2 -> orderBy="customer_name";
                case 3 -> orderBy="car_id";
                case 4 -> orderBy="brand_name";
                case 5 -> orderBy="total_price";
                case 6 -> orderBy="rental_contract_id";

                default -> {System.out.println("You didn't enter a valid value. Default sorting by id."); orderBy="car_id";}
            }
            rentalContractDb.printAllRentalContracts(orderBy);}
        else {
            System.out.println("Enter rental contract id:");
            int rentalContractId = UserInput.getInt();
            rentalContractDb.printSpecificRentalContract(rentalContractId);
        }
    }
    public void addRentalContract() throws SQLException {

        //Tilføjer ny customer eller vælger nuværende customer
        System.out.println("[1]Add new customer\n[2]Use existing customer");
        int choice = UserInput.getInt();
        int customer_id = 0;


        if (choice == 1) {
            CustomerController customerController = new CustomerController();
            customerController.addCustomer();
            // Får sidste tilføjede customers id fra db
            new AddressController().addAddress(customerController.getCustomerId());
            new DriverController().addDriver(customerController.getCustomerId());
        } else {
            System.out.println("Enter customer_id");
            customer_id = UserInput.getInt();
        }

        //Vælger car ud fra car_id
        System.out.println("Enter car_id");
        int car_id = UserInput.getInt();


        //Indtaster hvornår leje-kontrakten starter
        LocalDate startDate = UserInput.buildDate("start");

        //Indtaster hvornår leje-kontrakten slutter
        LocalDate endDate = UserInput.buildDate(("end"));
        while (startDate.isAfter(endDate)){
            System.out.println("End date must be after start date. Please enter a new end date:");
            endDate= UserInput.buildDate("");
        }


        //Indtaster max km bilen må køre
        System.out.println("enter max km");
        int max_km = UserInput.getInt();

        //Til kaldes metoden fra rentalContract, hvor vi tilføjer en ny rental contract til databasen med de data vi har indtastet
        rentalContractDb.addRentalContract(customer_id, car_id, startDate, endDate, max_km);
    }
    public void updateRentalContract() throws SQLException {

        System.out.println("Please write the ID of the rental contract you want to update");
        int rental_contract_id = UserInput.getInt();

        System.out.println("Please select what you want to be updated:\n" +
                           "Update Customer Info[1] Update End-Time[2]");
        int choice = UserInput.getInt();

        //Hvis brugeren har valgt "1" kalder vi updateCustomer metoden
        if (choice == 1) new CustomerController().updateCustomer(new RentalContractDb().getCustomerId(rental_contract_id));

        //Hvis brugeren ikke har valgt, skal vi opdatere slut-tiden for lejeaftalen.
        else {
            LocalDate endDate = UserInput.buildDate("end");
            while (rentalContractDb.getStartDate(rental_contract_id).isAfter(endDate))
            {
                System.out.println("Start date can't be after end date. Please enter the date again");
                endDate=UserInput.buildDate("end");
            }
            String sql = "UPDATE rental_contract SET end_time = '" +   endDate
                    + "' WHERE rental_contract_id = " + rental_contract_id;

            rentalContractDb.updateRentalContract(sql);
        }
    }
    public void removeRentalContract(){
        try {
            System.out.println("Enter the ID of the rental contract you want to remove:");

            rentalContractDb.removeRentalContract(UserInput.getInt());
        } catch (Exception e) {
            System.out.println("Error: Rental contract ID not found.");
            {
            }
        }
    }
}
