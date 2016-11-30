package edu.dev.util;

/**
 * Created by tengzhao on 10/26/16.
 */
public class Constant {
    /*
     * Session
     */
    public static final String SESSION_USER = "session_user";

    /*
     * SQL Keywords
     */
    public static final String JOIN_KEYWORD = "Join";
    public static final String[] OPERATORS = {">", ">=", "==", "<=", "<"};
    public static final String[] AND_OR_OPERATORS = {"||", "&&"};

    public interface DB_TYPE {
        int MYSQL = 0;
        int MONGODB = 1;
    }
}
