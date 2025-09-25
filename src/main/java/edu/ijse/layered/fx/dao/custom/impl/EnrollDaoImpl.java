package edu.ijse.layered.fx.dao.custom.impl;

import edu.ijse.layered.fx.dao.CrudUtil;
import edu.ijse.layered.fx.dao.custom.EnrollDao;
import edu.ijse.layered.fx.entity.EnrollEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EnrollDaoImpl implements EnrollDao {

    @Override
    public boolean save(EnrollEntity t) throws Exception{
        return CrudUtil.executeUpdate("INSERT INTO enroll VALUES(?,?)",
                t.getRegNum(),
                t.getCourseId());
    }

    @Override
    public boolean update(EnrollEntity t) throws Exception{
        return CrudUtil.executeUpdate("UPDATE enroll SET course_id = ? WHERE reg_number = ?",
                t.getCourseId(),
                t.getRegNum());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM enroll WHERE reg_number = ?");
    }

    @Override
    public EnrollEntity select(String s) throws Exception {

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM enroll WHERE reg_number = ?");

        if (rst.next()){
            return new EnrollEntity(
                    rst.getInt("reg_number"),
                    rst.getString("course_Id")
            );
        }

        return null;
    }

    @Override
    public ArrayList<EnrollEntity> viewAll() throws Exception {

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM enroll");
        ArrayList <EnrollEntity> enrollEntities = new ArrayList<>();

        while (rst.next()){
            enrollEntities.add(new EnrollEntity(
               rst.getInt("reg_number"),
               rst.getString("course_Id")
            ));
        }

        return enrollEntities;

    }
}
