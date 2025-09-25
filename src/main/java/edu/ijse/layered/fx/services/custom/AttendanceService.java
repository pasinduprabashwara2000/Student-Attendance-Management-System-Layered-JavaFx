package edu.ijse.layered.fx.services.custom;

import edu.ijse.layered.fx.dto.AttendanceDto;
import edu.ijse.layered.fx.services.SuperService;
import net.sf.jasperreports.engine.export.ExcelAbstractExporter;

import java.util.ArrayList;

public interface AttendanceService extends SuperService {

    public String saveAttendance(AttendanceDto attendanceDto) throws Exception;
    public String updateAttendance(AttendanceDto attendanceDto) throws Exception;
    public String deleteAttendance(Integer attendanceId) throws Exception;
    public AttendanceDto searchAttendance(Integer attendanceId) throws Exception;
    public ArrayList<AttendanceDto> getAllAttendance() throws Exception;

}
