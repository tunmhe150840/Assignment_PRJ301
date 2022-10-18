/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import dal.AttendantDBContext;
import dal.CourseDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Attendant;
import model.Course;
import model.Student;

/**
 *
 * @author Admin
 */
public class ViewAttendantController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stdCode = request.getParameter("stdCode");
        String cCode = request.getParameter("cCode");

        if (cCode != null && cCode.length() != 0) {
            request.setAttribute("cCode", cCode);
        }

        CourseDBContext cDB = new CourseDBContext();
        ArrayList<Course> courses = cDB.list(stdCode);
        request.setAttribute("courses", courses);

        StudentDBContext stdDB = new StudentDBContext();
        Student std = stdDB.get(stdCode);
        request.setAttribute("student", std);

        AttendantDBContext adbc = new AttendantDBContext();
        ArrayList<Attendant> attendants = adbc.list(stdCode, cCode);
        request.setAttribute("listAtt", attendants);

        request.getRequestDispatcher("../WEB-INF/view/student/viewAttendant.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
