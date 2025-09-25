package edu.ijse.layered.fx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceEntity {

    private int attendanceId;
    private LocalDate date;
    private String lectureId;
    private String studentName;
    private String courseName;
    private String subjectName;
    private String status;

}
