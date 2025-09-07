package edu.ijse.layered.fx.controller;

import edu.ijse.layered.fx.dao.custom.AttendanceDao;
import edu.ijse.layered.fx.dao.custom.AttendanceReportsDao;
import edu.ijse.layered.fx.dto.AttendanceDto;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceReportsController {

    private AttendanceReportsDao attendanceReportsDao;

    public ArrayList <AttendanceDto> searchByDate(LocalDate startDate, LocalDate endDate) throws Exception{
        return attendanceReportsDao.searchByDate(startDate.toString(),endDate.toString());
    }

}
