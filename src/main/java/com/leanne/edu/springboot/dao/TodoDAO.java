package com.leanne.edu.springboot.dao;

import com.leanne.edu.springboot.model.Todo;

import java.sql.*;

public class TodoDAO {

    public void add(Todo todo) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("INSERT INTO todo(work, registration, reference) values(?, now(), ?)");
        ps.setString(1, todo.getWork());
        ps.setString(2, todo.getReference());

        ps.execute();

        ps.close();
        c.close();
    }

    public Todo get(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM todo WHERE id = ?");
        ps.setInt(1,  id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setWork(rs.getString("work"));
        todo.setRegistration(rs.getDate("registration"));
        todo.setModify((rs.getDate("modify")));
        todo.setComplete((rs.getDate("complete")));
        todo.setReference(rs.getString("reference"));

        rs.close();
        ps.close();
        c.close();

        return todo;
    }

    public void modify(int id, String work, String reference) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("update todo set work = ?, reference = ? where id = ?");
        ps.setString(1, work);
        ps.setString(2, reference);
        ps.setInt(3, id);

        ps.execute();

        ps.close();
        c.close();
    }

}
