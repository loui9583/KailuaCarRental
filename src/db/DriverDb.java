package db;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DriverDb {

    public void addDriver(int driver_license_number, LocalDate driver_since_date, int customer_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + driver_license_number + "','" + driver_since_date + "','" + customer_id + "')";

        s.execute("INSERT INTO driver (driver_license_number,driver_since_date, customer_id) VALUES " + inputs);

        System.out.println(" added");

        Connection.getCon().close();
    }
}
