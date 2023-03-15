package controller;

import db.CarDb;
import input.UserInput;

import java.sql.SQLException;
public class CarController {

    CarDb carDb = new CarDb();

    public void printAll() throws SQLException {
        System.out.println("Show All Cars[1]    Show Specific Car[2]");
        int      choice = UserInput.getInt();
        if      (choice==1) {
            System.out.println("Order cars by:\n[1]Car ID.  [2]Brand.  [3]Category.   [4]Fuel Type   [5]Price per day");
            int chooseOrdering = UserInput.getInt();
            String orderBy="";
            switch (chooseOrdering){
                case 1 -> orderBy="car_id";
                case 2 -> orderBy="brand_name";
                case 3 -> orderBy="category_name";
                case 4 -> orderBy="fuel_type_name";
                case 5 -> orderBy="price_per_day";
                default -> {System.out.println("You didn't enter a value between 1-4. Default sorting by car id."); orderBy="car_id";}
            }

            carDb.showAllCars(orderBy);}
        else    {
            System.out.println("Enter car id:");
            carDb.showSpecificCar(UserInput.getInt());
        }
    }
    public void addCar() throws SQLException {
        System.out.println("Enter model name");
        String modelName = UserInput.getString();
        System.out.println("Enter fuel type ID");
        int fuelTypeId = UserInput.getInt();
        System.out.println("Enter category ID");
        int categoryId = UserInput.getInt();
        System.out.println("Enter price per day");
        int pricePerDay = UserInput.getInt();
        System.out.println("Enter brand id");
        int brandId = UserInput.getInt();

        carDb.addCar(modelName, fuelTypeId, categoryId, pricePerDay, brandId);
    }
    public void updateCar() throws SQLException{
        System.out.println("Please write the ID of the car you want to update");
        int car_id = UserInput.getInt();
        System.out.println("Please select what you want to be updated:");
        System.out.println("Model[1] Fuel Type[2] Category[3] Price per day[4] Brand[5]");
        int choice = UserInput.getInt();
        switch (choice) {
            case 1 -> System.out.println("Enter new model name:");
            case 2 -> System.out.println("Enter fuel_type_id:");
            case 3 -> System.out.println("Enter category_id:");
            case 4 -> System.out.println("Enter new price:");
            case 5 -> System.out.println("Enter brand_id:");
        }
        String value = UserInput.getString();
        String sql= "";
        switch (choice) {
            case 1 -> sql = "UPDATE car SET model_name = '" + value + "' WHERE car_id = " + car_id;
            case 2 -> sql = "UPDATE car SET fuel_type_id =   " + value + "  WHERE car_id = " + car_id;
            case 3 -> sql = "UPDATE car SET category_id = " + value + "  WHERE car_id = " + car_id;
            case 4 -> sql = "UPDATE car SET price_per_day = " + value + "  WHERE car_id = " + car_id;
            case 5 -> sql = "UPDATE car SET brand_id = " + value + "  WHERE car_id = " + car_id;
            default -> System.out.println("You didn't enter a number between 1-8. Please try again");
        }
        System.out.println(sql);
        carDb.updateCar(sql);
    }
    public void removeCar(){
        try {
            System.out.println("Enter the ID of the car you want to remove:");

            carDb.removeCar(UserInput.getInt());
        } catch (Exception e) {
            System.out.println("Error: Car ID not found, or car has an active rental contract.");
            {
            }
        }
    }
}
