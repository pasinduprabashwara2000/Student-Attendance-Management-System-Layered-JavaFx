CREATE DATABASE IJSE;

-- Table: course
CREATE TABLE course (
    course_id VARCHAR(25) NOT NULL PRIMARY KEY,
    name      VARCHAR(25) NOT NULL
);

-- Table: lecture
CREATE TABLE lecture (
    lecture_id      VARCHAR(10) NOT NULL PRIMARY KEY,
    name            VARCHAR(25) NOT NULL,
    contact_details VARCHAR(25) NOT NULL
);

-- Table: student
CREATE TABLE student (
    reg_number      INT NOT NULL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    contact_details VARCHAR(25) NOT NULL
);

-- Table: subject
CREATE TABLE subject (
    subject_id   VARCHAR(10) NOT NULL PRIMARY KEY,
    course_id    VARCHAR(10) NOT NULL,
    subject_name VARCHAR(25) NOT NULL,
    CONSTRAINT subject_ibfk_1 FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE INDEX idx_subject_course_id ON subject(course_id);

-- Table: class
CREATE TABLE class (
    class_id     VARCHAR(10) NOT NULL PRIMARY KEY,
    course_id    VARCHAR(10) NOT NULL,
    subject_name VARCHAR(10) NOT NULL,
    lecture_id   VARCHAR(10) NOT NULL,
    date         DATE NOT NULL,
    CONSTRAINT class_ibfk_1 FOREIGN KEY (course_id) REFERENCES course(course_id),
    CONSTRAINT class_ibfk_2 FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);

CREATE INDEX idx_class_course_id ON class(course_id);
CREATE INDEX idx_class_lecture_id ON class(lecture_id);

-- Table: enroll
CREATE TABLE enroll (
    reg_number INT NOT NULL,
    course_id  VARCHAR(10) NOT NULL,
    PRIMARY KEY (reg_number, course_id),
    CONSTRAINT enroll_ibfk_1 FOREIGN KEY (reg_number) REFERENCES student(reg_number),
    CONSTRAINT enroll_ibfk_2 FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE INDEX idx_enroll_course_id ON enroll(course_id);

-- Table: attendance
CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY,
    date         DATE NOT NULL,
    lecture_id   VARCHAR(10) NOT NULL,
    student_name VARCHAR(55) NOT NULL,
    course_name  VARCHAR(55) NOT NULL,
    subject_name VARCHAR(55) NOT NULL,
    status       ENUM('Present', 'Absent') NOT NULL
);

-- Table: user
CREATE TABLE user (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(25) NULL,
    password  VARCHAR(25) NULL,
    role      VARCHAR(25) NULL
);

INSERT INTO user VALUES 
(1, 'Admin', '123', 'Admin'),
(2, 'Lecture', '123', 'Lecture');
