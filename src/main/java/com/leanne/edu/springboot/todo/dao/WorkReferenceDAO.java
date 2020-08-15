package com.leanne.edu.springboot.todo.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WorkReferenceDAO {

    /**
     * 참조 저장
     * @param fromId
     * @param toId
     */
    public void insertReference(int fromId, int toId) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        PreparedStatement ps = c.prepareStatement("INSERT INTO WORK_REF(from_id, to_id, registration_date) values(?, ?, now())");
        ps.setInt(1, fromId);
        ps.setInt(2, toId);

        ps.execute();

        ps.close();
        c.close();
    }
}
