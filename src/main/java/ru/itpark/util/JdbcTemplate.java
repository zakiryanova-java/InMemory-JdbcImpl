package ru.itpark.util;

import ru.itpark.exception.DataIdGenerationException;
import ru.itpark.exception.DataAccessException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class JdbcTemplate {

    public static <T> T execute(String url, String sql, PreparedStatementExecutor<T> executor) {

        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            return executor.execute(statement);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public static int executeUpdate(String url, String sql, PreparedStatementSetter setter) {
        return execute(url, sql, stmt -> {
            setter.set(stmt);
            return stmt.executeUpdate();
        });
    }

    public static int executeUpdateReturningId(String url, String sql, PreparedStatementSetter setter) {
        return execute(url, sql, stmt -> {
            setter.set(stmt);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys();) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            throw new DataIdGenerationException();
        });
    }

    public static <T> List<T> executeQuery(String url, String sql, PreparedStatementSetter setter, RowMapper<T> mapper) {
        return execute(url, sql, stmt -> {
            setter.set(stmt);
            try (ResultSet resultSet = stmt.executeQuery();) {
                List<T> result = new LinkedList<>();
                while (resultSet.next()) {
                    result.add(mapper.map(resultSet));
                }
                return result;
            }
        });
    }
    }
