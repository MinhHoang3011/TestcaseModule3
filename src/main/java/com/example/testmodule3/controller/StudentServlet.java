package com.example.testmodule3.controller;

import com.example.testmodule3.dao.IStudentDao;
import com.example.testmodule3.dao.StudentDao;
import com.example.testmodule3.model.Student;

import java.io.*;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/manager")
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
                showEditFrom(req,resp);
                break;
            case "delete":
                deleteStudent(req,resp);
                break;
            default:
                showAllStudent(req,resp);
                break;
        }
    }

    private void showEditFrom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentDAO.findByID(id);
        req.setAttribute("studentfind", student);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/edit.jsp");
        dispatcher.forward(req, resp);
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
                editStudent(req,resp);
                break;
            case "delete":
                deleteStudent(req,resp);
                break;
            default:
                showAllStudent(req, resp);
                break;
        }
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int classId = Integer.parseInt(request.getParameter("classId"));
        studentDAO.edit(id,new Student(name,dateOfBirth,address,email,phone,classId));
        request.setAttribute("mess", "Success !");
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        dispatcher.forward(request, response);
    }
    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        studentDAO.delete(id);
        showAllStudent(req, resp);
    }
    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String name = req.getParameter("name");
        Date dateOfBirth = Date.valueOf(req.getParameter("DateOfBirth"));
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
