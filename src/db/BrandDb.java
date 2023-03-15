package db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BrandDb {
    public void brandsList() throws SQLException {
        Statement s = Connection.getCon().createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM brand");
        System.out.printf("%-20s %-20s\n\n", "Brand ID", "Brand Name");
        while (rs.next()) {
            System.out.printf("%-20s %-20s\n", rs.getInt("brand_id"), rs.getString("brand_name"));
        }
        Connection.getCon().close();
    }
    public void addBrand(String name) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + name + "')";

        s.execute("INSERT INTO brand (brand_name) VALUES " + inputs);

        System.out.println(" added");

        Connection.getCon().close();
    }
    public void removeBrand(int brand_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute("DELETE FROM brand WHERE brand_id = " + brand_id);

        System.out.println(" brand with id "+ brand_id + " was removed");

        Connection.getCon().close();
    }
    public void updateBrand(String sql) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute(sql);

        System.out.println(" brand type has been updated");

        Connection.getCon().close();
    }

}
