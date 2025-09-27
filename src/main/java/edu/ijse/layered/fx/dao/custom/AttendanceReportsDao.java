package edu.ijse.layered.fx.dao.custom;

import edu.ijse.layered.fx.dto.AttendanceDto;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AttendanceReportsDao {

    public ArrayList <AttendanceDto> searchByDate(LocalDate startDate, LocalDate endDate) throws Exception;

}
