package edu.ijse.layered.fx.services.custom;

import edu.ijse.layered.fx.dto.CourseDto;
import edu.ijse.layered.fx.dto.LecturerDto;
import edu.ijse.layered.fx.services.SuperService;

import java.util.ArrayList;

public interface CourseService extends SuperService {

    public String addCourse(CourseDto courseDto) throws Exception;
    public String updateCourse(CourseDto courseDto) throws Exception;
    public String deleteCourse(String id) throws Exception;
    public CourseDto searchCourse(String id) throws Exception;
    public ArrayList <CourseDto> getAllCourses () throws Exception;

}
