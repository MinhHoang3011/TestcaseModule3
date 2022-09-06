package com.example.testmodule3.dao;

import com.example.testmodule3.connection.ConnectionDB;
import com.example.testmodule3.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDao implements IStudentDao {
    private final Connection connection = ConnectionDB.getConnection();
    private final String SELECT_ALL_STUDENT = "select * from student;";
    private final String SELECT_STUDENT_BY_NAME = "select * from student where name like ?;";
    private final String INSERT_STUDENT = "insert into student (name, DateOfBirth, address, phone, email, ClassId) values (?,?,?,?,?,?);";
    private final String DELETE_STUDENT = "delete from student where id = ?;";
    private final String EDIT_STUDENT = "update student set name = ?, DateOfBirth = ?,address = ?,phone=?,email=?,ClassId=? where id = ?;";
    private final String SELECT_STUDENT_BY_ID = "select * from student where id = ?;";

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int classId = rs.getInt("classId");
                Student student = new Student(id, name, dateOfBirth, address, phone, email, classId);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> findByName(String name) {
        return null;
    }

    @Override
    public void save(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);
            statement.setString(1, student.getName());
            statement.setDate(2, (java.sql.Date) student.getDateOfBirth());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getEmail());
            statement.setInt(6, student.getIdClass());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(int id, Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Successful");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Disable Customer Successfull !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findByID(int id) {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int classId = rs.getInt("classId");
                student = new Student(id1, name, dateOfBirth,address, phone, email, classId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}