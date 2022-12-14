package com.github.AlenaKhudnitskaya.WinterMoodBot.command;

import com.github.AlenaKhudnitskaya.WinterMoodBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.github.AlenaKhudnitskaya.WinterMoodBot.command.CommandName.*;

public class RandomActivityCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private static final String url = "jdbc:mysql://localhost:3309/wmb_db?characterEncoding=UTF-8";
    private static final String user = "wmb_db_username";
    private static final String password = "wmb_db_password";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private static String activity_description;
    private static String activity_condition;
    private static int activity_id;

    String selectActivity = "select activity_id, activity_description, activity_condition from activity order by rand() limit 1";
    private static String RANDOM_MESSAGE;

    public void getRandomActivity() {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(selectActivity);

            while (rs.next()) {
                activity_id = rs.getInt(1);
                activity_description = rs.getString(2);
                activity_condition = rs.getString(3);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        if (activity_condition != null) {
            RANDOM_MESSAGE = String.format(
                    "✨" + activity_description + "✨\n"
                            + activity_condition + "\n\n"

                            + "%s - еще одна случайная идея занятия\n"
                            + "%s - получить помощь в работе со мной\n",
                    RA.getCommandName(), HELP.getCommandName());
        } else {
            RANDOM_MESSAGE = String.format(
                    "✨" + activity_description + "✨\n\n"

                            + "%s - еще одна случайная идея занятия\n"
                            + "%s - получить помощь в работе со мной\n",
                    RA.getCommandName(), HELP.getCommandName());
        }
    }

    public RandomActivityCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        getRandomActivity();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), RANDOM_MESSAGE);
    }
}