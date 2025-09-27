
package edu.ijse.layered.fx.dao.custom.impl;

import edu.ijse.layered.fx.dao.custom.AttendanceReportsDao;
import edu.ijse.layered.fx.db.DBConnection;
import edu.ijse.layered.fx.dto.AttendanceDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceReportsDaoImpl implements AttendanceReportsDao {

    @Override
    public ArrayList<AttendanceDto> searchByDate(LocalDate startDate, LocalDate endDate) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM attendance WHERE date BETWEEN ? AND ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setDate(1, Date.valueOf(startDate));
        st.setDate(2, Date.valueOf(endDate));

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
