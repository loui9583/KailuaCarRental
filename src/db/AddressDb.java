package db;


import java.sql.SQLException;
import java.sql.Statement;

public class AddressDb {
    public void addAddress(String address, int zip, String city, int customer_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + address + "','" + zip + "','" + city + "','" + customer_id + "')";

        s.execute("INSERT INTO address (address,zip,city, customer_id) VALUES " + inputs);

        System.out.println(" added");

        Connection.getCon().close();
    }
}
