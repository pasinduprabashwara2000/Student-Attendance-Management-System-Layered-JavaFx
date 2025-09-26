package edu.ijse.layered.fx.dao.custom.impl;

import edu.ijse.layered.fx.dao.CrudUtil;
import edu.ijse.layered.fx.dao.custom.CourseDao;
import edu.ijse.layered.fx.entity.CourseEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {

    @Override
    public boolean save(CourseEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO course(course_id, name) VALUES(?, ?)",
                t.getCourseId(),
                t.getName()
        );
    }

    @Override
    public boolean update(CourseEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE course SET name = ? WHERE course_id = ?",
                t.getName(),
                t.getCourseId()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM course WHERE course_id = ?",
                id
        );
    }

    @Override
    public CourseEntity select(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(
                "SELECT * FROM course WHERE course_id = ?",
                s
        );

        if (rst.next()) {
            return new CourseEntity(
                    rst.getString("course_id"),
                    rst.getString("name")
            );
        }
        return null;
    }

    @Override
    public ArrayList<CourseEntity> viewAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM course");
        ArrayList<CourseEntity> courseEntities = new ArrayList<>();

        while (rst.next()) {
            courseEntities.add(new CourseEntity(
                    rst.getString("course_id"),
                    rst.getString("name")
            ));
        }
        return courseEntities;
    }
}
