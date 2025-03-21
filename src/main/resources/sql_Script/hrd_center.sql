
-- ================== Create Database ==================
CREATE DATABASE hrd_center;

-- ================== Create Table ==================
CREATE TABLE students(
                         student_id SERIAL PRIMARY KEY,
                         student_name VARCHAR(50) NOT NULL,
                         student_email VARCHAR(50) NOT NULL,
                         student_phone VARCHAR(50) NOT NULL
);

CREATE TABLE instructors(
                            instructor_id SERIAL PRIMARY KEY,
                            instructor_name VARCHAR(50) NOT NULL,
                            instructor_email VARCHAR(50) NOT NULL
);

CREATE TABLE courses(
                        course_id SERIAL PRIMARY KEY,
                        course_name VARCHAR(50) NOT NULL,
                        course_description TEXT NOT NULL,
                        instructor_id INT NOT NULL,
                        FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id)
);

CREATE TABLE student_course(
                               student_course_id SERIAL PRIMARY KEY,
                               student_id INT NOT NULL,
                               course_id INT NOT NULL,
                               FOREIGN KEY (student_id) REFERENCES students(student_id),
                               FOREIGN KEY (course_id) REFERENCES courses(course_id)
);


-- =================== Insert Data ===================

-- Insert data into students table

INSERT INTO students (student_name, student_email, student_phone) VALUES
                                                                      ('John Doe', 'john.doe@example.com', '123-456-7890'),
                                                                      ('Jane Smith', 'jane.smith@example.com', '234-567-8901'),
                                                                      ('Alice Johnson', 'alice.johnson@example.com', '345-678-9012'),
                                                                      ('Bob Brown', 'bob.brown@example.com', '456-789-0123'),
                                                                      ('Charlie Davis', 'charlie.davis@example.com', '567-890-1234'),
                                                                      ('David Wilson', 'david.wilson@example.com', '678-901-2345'),
                                                                      ('Eve Taylor', 'eve.taylor@example.com', '789-012-3456'),
                                                                      ('Frank White', 'frank.white@example.com', '890-123-4567'),
                                                                      ('Grace Hall', 'grace.hall@example.com', '901-234-5678'),
                                                                      ('Henry King', 'henry.king@example.com', '012-345-6789');


-- Insert data into instructors table

INSERT INTO instructors (instructor_name, instructor_email) VALUES
                                                                ('Dr. Smith', 'smith@example.com'),
                                                                ('Prof. Johnson', 'johnson@example.com'),
                                                                ('Dr. Williams', 'williams@example.com'),
                                                                ('Prof. Brown', 'brown@example.com'),
                                                                ('Dr. Davis', 'davis@example.com'),
                                                                ('Prof. Wilson', 'wilson@example.com'),
                                                                ('Dr. Martinez', 'martinez@example.com'),
                                                                ('Prof. Anderson', 'anderson@example.com'),
                                                                ('Dr. Thomas', 'thomas@example.com'),
                                                                ('Prof. Taylor', 'taylor@example.com');


-- Insert data into courses table

INSERT INTO courses (course_name, course_description, instructor_id) VALUES
                                                                         ('Mathematics', 'Basic and advanced mathematics', 1),
                                                                         ('Physics', 'Introduction to physics', 2),
                                                                         ('Chemistry', 'Organic and inorganic chemistry', 3),
                                                                         ('Biology', 'Study of living organisms', 4),
                                                                         ('Computer Science', 'Introduction to programming', 5),
                                                                         ('History', 'World history and civilizations', 6),
                                                                         ('Literature', 'Analysis of literary works', 7),
                                                                         ('Economics', 'Basic economic principles', 8),
                                                                         ('Psychology', 'Understanding human behavior', 9),
                                                                         ('Philosophy', 'Fundamentals of philosophical thought', 10);


-- Insert data into student_course table

INSERT INTO student_course (student_id, course_id) VALUES
                                                       (1, 1), (1, 5),
                                                       (2, 2), (2, 6),
                                                       (3, 3), (3, 7),
                                                       (4, 4), (4, 8),
                                                       (5, 1), (5, 9),
                                                       (6, 2), (6, 10),
                                                       (7, 3), (7, 1),
                                                       (8, 4), (8, 2),
                                                       (9, 5), (9, 3),
                                                       (10, 6), (10, 4);

--==================== END OF INSERT DATA ====================