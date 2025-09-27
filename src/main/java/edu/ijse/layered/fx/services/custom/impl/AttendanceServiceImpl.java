package edu.ijse.layered.fx.services.custom.impl;

import edu.ijse.layered.fx.dao.DaoFactory;
import edu.ijse.layered.fx.dao.custom.AttendanceDao;
import edu.ijse.layered.fx.dto.AttendanceDto;
import edu.ijse.layered.fx.entity.AttendanceEntity;
import edu.ijse.layered.fx.services.custom.AttendanceService;
import java.util.ArrayList;

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDao attendanceDao =
            (AttendanceDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ATTENDANCE);

    @Override
    public String saveAttendance(AttendanceDto attendanceDto) throws Exception {
        AttendanceEntity attendanceEntity = new AttendanceEntity(
                attendanceDto.getAttendanceId(),
                attendanceDto.getDate(),
                attendanceDto.getLectureId(),
                attendanceDto.getStudentName(),
                attendanceDto.getCourseName(),
                attendanceDto.getSubjectName(),
                attendanceDto.getStatus()
        );
        return attendanceDao.save(attendanceEntity)
                ? "Attendance Saved Successfully"
                : "Attendance Save Failed";
    }

    @Override
    public String updateAttendance(AttendanceDto attendanceDto) throws Exception {
        AttendanceEntity attendanceEntity = new AttendanceEntity(
                attendanceDto.getAttendanceId(),
                attendanceDto.getDate(),
                attendanceDto.getLectureId(),
                attendanceDto.getStudentName(),
                attendanceDto.getCourseName(),
                attendanceDto.getSubjectName(),
                attendanceDto.getStatus()
        );
        return attendanceDao.update(attendanceEntity)
                ? "Attendance Updated Successfully"
                : "Attendance Update Failed";
    }

    @Override
    public String deleteAttendance(Integer attendanceId) throws Exception {
        return attendanceDao.delete(String.valueOf(attendanceId))
                ? "Attendance Deleted Successfully"
                : "Attendance Delete Failed";
    }

    @Override
    public AttendanceDto searchAttendance(Integer attendanceId) throws Exception {
        AttendanceEntity attendanceEntity = attendanceDao.select(String.valueOf(attendanceId));

        if (attendanceEntity != null) {
            return new AttendanceDto(
                    attendanceEntity.getAttendanceId(),
                    attendanceEntity.getDate(),
                    attendanceEntity.getLectureId(),
                    attendanceEntity.getStudentName(),
                    attendanceEntity.getCourseName(),
                    attendanceEntity.getSubjectName(),
                    attendanceEntity.getStatus()
            );
        }
        return null;
    }

    @Override
    public ArrayList<AttendanceDto> getAllAttendance() throws Exception {
        ArrayList<AttendanceDto> attendanceDtos = new ArrayList<>();
        ArrayList<AttendanceEntity> attendanceEntities = attendanceDao.viewAll();

        for (AttendanceEntity attendanceEntity : attendanceEntities) {
            attendanceDtos.add(new AttendanceDto(
                    attendanceEntity.getAttendanceId(),
                    attendanceEntity.getDate(),
                    attendanceEntity.getLectureId(),
                    attendanceEntity.getStudentName(),
                    attendanceEntity.getCourseName(),
                    attendanceEntity.getSubjectName(),
                    attendanceEntity.getStatus()
            ));
        }
        return attendanceDtos;
    }
}
