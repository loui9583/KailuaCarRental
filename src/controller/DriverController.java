package controller;

import db.DriverDb;
import input.UserInput;


import java.time.LocalDate;
import java.sql.SQLException;

public class DriverController {
    DriverDb driverDb = new DriverDb();

    public void addDriver(int customer_id) throws SQLException{
        System.out.println("Enter License Number");
        int licenseNumber = UserInput.getInt();
        LocalDate driverSince = UserInput.buildDate("");
        driverDb.addDriver(licenseNumber,driverSince,customer_id);
    }
}
