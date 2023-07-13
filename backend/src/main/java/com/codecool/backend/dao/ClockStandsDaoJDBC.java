package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public ClockStandsDto getClockStandByDate(int year, Month month) {
        //TODO
        return null;
    }

    @Override
    public ClockStandsDto getClockStandById(int id) {
        //TODO
        return null;
    }
}
