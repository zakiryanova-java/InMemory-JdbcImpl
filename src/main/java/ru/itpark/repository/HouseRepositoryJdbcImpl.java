package ru.itpark.repository;

import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;
import java.util.*;

public class HouseRepositoryJdbcImpl implements HouseRepository {
    private final String url;

    public HouseRepositoryJdbcImpl(String url) {
        this.url = url;
    }



    @Override
    public List <House> getByDistrict(String district) {
        String sql = "SELECT * FROM houses WHERE district=?";
        return JdbcTemplate.executeQuery(url, sql, stmt -> stmt.setString(1, district), resultSet ->
                new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                ));

    }
    @Override
    public List<House> sortByPriceASC() {
        String sql = "SELECT * FROM houses ORDER BY price ASC";
        return JdbcTemplate.executeQuery(url, sql, stmt -> {
        }, resultSet ->
                new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")));
    }

    @Override
    public List<House> sortByPriceDESC() {
        String sql = "SELECT * FROM houses ORDER BY price DESC";
        return JdbcTemplate.executeQuery(url, sql, stmt -> {
        }, resultSet ->
                new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")));
    }


    @Override
    public House save(House house) {
        return house.getId() == 0 ? insert(house) : update(house);

    }
    private House insert(House house) {
        String sql = "INSERT INTO houses (price, district, underground) VALUES (?,?,?);";
        final int id = JdbcTemplate.executeUpdateReturningId(url, sql, stmt -> {
            stmt.setInt(1, house.getPrice());
            stmt.setString(2, house.getDistrict());
            stmt.setString(3, house.getUnderground());
        });

        house.setId(id);
        return house;
    }

    private House update(House house) {
        String sql = "UPDATE houses SET price=?, district=?, underground=? WHERE id=?;";
        JdbcTemplate.executeUpdate(url, sql, stmt -> {
            stmt.setInt(1, house.getPrice());
            stmt.setString(2, house.getDistrict());
            stmt.setString(3, house.getUnderground());
            stmt.setInt(4, house.getId());
        });
        return house;
    }
}
