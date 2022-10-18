/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> filter(String stdCode, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "Select s.SessionID,s.Date,s.Attended\n"
                    + ",g.GroupID,g.GroupName,g.CourseCode\n"
                    + ",st.StudentCode,st.FullName\n"
                    + ",i.InstructorCode,i.InstructorName\n"
                    + ",r.RoomID,r.RoomName\n"
                    + ",t.SlotID,t.Description \n"
                    + "from Session s\n"
                    + "INNER JOIN [Group] g ON s.GroupID = g.GroupID\n"
                    + "INNER JOIN Group_Student gs ON g.GroupID = gs.GroupID\n"
                    + "INNER JOIN Student st ON gs.StudentCode = st.StudentCode\n"
                    + "INNER JOIN Instructor i ON s.InstructorCode = i.InstructorCode\n"
                    + "INNER JOIN Room r ON s.RoomID = r.RoomID\n"
                    + "INNER JOIN TimeSlot t ON s.SlotID = t.SlotID\n"
                    + "WHERE st.StudentCode = ? AND s.Date >=? AND s.Date<=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stdCode);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                Group g = new Group();
                Course c = new Course();
                Student s = new Student();
                ArrayList<Student> students = new ArrayList<>();
                Instructor i = new Instructor();
                Room r = new Room();
                TimeSlot t = new TimeSlot();
                session.setSessionID(rs.getInt("SessionID"));
                session.setDate(rs.getDate("Date"));
                session.setAttended(rs.getBoolean("Attended"));

                s.setStudentCode(rs.getString("StudentCode"));
                s.setFullName(rs.getNString("FullName"));
                students.add(s);
                g.setStudents(students);

                i.setInstructorCode(rs.getString("InstructorCode"));
                i.setInstructorName(rs.getNString("InstructorName"));
                session.setInstructor(i);

                c.setCourseCode(rs.getString("CourseCode"));
                g.setCourse(c);

                g.setGroupID(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("GroupName"));
                session.setGroup(g);

                r.setRoomID(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                session.setRoom(r);

                t.setSlotID(rs.getInt("SlotID"));
                t.setDescription(rs.getString("Description"));
                session.setTimeslot(t);

                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
