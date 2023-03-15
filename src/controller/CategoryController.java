package controller;

import db.CategoryDb;
import input.UserInput;

import java.sql.SQLException;


public class CategoryController {
    CategoryDb categoryDb = new CategoryDb();

    public void printAll() throws SQLException {
        categoryDb.categoriesList();
    }

    public void addCategory() throws SQLException {


        System.out.println("Enter Category");
        String name = UserInput.getString();
        categoryDb.addCategory(name);
    }
    public void updateCategory() throws SQLException {


        System.out.println("Please write the category type ID of the category you want to update");
        int category_id = UserInput.getInt();
        System.out.println("Please enter the new name of the category");
        String categoryName = UserInput.getString();
        String sql;
        sql = "UPDATE category SET category_name = '" + categoryName + "'   WHERE category_id = " + category_id;

        categoryDb.updateCategory(sql);
    }

    public void removeBrand() {
        try {
            System.out.println("Enter the ID of the category you want to remove:");

            categoryDb.removeCategory(UserInput.getInt());
        } catch (Exception e) {
            System.out.println("Error: Category ID not found.");
            {
            }
        }
    }
}
