import controller.*;
import input.UserInput;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
//Hej
    public static void main(String[] args) throws SQLException {

       new Main().menu();

    }

    public void menu() throws SQLException {

        AddressController addressController = new AddressController();
        BrandController brandController = new BrandController();
        CarController carController = new CarController();
        CategoryController categoryController = new CategoryController();
        CustomerController customerController = new CustomerController();
        DriverController driverController = new DriverController();
        FuelTypeController fuelTypeController = new FuelTypeController();
        RentalContractController rentalContractController = new RentalContractController();

        String menu =
                ("""
                         [1]Add Customer        |   [2]Delete Customer       |  [3]Update Customer         |  [4]Show Customers

                         [5]Add Car             |   [6]Delete Car            |  [7]Update Car              |  [8]Show Cars

                         [9]Add Rental Contract |  [10]Delete Rental Contract| [11]Update Rental Contract  | [12]Show Rental Contracts

                        [13]Add Fuel Type       |  [14]Delete Fuel Type      | [15]Update Fuel Type        | [16]Show Fuel Type

                        [17]Add Brand           |  [18]Delete Brand          | [19]Update Brand            | [20]Show Brands

                        [21]Add Category        |  [22]Delete Category       | [23]Update Category         | [24]Show Categories
                        
                        [25]Exit""");


        while (true) {
            System.out.println("Press Enter to show the menu");
            switch (UserInput.getString()) {
                case "1" -> {
                    //Add Customer
                    customerController.addCustomer();
                    addressController.addAddress(customerController.getCustomerId());
                    driverController.addDriver(customerController.getCustomerId());
                }
                case "2" ->
                    //Delete Customer
                        customerController.removeCustomer();
                case "3" ->
                    //Update Customer
                        customerController.updateCustomer();
                case "4" ->
                    //Show Customer
                        customerController.showCustomerInfo();
                case "5" -> {
                    //add Car
                    carController.addCar();
                }
                case "6" ->
                    //Delete car
                        carController.removeCar();
                case "7" ->
                    //Update car
                        carController.updateCar();
                case "8" ->
                    //Show Car
                        carController.printAll();
                case "9" ->
                    //Add Rental Contract
                        rentalContractController.addRentalContract();
                case "10" ->
                    //delete Rental Contract
                        rentalContractController.removeRentalContract();
                case "11" ->
                    //Update Rental Contract
                        rentalContractController.updateRentalContract();
                case "12" ->
                    //Show Rental Contract
                        rentalContractController.printRentalContract();
                case "13" ->
                    // add Fuel Type
                        fuelTypeController.addFuelType();
                case "14" ->
                    //delete Fuel type
                        fuelTypeController.removeFuelType();
                case "15" ->
                    // update fuel type
                        fuelTypeController.updateFuelType();
                case "16" ->
                    // Show fuel type
                        fuelTypeController.printAll();
                case "17" ->
                    // add brand
                        brandController.addBrand();
                case "18" ->
                    // delete brand
                        brandController.removeBrand();
                case "19" ->
                    // update brand
                        brandController.updateBrand();
                case "20" ->
                    // show brand
                        brandController.printAll();
                case "21" ->
                    //add category
                        categoryController.addCategory();
                case "22" ->
                    //delete category
                        categoryController.removeBrand();
                case "23" ->
                    //update category
                        categoryController.updateCategory();
                case "24" ->
                    //show category
                        categoryController.printAll();
                case "25" -> {
                    //exit
                    return;}
                default -> System.out.println(menu);
            }
        }
    }

}


