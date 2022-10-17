/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Session {

    private int sessionID;
    private String sessionName;
    private Group group;
    private Instructor instructor;
    private TimeSlot timeslot;
    private Room room;
    private Date date;
    private boolean attended;
    private ArrayList<Attendant> attendances = new ArrayList<>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public ArrayList<Attendant> getAttendances() {
        return attendances;
    }

    public void setAttendances(ArrayList<Attendant> attendances) {
        this.attendances = attendances;
    }

    public Session() {
    }

    public Session(int sessionID, String sessionName) {
        this.sessionID = sessionID;
        this.sessionName = sessionName;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group groups) {
        this.group = groups;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructors) {
        this.instructor = instructors;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslots) {
        this.timeslot = timeslots;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room rooms) {
        this.room = rooms;
    }

}
