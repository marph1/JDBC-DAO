package com.desenvolvimento.model.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
                System.out.println("Connection with successfully!");
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
                System.out.println("Connection closed with successfully!");
            } catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }
    private static Properties loadProperties(){
        try{
            FileInputStream fs = new FileInputStream("db.properties");
            Properties propos = new Properties();
            propos.load(fs);
            return propos;
        } catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}
