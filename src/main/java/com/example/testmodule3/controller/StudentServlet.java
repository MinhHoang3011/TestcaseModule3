package com.example.testmodule3.controller;

import com.example.testmodule3.dao.IStudentDao;
import com.example.testmodule3.dao.StudentDao;
import com.example.testmodule3.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/home")
public class StudentServlet extends HttpServlet {
    IStudentDao studentDAO = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "create":
                showCreateFrom(req,resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                showAllStudent(req,resp);
                break;
        }
    }

    private void showAllStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        List<Student> studentList = studentDAO.findAll();
        req.setAttribute("list",studentList);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void showCreateFrom(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
        req.getRequestDispatcher("view/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createStudent(req, resp);
                break;
            case "edit":
                break;
            case "delete":

                break;
            default:
                showAllStudent(req, resp);
                break;
        }
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String name = req.getParameter("name");
        Date dateOfBirth = (req.getParameter("DateOfBirth"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        int classID = Integer.parseInt(req.getParameter("ClassID")) ;
        Student student = new Student(name,dateOfBirth,address,phone,email,classID);
        studentDAO.save(student);
        req.setAttribute("messenger","Successful");
        req.getRequestDispatcher("view/create.jsp").forward(req,resp);
    }
}
