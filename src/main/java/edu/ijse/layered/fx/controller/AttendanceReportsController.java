package edu.ijse.layered.fx.controller;

import edu.ijse.layered.fx.dao.custom.AttendanceReportsDao;
import edu.ijse.layered.fx.dao.custom.impl.AttendanceReportsDaoImpl;
import edu.ijse.layered.fx.dto.AttendanceDto;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceReportsController {

    private final AttendanceReportsDao attendanceReportsDao;

    public AttendanceReportsController(){
        this.attendanceReportsDao = new AttendanceReportsDaoImpl();
    }

    public ArrayList <AttendanceDto> searchByDate(LocalDate startDate, LocalDate endDate) throws Exception{
        return attendanceReportsDao.searchByDate(startDate, endDate);
    }

}
