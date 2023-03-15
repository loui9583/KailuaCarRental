package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class RentalContractDb {

    public void addRentalContract(int customer_id, int car_id, LocalDate start_time, LocalDate end_time, int max_km) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        String inputs = "('" + customer_id + "','" + car_id + "','" + start_time + "','"+ end_time+"','"+ max_km + "')";

        try {
            s.execute("INSERT INTO rental_contract (customer_id,car_id,start_time, end_time, max_km) VALUES " + inputs);
            System.out.println(" added");
        }

        catch (SQLException sqlException){
            System.out.println("Could not add rental order. Car or customer doesn't exist.");
        }

        Connection.getCon().close();
    }
    public void removeRentalContract(int rental_contract_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute("DELETE FROM rental_contract WHERE rental_contract_id = " + rental_contract_id);

        System.out.println(" rental contract with id "+ rental_contract_id + " was removed");

        Connection.getCon().close();
    }
    public void updateRentalContract(String sql) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute(sql);

        System.out.println(" Rental Contract has been updated");

        Connection.getCon().close();
    }
    public int getCustomerId(int rental_contract_id) throws SQLException {
        Statement statement = Connection.getCon().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT customer_id FROM rental_contract WHERE rental_contract_id = " + rental_contract_id);
        int customer_id = -1;

        if (resultSet.next()) {
            customer_id = resultSet.getInt("customer_id");
        }

        resultSet.close();
        statement.close();
        Connection.getCon().close();

        if (customer_id == -1) {
            throw new SQLException("No customer ID found for rental contract ID " + rental_contract_id);
        }

        return customer_id;
    }
    public void printAllRentalContracts(String orderBy) throws SQLException {
        String sql =
            "SELECT rc.rental_contract_id, rc.customer_id, c.name AS customer_name, c.phone, c.email, d.driver_license_number, rc.car_id, car.model_name, brand.brand_name, rc.start_time, rc.end_time, rc.MAX_KM, (DATEDIFF(rc.end_time, rc.start_time) + 1) * car.price_per_day AS total_price FROM rental_contract rc " +
            "JOIN customer c ON rc.customer_id = c.customer_id " +
            "JOIN driver d ON c.customer_id = d.customer_id " +
            "JOIN car ON rc.car_id = car.car_id " +
            "JOIN brand ON car.brand_id = brand.brand_id " +
            "ORDER BY " + orderBy + " ASC";

        printSql(sql);
    }
    public void printSpecificRentalContract(int rentalContractId) throws SQLException {
        String sql =
                "SELECT rc.rental_contract_id, rc.customer_id, c.name AS customer_name, c.phone, c.email, d.driver_license_number, rc.car_id, car.model_name, brand.brand_name, rc.start_time, rc.end_time, rc.MAX_KM, (DATEDIFF(rc.end_time, rc.start_time) + 1) * car.price_per_day AS total_price FROM rental_contract rc " +
                        "JOIN customer c ON rc.customer_id = c.customer_id " +
                        "LEFT JOIN driver d ON c.customer_id = d.customer_id " +
                        "JOIN car ON rc.car_id = car.car_id " +
                        "JOIN brand ON car.brand_id = brand.brand_id " +
                        "WHERE rental_contract_id = " + rentalContractId;

        printSql(sql);
    }
    private void printSql(String sql) throws SQLException {
        Statement s = Connection.getCon().createStatement();

        ResultSet rs = s.executeQuery(sql);
        System.out.printf("%-15s  %-15s  %-25s  %-18s  %-30s  %-20s  %-10s  %-20s  %-20s %-15s  %-15s %-15s %-20s\n\n",
                "Contract ID", "Customer ID", "Name", "Phone", "Email", "License Nr", "Car id", "Model", "Brand", "Start time", "End time", "MAX KM", "Price" );

        while (rs.next()) {
            System.out.printf("%-15s  %-15s  %-25s  %-18s  %-30s  %-20s  %-10s  %-20s  %-20s %-15s  %-15s %-15s  %-20s\n",
                    rs.getInt("rental_contract_id"),
                    rs.getInt("customer_id"),
                    rs.getString("customer_name"),
                    rs.getInt("phone"),
                    rs.getString("email"),
                    rs.getInt("driver_license_number"),
                    rs.getInt("car_id"),
                    rs.getString("model_name"),
                    rs.getString("brand_name"),
                    rs.getString("start_time"),
                    rs.getString("end_time"),
                    rs.getInt("MAX_KM"),
                    rs.getInt("total_price"));
        }
        Connection.getCon().close();
    }
    public LocalDate getStartDate(int rental_contract_id) throws SQLException {
        Statement statement = Connection.getCon().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT start_time FROM rental_contract WHERE rental_contract_id = " + rental_contract_id);
    
        String date = null;
        
        while (resultSet.next()) {
            date = resultSet.getString("start_time");
        }

        resultSet.close();
        statement.close();
        Connection.getCon().close();

        String[] dateSplit = date.split("-");

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);

        LocalDate startDate = LocalDate.of(year, month, day);

        
        return startDate;
    }

}
