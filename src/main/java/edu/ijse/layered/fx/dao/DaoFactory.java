package edu.ijse.layered.fx.dao;

import edu.ijse.layered.fx.dao.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type){
        switch (type) {
            case STUDENT:
                return new StudentDaoImpl();
            case LECTURE:
                return new LecturerDaoImpl();
            case COURSE:
                return new CourseDaoImpl();
            case CLASSES:
                return new ClassDaoImpl();
            case SUBJECT:
                return new SubjectDaoImpl();
            case ENROLL:
                return new EnrollDaoImpl();
            case ATTENDANCE:
                return new AttendanceDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoTypes{
        STUDENT,LECTURE,COURSE,CLASSES,SUBJECT,ENROLL,ATTENDANCE;
    }

}
