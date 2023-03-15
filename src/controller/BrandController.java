package controller;

import db.BrandDb;
import input.UserInput;
import java.sql.SQLException;

public class BrandController {
    BrandDb brandDb = new BrandDb();
    public void printAll() throws SQLException {
        brandDb.brandsList();
    }
    public void addBrand() throws SQLException {

        System.out.println("Enter Brand");
        brandDb.addBrand(UserInput.getString());
    }
    public void updateBrand() throws SQLException {


        System.out.println("Please write the brand type ID of the brand you want to update");
        int brand_id = UserInput.getInt();
        System.out.println("Please enter the new name of the brand");
        String brandName = UserInput.getString();
        String sql;
        sql = "UPDATE brand SET brand_name = '" + brandName + "'   WHERE brand_id = " + brand_id;

        brandDb.updateBrand(sql);
    }
    public void removeBrand() {
        try {
            System.out.println("Enter the ID of the brand you want to remove:");

            brandDb.removeBrand(UserInput.getInt());
        } catch (Exception e) {
            System.out.println("Error: Brand ID not found.");
            {
            }
        }
    }
}
