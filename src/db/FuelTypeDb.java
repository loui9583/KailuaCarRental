package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuelTypeDb {
    public void fuel_typesList() throws SQLException {
        Statement s = Connection.getCon().createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM fuel_type");
        System.out.printf("%-20s %-20s\n", "Fuel Type ID", "Fuel Type Name");
        while (rs.next()) {
            System.out.printf("%-20s %-20s\n",rs.getInt("fuel_type_id"),rs.getString("fuel_type_name"));
        }
        Connection.getCon().close();
    }

    public void addFuelType(String fuelTypeName) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + fuelTypeName + "')";

        s.execute("INSERT INTO fuel_type (fuel_type_name) VALUES " + inputs);

        System.out.println(" added");

        Connection.getCon().close();
    }
    public void removeFuelType(int fuel_type_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute("DELETE FROM fuel_type WHERE fuel_type_id = " + fuel_type_id);

        System.out.println(" fuel type with id "+ fuel_type_id + " was removed");

        Connection.getCon().close();
    }
    public void updateFuelType(String sql) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute(sql);

        System.out.println(" fuel type has been updated");

        Connection.getCon().close();
    }
}
