package edu.dev.controller;

import edu.dev.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tengzhao on 9/18/16.
 */
@Controller
public class LoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute User user) {
        List<User> result = jdbcTemplate.query(
                "SELECT username, name, password FROM User WHERE username=?",
                new Object[] { user.getUsername()},
                (rs, rowNum) -> new User(rs.getString("username"), rs.getString("name"), rs.getString("password")));
        if (result != null && result.size() == 1) {
            if (user.getPassword().equals(result.get(0).getPassword())) {
                return "success";
            }
        }
        return "error";
    }
}
