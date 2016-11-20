package edu.dev.controller;

import edu.dev.entity.*;
import edu.dev.service.MongoService;
import edu.dev.service.MySqlService;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.sun.tools.corba.se.idl.constExpr.Expression.one;

/**
 * Created by tengzhao on 11/19/16.
 */
@Controller
public class OnlineJudgeController {

    @Autowired
    MySqlService mySqlService;

    @Autowired
    MongoService mongoService;

    @GetMapping("/onlinejudge")
    public String onlineJudgeGet(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        setupPage(model, null);
        return "onlinejudge";
    }

    @PostMapping("/onlinejudge")
    public String onlineJudgePost(@ModelAttribute OnlineJudgeRequest onlineJudgeRequest, Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return "redirect:/login";
        }
        OnlineJudgeResponse onlineJudgeResponse = new OnlineJudgeResponse();
        if (onlineJudgeRequest.getDatabase() == Constant.DB_TYPE.MYSQL) {
            onlineJudgeResponse = mySqlService.executeQueryForOnlineJudge(onlineJudgeRequest.getQuery());
        }
        else if (onlineJudgeRequest.getDatabase() == Constant.DB_TYPE.MONGODB) {

        }
        setupPage(model, onlineJudgeResponse);
        return "onlinejudge";
    }

    private void setupPage(Model model, OnlineJudgeResponse onlineJudgeResponse) {
        model.addAttribute("onlinejudge", new OnlineJudgeRequest());
        model.addAttribute("onlinejudgeResponse", onlineJudgeResponse);
    }
}
