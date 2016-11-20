package edu.dev.service;

import java.util.Date;
import java.util.List;

import edu.dev.entity.OnlineJudgeResponse;
import edu.dev.entity.StatisticEntry;
import edu.dev.util.CommonUtils;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.dev.entity.User;

@Service
public class MySqlService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public Object executeQuery(String sql) {
        Object rawResponse = null;
        try {
            rawResponse = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        } catch (Throwable t) {
            rawResponse = t;
        }
        return rawResponse;
    }

    public OnlineJudgeResponse executeQueryForOnlineJudge(String sql) {
        OnlineJudgeResponse onlineJudgeResponse = new OnlineJudgeResponse();
        Date startTime = new Date();
        Object rawResponse = null;
        try {
            rawResponse = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        } catch (Throwable t) {
            rawResponse = t;
        }
        Date endTime = new Date();

        onlineJudgeResponse.setRawResponse(rawResponse);
        onlineJudgeResponse.addStatisticEntry(
                new StatisticEntry(StatisticEntry.NAME.EXEC_TIME, endTime.getTime() - startTime.getTime(), "ms"));
        onlineJudgeResponse.addStatisticEntry(
                new StatisticEntry(StatisticEntry.NAME.NUM_OF_JOIN, CommonUtils.countOfString(sql, Constant.JOIN_KEYWORD), ""));

        return onlineJudgeResponse;
    }
}
