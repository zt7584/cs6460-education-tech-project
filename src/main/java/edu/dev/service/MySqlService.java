package edu.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.dev.entity.User;

@Service
public class MySqlService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Object> executeQuery(String sql) {
    	return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }
}
