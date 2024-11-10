package PERTEMUAN_7_Membership.src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_membership";
    private final static String DB_USER = "root";  // Sesuaikan dengan user MySQL Anda
    private final static String DB_PASS = "";  // Sesuaikan dengan password MySQL Anda

    private static MySqlConnection instance;

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }    
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            // Memastikan driver MySQL sudah dimuat
            Class.forName("com.mysql.cj.jdbc.Driver");  
            // Mencoba untuk menghubungkan ke database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            // Menangani jika class driver tidak ditemukan
            System.out.println("MySQL JDBC Driver tidak ditemukan. Pastikan driver sudah terpasang.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Menangani kesalahan dalam menghubungkan ke database
            System.out.println("Koneksi database gagal.");
            e.printStackTrace();
        }
        return connection;
    }
}
