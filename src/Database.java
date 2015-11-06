import java.sql.*;

public class Database {
    private final String connectionUrl = "jdbc:mysql://localhost/test";
    private Connection sqlConnection;

    public Database() {
        try {
            sqlConnection = DriverManager.getConnection(connectionUrl, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertOrder(Order order) {
        try {
            Statement insertQuery = sqlConnection.createStatement();
            insertQuery.execute("insert into orders values(" + "NULL," + " " +
                    "'" + order.getBookUrl() + "', '" + order.getUsername() + "'" + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBookPrice(String url) throws SQLException {
        Statement priceQuery = sqlConnection.createStatement();
        priceQuery.execute("select (price) from books where url = '" + url + "'");
        ResultSet rs = priceQuery.getResultSet();
        return rs.getInt(0);
    }
}
