package controller;
import db.FuelTypeDb;
import input.UserInput;

import java.sql.SQLException;
public class FuelTypeController {
    FuelTypeDb fuel_typeDb = new FuelTypeDb();
    public void printAll() throws SQLException {
       fuel_typeDb.fuel_typesList();
        }
    public void addFuelType() throws SQLException {

        System.out.println("Enter Fuel Type Name");
        String fuelTypeName = UserInput.getString();
        fuel_typeDb.addFuelType(fuelTypeName);
    }
    public void updateFuelType() throws SQLException {


        System.out.println("Please write the fuel type ID of the fuel type you want to update");
        int fuel_type_id = UserInput.getInt();
        System.out.println("Please enter the new name of the fuel type");
        String fuelTypeName = UserInput.getString();
        String sql;
        sql = "UPDATE fuel_type SET fuel_type_name = '" + fuelTypeName + "'   WHERE fuel_type_id = " + fuel_type_id;

        fuel_typeDb.updateFuelType(sql);
    }
    public void removeFuelType(){
        try {
            System.out.println("Enter the ID of the fuel type you want to remove:");

            fuel_typeDb.removeFuelType(UserInput.getInt());
        } catch (Exception e) {
            System.out.println("Error: Fuel type ID not found.");
            {
            }
        }
    }
    }
