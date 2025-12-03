package dao;

import model.Kendaraan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KendaraanDAO {
    private Connection conn;

    public KendaraanDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kendaraan", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kendaraan> getAll() {
        List<Kendaraan> list = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Kendaraan k = new Kendaraan(
                    rs.getInt("id"),
                    rs.getString("merek"),
                    rs.getString("plat"),
                    rs.getString("warna"),
                    rs.getString("tahun")
                );
                list.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Kendaraan k) throws SQLException {
        String sql = "INSERT INTO kendaraan (merek, plat, warna, tahun) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getMerek());
            ps.setString(2, k.getPlat());
            ps.setString(3, k.getWarna());
            ps.setString(4, k.getTahun());
            ps.executeUpdate();
        }
    }

    public void update(Kendaraan k) throws SQLException {
        String sql = "UPDATE kendaraan SET merek=?, plat=?, warna=?, tahun=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getMerek());
            ps.setString(2, k.getPlat());
            ps.setString(3, k.getWarna());
            ps.setString(4, k.getTahun());
            ps.setInt(5, k.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM kendaraan WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
