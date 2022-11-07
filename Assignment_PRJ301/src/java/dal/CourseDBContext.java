/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;

/**
 *
 * @author Admin
 */
public class CourseDBContext extends DBContext<Course> {

    public ArrayList<Course> listCourseOfStudent(String stdCode) {
        String sql = "Select c.CourseCode,c.CourseName from [Group] g\n"
                + "INNER JOIN Group_Student gs ON g.GroupID = gs.GroupID\n"
                + "INNER JOIN Course c ON g.CourseCode = c.CourseCode\n"
                + "WHERE gs.StudentCode = ?";
        ArrayList<Course> courses = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stdCode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseCode(rs.getString("CourseCode"));
                c.setCourseName(rs.getString("CourseName"));
                courses.add(c);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Course> listCourseOfInstructor(String insCode) {
        String sql = "Select distinct c.CourseCode,c.CourseName from [Group] g\n"
                + "                INNER JOIN Instructor i ON g.InstructorCode = i.InstructorCode\n"
                + "                INNER JOIN Course c ON g.CourseCode = c.CourseCode\n"
                + "                WHERE i.InstructorCode = ?";
        ArrayList<Course> courses = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, insCode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseCode(rs.getString("CourseCode"));
                c.setCourseName(rs.getString("CourseName"));
                courses.add(c);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Course> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
