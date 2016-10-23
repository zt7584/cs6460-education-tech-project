package edu.dev.controller;

import edu.dev.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tengzhao on 9/18/16.
 */
@RestController
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/getAll")
    public String getUsers() {
        StringBuilder sb = new StringBuilder();
        jdbcTemplate.query(
                "SELECT * FROM user WHERE username = ? OR 1 = 1", new Object[] { "Josh" },
                (rs, rowNum) -> new User(rs.getString("username") + "", rs.getString("name"), rs.getString("password"))
        ).forEach(customer -> sb.append(customer.toString()));
        return sb.toString();
    }
}
