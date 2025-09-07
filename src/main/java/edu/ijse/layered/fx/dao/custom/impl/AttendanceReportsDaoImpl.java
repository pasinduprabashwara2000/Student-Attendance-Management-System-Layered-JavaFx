package edu.ijse.layered.fx.dao.custom.impl;

import edu.ijse.layered.fx.dao.custom.AttendanceReportsDao;
import edu.ijse.layered.fx.db.DBConnection;
import edu.ijse.layered.fx.dto.AttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AttendanceReportsDaoImpl implements AttendanceReportsDao {

    @Override
    public ArrayList<AttendanceDto> searchByDate(String startDate, String endDate) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM attendance WHERE date BETWEEN ? AND ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,startDate);
        st.setString(2,endDate);

        ResultSet rst = st.executeQuery();

        ArrayList <AttendanceDto> attendanceDtos = new ArrayList<>();
        while (rst.next()){
            attendanceDtos.add(new AttendanceDto(
                    rst.getInt("attendance_id"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("lecture_id"),
                    rst.getString("student_name"),
                    rst.getString("course_name"),
                    rst.getString("subject_name"),
                    rst.getString("status")
            ));
        }

        return attendanceDtos;

    }
}
