package edu.ijse.layered.fx.controller;

import edu.ijse.layered.fx.dto.AttendanceDto;
import edu.ijse.layered.fx.services.ServiceFactory;
import edu.ijse.layered.fx.services.custom.AttendanceService;

import java.util.ArrayList;

public class AttendanceController {

    private AttendanceService attendanceservice = (AttendanceService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceTypes.ATTENDANCE);

    public String saveAttendance(AttendanceDto attendanceDto) throws Exception{
        return attendanceservice.saveAttendance(attendanceDto);
    }

    public String updateAttendance(AttendanceDto attendanceDto) throws Exception{
        return attendanceservice.updateAttendance(attendanceDto);
    }

    public String deleteAttendance(Integer attendanceId) throws Exception{
        return attendanceservice.deleteAttendance(attendanceId);
    }

    public AttendanceDto searchAttendance(Integer attendanceId) throws Exception{
        return attendanceservice.searchAttendance(attendanceId);
    }

    public ArrayList<AttendanceDto> getAllAttendance() throws Exception{
        return attendanceservice.getAllAttendance();
    }


}
