package com.codecool.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String url;
    private final String username;
    private final String password;

    public static Database withSettings(String url, String username, String password) {
        return new Database(url, username, password);
    }

    private Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.err.println("Could not create database connection.");
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns the basic information about the setup of the DB connection in the following format:
     * [DB URL: url, DB Username: username]
     */
    @Override
    public String toString() {
        return "[DB URL: " + url + ", DB Username: " + username + "]";
    }
}
