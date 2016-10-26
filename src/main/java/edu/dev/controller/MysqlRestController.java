package edu.dev.controller;

import edu.dev.entity.User;
import edu.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tengzhao on 10/26/16.
 */
@RestController
public class MysqlRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/mysql", method = RequestMethod.POST, produces = "application/json")
    public Object execute(@RequestBody String sql) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        //return sql;
    }
}
