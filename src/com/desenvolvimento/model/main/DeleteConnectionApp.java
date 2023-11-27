package com.desenvolvimento.model.main;

import com.desenvolvimento.model.db.DB;
import com.desenvolvimento.model.db.DBIntegrityException;

import java.sql.*;

// truncate table -->
//SET FOREIGN_KEY_CHECKS = 0;
//        TRUNCATE table department;
//        SET FOREIGN_KEY_CHECKS = 1;

public class DeleteConnectionApp {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM department "
                            + "WHERE "
                            + "Id = ?");

            st.setInt(1, 2); // -> exclusao do id aqui

            int rowsAffected = st.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();

        }
    }
}
