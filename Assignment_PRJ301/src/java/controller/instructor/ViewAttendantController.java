/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.auth.BaseRoleController;
import dal.CourseDBContext;
import dal.GroupDBContext;
import dal.InstructorDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Course;
import model.Group;
import model.Instructor;
import model.Session;

/**
 *
 * @author Admin
 */
public class ViewAttendantController extends BaseRoleController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account.getUsername().equalsIgnoreCase(request.getParameter("insCode"))) {
            String insCode = request.getParameter("insCode");
            String cCode = request.getParameter("cCode");
            int gCode = -1;
            if (request.getParameter("gCode") != null) {
                gCode = Integer.parseInt(request.getParameter("gCode"));
            }
            
            if (cCode != null && cCode.length() != 0) {
                request.setAttribute("cCode", cCode);
            }
            
            CourseDBContext cDB = new CourseDBContext();
            ArrayList<Course> courses = cDB.listCourseOfInstructor(insCode);
            request.setAttribute("courses", courses);
            
            InstructorDBContext insDB = new InstructorDBContext();
            Instructor ins = insDB.get(insCode);
            request.setAttribute("instructor", ins);
            
            GroupDBContext gdbc = new GroupDBContext();
            ArrayList<Group> groups = gdbc.list(insCode, cCode);
            request.setAttribute("groups", groups);
            
            SessionDBContext sdbc = new SessionDBContext();
            ArrayList<Session> sessions = sdbc.sessionOfInstructor(insCode, cCode, gCode);
            request.setAttribute("listSes", sessions);
            
            request.getRequestDispatcher("../WEB-INF/view/instructor/viewAttendant.jsp").forward(request, response);
        } else {
            response.getWriter().print("Access deny!");
        }
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
}
