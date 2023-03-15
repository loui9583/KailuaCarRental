package db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryDb {
    public void categoriesList() throws SQLException {


        Statement s = Connection.getCon().createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM category");

        System.out.printf("%-20s %-20s\n","Category ID", "Category Name");

        while (rs.next()) {

            System.out.printf("%-20s %-20s\n", rs.getInt("category_id"), rs.getString("category_name"));

        }
        Connection.getCon().close();
    }
    public void addCategory(String name) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + name + "')";

        s.execute("INSERT INTO category (category_name) VALUES " + inputs);

        System.out.println(" added");

        Connection.getCon().close();
    }
    public void removeCategory(int category_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute("DELETE FROM category WHERE category_id = " + category_id);

        System.out.println(" category with id "+ category_id + " was removed");

        Connection.getCon().close();
    }
    public void updateCategory(String sql) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute(sql);

        System.out.println(" category type has been updated");

        Connection.getCon().close();
    }

}
