package com.codecool.backend.dao;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.database.Database;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClockStandsDaoJDBC implements ClockStandsDao {
    private final Database database;

    public ClockStandsDaoJDBC(Database database) throws SQLException {
        this.database = database;
        initTable();
        log.info("Database connection settings: {}", database);
    }

    private void initTable() throws SQLException {
        String query = """
                create table if not exists clockstands
                (
                    id               serial
                        primary key,
                    date             timestamp default CURRENT_TIMESTAMP,
                    warmwaterstand   numeric(6, 3),
                    coldwaterstand   numeric(6, 3),
                    electricitystand integer,
                    warmingbill      integer,
                    gasbill          integer
                );""";
        Connection connection = database.getConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<ClockStands> getAllClockStands() throws SQLException {
        String query = "SELECT * FROM clockstands;";
        Connection connection = database.getConnection();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            return prepareClockStands(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<ClockStands> getClockStandByMonthAndYear(int year, Month month) throws SQLException {
        String query = """
                SELECT *
                FROM clockstands
                WHERE extract(MONTH FROM date) = ?
                  AND extract(YEAR FROM date) = ?;""";
        Connection connection = database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, month.getValue());
            preparedStatement.setInt(2, year);
            ResultSet rs = preparedStatement.executeQuery();
            return prepareClockStands(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private static List<ClockStands> prepareClockStands(ResultSet rs) throws SQLException {
        List<ClockStands> clockStands = new ArrayList<>();
        while (rs.next()) {
            int ID = rs.getInt("id");
            LocalDateTime localDate = rs.getTimestamp("date").toLocalDateTime();
            double warmwaterstand = rs.getDouble("warmwaterstand");
            double coldwaterstand = rs.getDouble("coldwaterstand");
            int electricityStand = rs.getInt("electricitystand");
            int warmingBill = rs.getInt("warmingbill");
            clockStands.add(ClockStands.of(localDate, warmwaterstand, coldwaterstand, electricityStand, warmingBill));
            System.out.println("Requested clockstand:");
            System.out.println(ID + ", " + localDate + ", " + warmwaterstand +
                    ", " + coldwaterstand + ", " + electricityStand + ", " + warmingBill);
        }
        return clockStands;
    }

    @Override
    public boolean postClockStand(ClockStands clockStands) throws SQLException {
        log.info("Attempting to save new clockstand: {}", clockStands);
        if (getClockStandByMonthAndYear(LocalDate.now().getYear(), LocalDate.now().getMonth()).isEmpty()) {
            String query = """
                    INSERT INTO clockstands VALUES (DEFAULT, DEFAULT, ?, ?, ?, ?, ?);
                    """;
            Connection connection = database.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, clockStands.getWarmWaterStand());
                preparedStatement.setDouble(2, clockStands.getColdWaterStand());
                preparedStatement.setInt(3, clockStands.getElectricityStand());
                preparedStatement.setInt(4, clockStands.getWarmingBill());
                preparedStatement.setInt(5, clockStands.getGasBill());
                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                return true;
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
        log.error("Clockstand saving failed, probably due to already having an entry for the current month!");
        return false;
    }

    @Override
    public void deleteClockStandByYearAndMonth(int year, Month month) throws SQLException {
        String query = """
                DELETE FROM clockstands
                    WHERE to_char(date, 'YYYY') = ?
                    AND to_char(date, 'MM') = ?;
                    """;
        String monthString = "0";
        if (Integer.toString(month.getValue()).length() == 1) {
            monthString += Integer.toString(month.getValue());
        } else {
            monthString = Integer.toString(month.getValue());
        }
        Connection connection = database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, Integer.toString(year));
            preparedStatement.setString(2, monthString);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
