package edu.ijse.layered.fx.dao;

import edu.ijse.layered.fx.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CrudUtil {

    private static PreparedStatement getStatement(String sql, Object... args) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                st.setObject(i + 1, args[i]);
            }
        }
        return st;
    }

    public static ResultSet execute(String sql, Object... args) throws Exception {
        PreparedStatement st = getStatement(sql, args);
        return st.executeQuery();
    }

    public static ResultSet executeQuery(String sql, Object... args) throws Exception {
        return execute(sql, args);
    }

    public static boolean executeUpdate(String sql, Object... args) throws Exception {
        PreparedStatement st = getStatement(sql, args);
        return st.executeUpdate() > 0;
    }
}
