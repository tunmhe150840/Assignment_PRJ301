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
import model.Attendant;
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

    public ArrayList<Session> sessionOfInstructor(String insCode, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "Select s.SessionID,s.[Index],s.Date,s.Attended\n"
                    + ",g.GroupID,g.GroupName,g.CourseCode\n"
                    + ",i.InstructorCode,i.InstructorName\n"
                    + ",r.RoomID,r.RoomName\n"
                    + ",t.SlotID,t.Description \n"
                    + "from Session s\n"
                    + "INNER JOIN [Group] g ON s.GroupID = g.GroupID\n"
                    + "INNER JOIN Instructor i ON s.InstructorCode = i.InstructorCode\n"
                    + "INNER JOIN Room r ON s.RoomID = r.RoomID\n"
                    + "INNER JOIN TimeSlot t ON s.SlotID = t.SlotID\n"
                    + "WHERE i.InstructorCode = ? AND s.Date >=? AND s.Date<=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, insCode);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                Group g = new Group();
                Course c = new Course();
                Instructor i = new Instructor();
                Room r = new Room();
                TimeSlot t = new TimeSlot();
                session.setSessionID(rs.getInt("SessionID"));
                session.setDate(rs.getDate("Date"));
                Boolean attended = rs.getBoolean("Attended");
                if (rs.wasNull()) {
                    attended = null;
                }
                session.setAttended(attended);

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

    public ArrayList<Session> sessionOfStudent(String stdCode, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "Select s.SessionID,s.Date,s.Attended\n"
                    + "                    ,g.GroupID,g.GroupName,g.CourseCode\n"
                    + "                    ,st.StudentCode,st.FullName\n"
                    + "                    ,i.InstructorCode,i.InstructorName\n"
                    + "                    ,r.RoomID,r.RoomName\n"
                    + "                    ,t.SlotID,t.Description \n"
                    + "                     ,a.AttendantID,a.Status\n"
                    + "                    from Session s\n"
                    + "                    INNER JOIN [Group] g ON s.GroupID = g.GroupID\n"
                    + "                    INNER JOIN Group_Student gs ON g.GroupID = gs.GroupID\n"
                    + "                    INNER JOIN Student st ON gs.StudentCode = st.StudentCode\n"
                    + "                    INNER JOIN Instructor i ON s.InstructorCode = i.InstructorCode\n"
                    + "                    INNER JOIN Room r ON s.RoomID = r.RoomID\n"
                    + "                    INNER JOIN TimeSlot t ON s.SlotID = t.SlotID\n"
                    + "                    LEFT JOIN Attendant a ON s.SessionID = a.SessionID AND a.StudentCode = st.StudentCode\n"
                    + "                  WHERE st.StudentCode = ? AND s.Date >= ? AND s.Date<= ?";
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
                Attendant att = new Attendant();
                session.setSessionID(rs.getInt("SessionID"));
                session.setDate(rs.getDate("Date"));
                Boolean attended = rs.getBoolean("Attended");
                if (rs.wasNull()) {
                    attended = null;
                }
                session.setAttended(attended);

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
                
                att.setAttendantID(rs.getInt("AttendantID"));
                Boolean status = rs.getBoolean("Status");
                if (rs.wasNull()) {
                    status = null;
                }
                att.setStatus(status);
                session.getAttendances().add(att);
                sessions.add(session);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Session] SET Attended = 1 WHERE SessionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getSessionID());
            stm.executeUpdate();

            //remove old attandances
            sql = "DELETE Attendant WHERE SessionID = ?";
            PreparedStatement stm_delete = connection.prepareStatement(sql);
            stm_delete.setInt(1, model.getSessionID());
            stm_delete.executeUpdate();

            //insert new attandances
            for (Attendant att : model.getAttendances()) {
                sql = "INSERT INTO Attendant \n"
                        + "(StudentCode,SessionID,Status,RecordTime)\n"
                        + "VALUES \n"
                        + "(?,?,?,GETDATE())";
                PreparedStatement stm_insert = connection.prepareStatement(sql);
                stm_insert.setString(1, att.getStudent().getStudentCode());
                stm_insert.setInt(2, model.getSessionID());
                stm_insert.setBoolean(3, att.isStatus());
                stm_insert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int sessionID) {
        String sql = "SELECT s.SessionID,s.[Index],s.Date,s.Attended\n"
                + "		,g.GroupID,g.GroupName\n"
                + "		,r.RoomID,r.RoomName\n"
                + "             ,ts.SlotID,ts.[Description]\n"
                + "             ,i.InstructorCode,i.InstructorName\n"
                + "             ,c.CourseCode,c.CourseName\n"
                + "             ,st.StudentCode,st.FullName\n"
                + "             ,ISNULL(a.Status,0) Status, a.RecordTime\n"
                + "			FROM [Session] s\n"
                + "                         INNER JOIN Room r ON r.RoomID = s.RoomID\n"
                + "                         INNER JOIN TimeSlot ts ON ts.SlotID = s.SlotID\n"
                + "                         INNER JOIN Instructor i ON i.InstructorCode = s.InstructorCode\n"
                + "                         INNER JOIN [Group] g ON g.GroupID = s.GroupID\n"
                + "                         INNER JOIN Course c ON c.CourseCode = g.CourseCode\n"
                + "                         INNER JOIN [Group_Student] gs ON gs.GroupID = g.GroupID\n"
                + "                         INNER JOIN Student st ON st.StudentCode = gs.StudentCode\n"
                + "                         LEFT JOIN Attendant a ON st.StudentCode = a.StudentCode AND s.SessionID = a.SessionID\n"
                + "                         WHERE s.SessionID = ?";

        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionID);
            ResultSet rs = stm.executeQuery();
            Session session = null;
            while (rs.next()) {
                if (session == null) {
                    session = new Session();
                    session.setSessionID(rs.getInt("SessionID"));
                    session.setIndex(rs.getInt("Index"));
                    session.setDate(rs.getDate("Date"));
                    session.setAttended(rs.getBoolean("Attended"));

                    Group group = new Group();
                    group.setGroupID(rs.getInt("GroupID"));
                    group.setGroupName(rs.getString("GroupName"));

                    Course course = new Course();
                    course.setCourseCode(rs.getString("CourseCode"));
                    course.setCourseName(rs.getString("CourseName"));
                    group.setCourse(course);

                    Instructor instructor = new Instructor();
                    instructor.setInstructorCode(rs.getString("InstructorCode"));
                    instructor.setInstructorName(rs.getNString("InstructorName"));
                    group.setSupervisor(instructor);
                    session.setGroup(group);

                    Room room = new Room();
                    room.setRoomID(rs.getInt("RoomID"));
                    room.setRoomName(rs.getString("RoomName"));
                    session.setRoom(room);

                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setSlotID(rs.getInt("SlotID"));
                    timeSlot.setDescription(rs.getString("Description"));
                    session.setTimeslot(timeSlot);
                }

                Student student = new Student();
                student.setStudentCode(rs.getString("StudentCode"));
                student.setFullName(rs.getNString("FullName"));

                Attendant attendant = new Attendant();
                attendant.setStatus(rs.getBoolean("Status"));
                attendant.setStudent(student);
                attendant.setRecordTime(rs.getDate("RecordTime"));
                session.getAttendances().add(attendant);
            }
            return session;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
