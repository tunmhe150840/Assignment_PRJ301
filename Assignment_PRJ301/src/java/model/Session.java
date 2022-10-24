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
    private int index;
    private Group group;
    private Instructor instructor;
    private TimeSlot timeslot;
    private Room room;
    private Date date;
    private Boolean attended;
    private ArrayList<Attendant> attendances = new ArrayList<>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
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

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public Boolean getAttended() {
        return attended;
    }

}
