/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.SessionDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Session;
import model.Student;
import model.TimeSlot;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
public class TimetableController extends BaseRoleController {
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account.getUsername().equalsIgnoreCase(request.getParameter("stdCode"))) {
            String stdCode = request.getParameter("stdCode");
            String raw_from = request.getParameter("from");
            String raw_to = request.getParameter("to");
            java.sql.Date from = null;
            java.sql.Date to = null;
            if (raw_from == null || raw_from.length() == 0) {
                Date today = new Date();
                int todayOfWeek = DateTimeHelper.getDayofWeek(today);
                Date e_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
                Date e_to = DateTimeHelper.addDays(today, 8 - todayOfWeek);
                from = DateTimeHelper.toDateSql(e_from);
                to = DateTimeHelper.toDateSql(e_to);
            } else {
                from = java.sql.Date.valueOf(raw_from);
                to = java.sql.Date.valueOf(raw_to);
            }

            request.setAttribute("from", from);
            request.setAttribute("to", to);
            request.setAttribute("dates", DateTimeHelper.getDateList(from, to));

            TimeSlotDBContext slotDB = new TimeSlotDBContext();
            ArrayList<TimeSlot> slots = slotDB.list();
            request.setAttribute("slots", slots);

            SessionDBContext sesDB = new SessionDBContext();
            ArrayList<Session> sessions = sesDB.sessionOfStudent(stdCode, from, to);
            request.setAttribute("sessions", sessions);

            StudentDBContext stdDB = new StudentDBContext();
            Student std = stdDB.get(stdCode);
            request.setAttribute("student", std);

            request.getRequestDispatcher("../WEB-INF/view/student/timetable.jsp").forward(request, response);
        } else {
            response.getWriter().print("Access deny!");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req
     * @param resp
     * @param account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req
     * @param resp
     * @param account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
