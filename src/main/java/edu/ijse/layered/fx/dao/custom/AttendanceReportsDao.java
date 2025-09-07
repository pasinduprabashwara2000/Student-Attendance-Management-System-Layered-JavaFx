package edu.ijse.layered.fx.dao.custom;

import edu.ijse.layered.fx.dto.AttendanceDto;

import java.util.ArrayList;

public interface AttendanceReportsDao {

    public ArrayList <AttendanceDto> searchByDate(String startDate , String endDate) throws Exception;

}
