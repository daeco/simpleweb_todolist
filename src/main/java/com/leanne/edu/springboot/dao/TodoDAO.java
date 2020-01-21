package com.leanne.edu.springboot.dao;

import com.leanne.edu.springboot.model.Todo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDAO {

    /**
     * todo 추가
     *
     * @param work
     * @param refer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void insertTodo(String work, String refer) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("INSERT INTO todo(work, registration_date, reference) values(?, now(), ?)");
        ps.setString(1, work);
        ps.setString(2, refer);

        ps.execute();

        ps.close();
        c.close();
    }

    public void insertTodo(Integer id, String work, String refer) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("INSERT INTO todo(id, work, registration_date, reference) values(?, ?, now(), ?)");
        ps.setInt(1, id);
        ps.setString(2, work);
        ps.setString(3, refer);

        ps.execute();

        ps.close();
        c.close();
    }

    public Integer selectNextTodoId() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT ifnull(max(id), 0) + 1 as id FROM todo");
        ResultSet rs = ps.executeQuery();
        Integer id = null;
        if(rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    /**
     * 모든 todo list 조회
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Todo> selectAll() throws ClassNotFoundException, SQLException {
        List<Todo> todoList = new ArrayList<Todo>();
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM todo");

        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Todo todo = new Todo();
            todo.setId(rs.getInt("id"));
            todo.setWork(rs.getString("work"));
            todo.setRegistrationDate(rs.getDate("registration_date"));
            todo.setModificationDate((rs.getDate("modification_date")));
            todo.setCompleteYn((rs.getString("complete_yn")));
            todo.setReference(rs.getString("reference"));
            todoList.add(todo);
        }

        rs.close();
        ps.close();
        c.close();
        return todoList;
    }

    /**
     * id로 todo list 조회
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Todo selectTodoById(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM todo WHERE id = ?");
        ps.setInt(1,  id);

        ResultSet rs = ps.executeQuery();
        Todo todo = null;
        if(rs.next()) {
            todo = new Todo();
            todo.setId(rs.getInt("id"));
            todo.setWork(rs.getString("work"));
            todo.setRegistrationDate(rs.getDate("registration_date"));
            todo.setModificationDate((rs.getDate("modification_date")));
            todo.setCompleteYn((rs.getString("complete_yn")));
            todo.setReference(rs.getString("reference"));
        }

        rs.close();
        ps.close();
        c.close();

        return todo;
    }

    /**
     * 등록일로 todo list 조회
     *
     * @param
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Todo selectTodoByRegistrationDate(Date registrationDate) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM todo WHERE registration_date = ?");
        ps.setDate(1,  registrationDate);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setWork(rs.getString("work"));
        todo.setRegistrationDate(rs.getDate("registration_date"));
        todo.setModificationDate((rs.getDate("modification_date")));
        todo.setCompleteYn((rs.getString("complete_yn")));
        todo.setReference(rs.getString("reference"));

        rs.close();
        ps.close();
        c.close();

        return todo;
    }

    public Todo selectTodoByModificationDate(Date modificationDate) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM todo WHERE modification_date = ?");
        ps.setDate(1,  modificationDate);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setWork(rs.getString("work"));
        todo.setRegistrationDate(rs.getDate("registration_date"));
        todo.setModificationDate((rs.getDate("modification_date")));
        todo.setCompleteYn((rs.getString("complete_yn")));
        todo.setReference(rs.getString("reference"));

        rs.close();
        ps.close();
        c.close();

        return todo;
    }

    public Todo selectTodoByReference(String refer) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM todo WHERE id = ?");
        ps.setString(1,  refer);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setWork(rs.getString("work"));
        todo.setRegistrationDate(rs.getDate("registration_date"));
        todo.setModificationDate((rs.getDate("modification_date")));
        todo.setCompleteYn((rs.getString("complete_yn")));
        todo.setReference(rs.getString("reference"));

        rs.close();
        ps.close();
        c.close();

        return todo;
    }

    /**
     * todo 할 일 / 참조 / 완료여부 수정
     *
     * @param id
     * @param work
     * @param refer
     * @param complete_yn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateTodo(int id, String work, String refer, String complete_yn) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("update todo set work = ?, modify = now(), reference = ?, complete_yn = ? where id = ?");
        ps.setString(1, work);
        ps.setString(2, refer);
        ps.setString(3,  complete_yn);
        ps.setInt(4, id);

        ps.execute();

        ps.close();
        c.close();
    }


}
