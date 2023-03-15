package db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarDb {
    public void addCar(String modelName, int fuelTypeId, int categoryId, int pricePerDay, int brandId) throws SQLException {
try {
    Statement s = Connection.getCon().createStatement();

    String inputs = "('" + modelName + "','" + fuelTypeId + "','" + categoryId + "','" + pricePerDay + "','" + brandId + "')";

    s.execute("INSERT INTO car (model_name,fuel_type_id,category_id,price_per_day, brand_id) VALUES " + inputs);

    System.out.println(" added");

    Connection.getCon().close();
}
catch (SQLException sqlException){
    System.out.println("Error: Failed to add car. Either\n1: Category doesn't exist.\n2:Brand doesnt' exist.\n3: Fuel type doesn't exist ");
}
    }
    public void updateCar(String sql) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute(sql);

        System.out.println(" car has been updated");

        Connection.getCon().close();
    }
    public void removeCar(int car_id) throws SQLException {

        Statement s = Connection.getCon().createStatement();

        s.execute("DELETE FROM car WHERE car_id = " + car_id);

        System.out.println(" car with id "+ car_id + " was removed");

        Connection.getCon().close();
    }
    public void showAllCars (String orderBy) throws SQLException{
        String sql = "SELECT car_id, model_name, fuel_type_name, price_per_day, brand_name, category_name FROM car  " +
                     "JOIN fuel_type ON car.fuel_type_id = fuel_type.fuel_type_id  " +
                     "JOIN brand ON car.brand_id = brand.brand_id " +
                     "JOIN category ON car.category_id = category.category_id " +
                     "ORDER BY " + orderBy + " ASC";
        executeAndPrintCar(sql);
    }
    public void showSpecificCar (int carId) throws SQLException{
        String sql = "SELECT car_id, model_name, fuel_type_name, price_per_day, brand_name, category_name FROM car  " +
                     "JOIN fuel_type ON car.fuel_type_id = fuel_type.fuel_type_id  " +
                     "JOIN brand ON car.brand_id = brand.brand_id " +
                     "JOIN category ON car.category_id = category.category_id " +
                     "WHERE car_id = " + carId;
        executeAndPrintCar(sql);
    }
    private void executeAndPrintCar(String sql) throws SQLException {
        Statement s = Connection.getCon().createStatement();

        ResultSet rs = s.executeQuery(sql);

        System.out.printf("%-10s  %-20s  %-20s  %-20s  %-20s  %-20s \n\n",
                "Car ID", "model", "fuel type", "price per day", "brand", "category");
        while (rs.next()) {
            System.out.printf("%-10s  %-20s  %-20s  %-20s  %-20s  %-20s\n",
                    rs.getInt("car_id"),
                    rs.getString("model_name"),
                    rs.getString("fuel_type_name"),
                    rs.getInt("price_per_day"),
                    rs.getString("brand_name"),
                    rs.getString("category_name"));
        }
        Connection.getCon().close();
    }
}






