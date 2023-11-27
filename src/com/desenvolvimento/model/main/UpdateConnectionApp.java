package com.desenvolvimento.model.main;

import com.desenvolvimento.model.db.DB;
import com.desenvolvimento.model.db.DBException;
import com.desenvolvimento.model.db.DBIntegrityException;

import java.sql.*;

public class UpdateConnectionApp {
    public static void main(String[] args) {

        Connection conn;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)");

            st.setDouble(1, 200.0); // -> exclusao do id aqui
            st.setInt(2,2);

            int rowsAffected = st.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
