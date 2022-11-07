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
import model.Group;
import model.Instructor;

/**
 *
 * @author Admin
 */
public class GroupDBContext extends DBContext<Group> {

    public ArrayList<Group> list(String insCode,String courseCode) {
        try {
            String sql = "SELECT [GroupID]\n"
                    + "      ,[GroupName]\n"
                    + "      ,[CourseCode]\n"
                    + "      ,[InstructorCode]\n"
                    + "  FROM [Group]\n"
                    + "  WHERE InstructorCode = ? AND CourseCode = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, insCode);
            stm.setString(2, courseCode);
            ResultSet rs = stm.executeQuery();
            ArrayList<Group> list = new ArrayList<>();
            while (rs.next()) {                
                Group g = new Group();
                g.setGroupID(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("GroupName"));
                
                Course c = new Course();
                c.setCourseCode(rs.getString("CourseCode"));
                g.setCourse(c);
                
                Instructor i = new Instructor();
                i.setInstructorCode(rs.getString("InstructorCode"));
                g.setSupervisor(i);
                
                list.add(g);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
