package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.database.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ClockStandsDaoJDBC implements ClockStandsDao {
    private final Database database;

    public ClockStandsDaoJDBC(Database database) {
        this.database = database;
    }

    @Override
    public List<ClockStandsDto> getAllClockStands() throws SQLException {
        String query = "SELECT * FROM clockstands;";
        Connection connection = database.getConnection();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            return prepareClockStandDtos(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<ClockStandsDto> getClockStandByMonthAndYear(int year, Month month) throws SQLException {
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
            return prepareClockStandDtos(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private static List<ClockStandsDto> prepareClockStandDtos(ResultSet rs) throws SQLException {
        List<ClockStandsDto> clockStandsDtos = new ArrayList<>();
        while (rs.next()) {
            int ID = rs.getInt("id");
            LocalDateTime localDate = rs.getTimestamp("date").toLocalDateTime();
            double warmwaterstand = rs.getDouble("warmwaterstand");
            double coldwaterstand = rs.getDouble("coldwaterstand");
            int electricityStand = rs.getInt("electricitystand");
            int warmingBill = rs.getInt("warmingbill");
            int gasbill = rs.getInt("gasbill");
            clockStandsDtos.add(new ClockStandsDto(ID, localDate, warmwaterstand, coldwaterstand, electricityStand, warmingBill, gasbill));
            System.out.println(ID + ", " + localDate + ", " + warmwaterstand +
                    ", " + coldwaterstand + ", " + electricityStand + ", " + warmingBill + ", " + gasbill);
        }
        return clockStandsDtos;
    }

    @Override
    public boolean postClockStand(ClockStands clockStands) throws SQLException {
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
        return false;
    }
}
