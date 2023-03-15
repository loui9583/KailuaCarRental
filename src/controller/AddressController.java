package controller;

import db.AddressDb;
import input.UserInput;
import java.sql.SQLException;
public class AddressController {
    AddressDb addressDb = new AddressDb();
    public void addAddress(int customer_id) throws SQLException {
        System.out.println("Enter Address");
        String address = UserInput.getString();
        System.out.println("Enter zipcode");
        int zip = UserInput.getInt();
        System.out.println("Enter city");
        String city = UserInput.getString();
        addressDb.addAddress(address, zip, city, customer_id);
    }
}