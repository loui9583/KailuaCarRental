package db;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDb {

    public void addCustomer(String name, int phone, String email) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + name + "','" + phone + "','" + email + "')";

        s.execute("INSERT INTO customer (name, phone, email) VALUES " + inputs);

        System.out.println(" added");

        Connection.getCon().close();
    }
    public void removeCustomer(int customer_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute("DELETE FROM customer WHERE customer_id = " + customer_id);

        System.out.println(" customer with id "+ customer_id + " was removed");

        Connection.getCon().close();
    }
    public void updateCustomer(String sql) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute(sql);

        System.out.println(" customer has been updated");

        Connection.getCon().close();
    }
    public void showAllCustomersInfo(String orderBy) throws SQLException {
        String sql = "SELECT c.customer_id, c.name, c.phone, c.email, a.address, a.zip, a.city, d.driver_license_number, d.driver_since_date " +
                     "FROM customer c " +
                     "JOIN address a ON c.customer_id = a.customer_id " +
                     "JOIN driver d  ON c.customer_id = d.customer_id " +
                     "ORDER BY " + orderBy + " ASC";
        executeAndPrintCustomers(sql);

    }
    public void showSpecificCustomerInfo(int customer_id) throws SQLException {
        String sql =
                "SELECT c.customer_id, c.name, c.phone, c.email, a.address, a.zip, a.city, d.driver_license_number, d.driver_since_date " +
                        "FROM customer c " +
                        "JOIN address a ON c.customer_id = a.customer_id " +
                        "JOIN driver d  ON c.customer_id = d.customer_id " +
                        "WHERE c.customer_id = "  + customer_id;

        executeAndPrintCustomers(sql);
    }
    private void executeAndPrintCustomers(String sql) throws SQLException {
        Statement s = Connection.getCon().createStatement();

        ResultSet rs = s.executeQuery(sql);

        System.out.printf("%-11s  %-20s  %-15s  %-28s  %-30s  %-10s  %-20s  %-25s  %-15s\n\n",
                "Customer ID", "Name", "Phone", "Email", "Address", "Zip", "City", "License Number", "Driver Since");

        while (rs.next()) {
            System.out.printf("%-11s  %-20s  %-15s  %-28s  %-30s  %-10s  %-20s  %-25s  %-15s\n",
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("zip"),
                    rs.getString("city"),
                    rs.getString("driver_license_number"),
                    rs.getString("driver_since_date"));
        }
        Connection.getCon().close();
    }
    public int getLatestCustomerId() throws SQLException {
        Statement statement = Connection.getCon().createStatement();
        ResultSet rs = statement.executeQuery("SELECT MAX(customer_id) FROM customer");
        int customer_id=0;

        while (rs.next()) {
            customer_id = rs.getInt("MAX(customer_id)");
        }
        rs.close();
        return customer_id;
    }
}
