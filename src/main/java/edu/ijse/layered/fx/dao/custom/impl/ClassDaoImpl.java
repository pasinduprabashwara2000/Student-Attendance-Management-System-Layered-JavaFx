package edu.ijse.layered.fx.dao.custom.impl;

import edu.ijse.layered.fx.dao.CrudUtil;
import edu.ijse.layered.fx.dao.custom.ClassDao;
import edu.ijse.layered.fx.entity.ClassEntity;

import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

public class ClassDaoImpl implements ClassDao {

    @Override
    public boolean save(ClassEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO class VALUES (?,?,?,?,?)",
                t.getClassId(),
                t.getCourseId(),
                t.getSubjectId(),
                t.getLectureId(),
                Date.valueOf(t.getDate())
        );
    }

    @Override
    public boolean update(ClassEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE class SET course_id = ?, subject_id = ?, lecture_id = ?, date = ? WHERE class_id = ?",
                t.getCourseId(),
                t.getSubjectId(),
                t.getLectureId(),
                Date.valueOf(t.getDate()),
                t.getClassId()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM class WHERE class_id = ?", id);
    }

    @Override
    public ClassEntity select(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM class WHERE class_id = ?", s);

        if (rst.next()) {
            return new ClassEntity(
                    rst.getString("class_id"),
                    rst.getString("course_id"),
                    rst.getString("subject_id"),
                    rst.getString("lecture_id"),
                    rst.getDate("date").toLocalDate()
            );
        }
        return null;
    }

    @Override
    public ArrayList<ClassEntity> viewAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM class");
        ArrayList<ClassEntity> classEntities = new ArrayList<>();

        while (rst.next()) {
            classEntities.add(new ClassEntity(
                    rst.getString("class_id"),
                    rst.getString("course_id"),
                    rst.getString("subject_id"),
                    rst.getString("lecture_id"),
                    rst.getDate("date").toLocalDate()
            ));
        }
        return classEntities;
    }
}
