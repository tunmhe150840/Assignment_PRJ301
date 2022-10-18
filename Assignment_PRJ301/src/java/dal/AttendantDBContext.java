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
import model.Attendant;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class AttendantDBContext extends DBContext<Attendant> {

    public ArrayList<Attendant> list(String stdCode, String cCode) {
        String sql = "Select a.AttendantID, a.Status, "
                + "s.SessionID,s.[Index],s.Date,"
                + "r.RoomID,r.RoomName,"
                + "ts.SlotID,ts.Description,"
                + "g.GroupID,g.GroupName,g.InstructorCode From Attendant a\n"
                + "INNER JOIN Session s ON a.SessionID = s.SessionID\n"
                + "INNER JOIN [Group] g ON s.GroupID = g.GroupID\n"
                + "INNER JOIN TimeSlot ts ON s.SlotID = ts.SlotID\n"
                + "INNER JOIN Room r ON s.RoomID = r.RoomID\n"
                + "WHERE a.StudentCode = ? AND g.CourseCode = ? ";
        ArrayList<Attendant> attendants = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stdCode);
            stm.setString(2, cCode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendant at = new Attendant();
                Session s = new Session();
                Room r = new Room();
                TimeSlot ts = new TimeSlot();
                Group g = new Group();
                Instructor i = new Instructor();

                at.setAttendantID(rs.getInt("AttendantID"));
                Boolean status = rs.getBoolean("Status");
                if(rs.wasNull()){
                    status = null;
                }
                at.setStatus(status);
                
                s.setSessionID(rs.getInt("SessionID"));
                s.setIndex(rs.getInt("Index"));
                s.setDate(rs.getDate("Date"));

                r.setRoomID(rs.getInt("RoomID"));
                r.setRoomName(rs.getString("RoomName"));
                s.setRoom(r);

                ts.setSlotID(rs.getInt("SlotID"));
                ts.setDescription(rs.getString("Description"));
                s.setTimeslot(ts);

                g.setGroupID(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("GroupName"));

                i.setInstructorCode(rs.getString("InstructorCode"));
                g.setSupervisor(i);
                s.setGroup(g);
                at.setSession(s);

                attendants.add(at);
            }
            return attendants;
        } catch (SQLException ex) {
            Logger.getLogger(AttendantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Attendant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendant get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendant get(String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendant> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
