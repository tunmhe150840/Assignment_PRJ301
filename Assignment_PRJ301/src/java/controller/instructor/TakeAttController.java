/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Attendant;
import model.Session;
import model.Student;

/**
 *
 * @author Admin
 */
public class TakeAttController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sesid = Integer.parseInt(req.getParameter("id"));
        SessionDBContext sesDB = new SessionDBContext();
        Session ses = sesDB.get(sesid);
        req.setAttribute("session", ses);
        req.getRequestDispatcher("../WEB-INF/view/instructor/TakeAtt.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session ses = new Session();
        ses.setSessionID(Integer.parseInt(req.getParameter("sesid")));
        String[] stdCodes = req.getParameterValues("stdCode");
        for (String stdCode : stdCodes) {
            Attendant a = new Attendant();
            Student s = new Student();
            a.setStudent(s);
            a.setStatus(req.getParameter("status" + stdCode).equals("present"));
            s.setStudentCode(stdCode);
            ses.getAttendances().add(a);
        }
        SessionDBContext db = new SessionDBContext();
        db.update(ses);
        resp.sendRedirect("takeAtt?id=" + ses.getSessionID());
    }

}
