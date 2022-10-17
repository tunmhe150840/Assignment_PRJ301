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
public class Student {
    private String studentCode;
    private String fullName;
    private Date dateOfBirth;
    private boolean gender;
    private ArrayList<Group> groups = new ArrayList<>();

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
    private ArrayList<Attendant> attendants;

    public Student() {
    }

    public Student(String studentCode, String fullName, Date dateOfBirth, boolean gender) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public ArrayList<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(ArrayList<Attendant> attendants) {
        this.attendants = attendants;
    }

}
