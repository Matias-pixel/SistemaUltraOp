package org.teambd.sgp.dao;

import org.teambd.sgp.models.Brand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBrand implements DAO<Brand> {

    private MyConnection connection;

    public DAOBrand(MyConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<Brand> getAll() throws SQLException {
        String sql = "SELECT id, name FROM brand;";
        List<Brand> brands;

        ResultSet rs = connection
                .getConnection()
                .createStatement()
                .executeQuery(sql);
        if ( rs.next() ) {
            brands = new ArrayList<>();
            do {
                brands.add(new Brand(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            } while (rs.next());
        } else {
            brands = null;
        }

        return brands;
    }

    @Override
    public Brand getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM brand WHERE id = ? ;";
        Brand brand;

        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if ( rs.next() ) {
            brand = new Brand(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        } else {
            brand = null;
        }

        return brand;
    }

    @Override
    public int insert(Brand brand) throws SQLException {
        String sql = "INSERT INTO brand (name) VALUES (?);";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, brand.getName());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int update(Brand brand) throws SQLException {
        String sql = "UPDATE brand SET name = ? WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setString(1, brand.getName());
        ps.setInt(2, brand.getId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM brand WHERE id = ? ;";
        PreparedStatement ps = connection
                .getConnection()
                .prepareStatement(sql);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
