package com.gladius.notification;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class NotificationHandler implements Notification {
    private Mailer mailer;

    public NotificationHandler() {
        mailer = new Mailer("gladius.notification@gmail.com", "smtp.gmail.com", "587", true, "gladius.notification@gmail.com", "9FM-mZD-wtC-trd");
    }

    public String sendNotification(long userID, String message, String noticeType) {

        return null;
    }

    public String sendNotification(ArrayList<Long> userISs, String message, String noticeType) {

        return null;
    }

    private String validate(ArrayList<Long> userISs, String message, String noticeType) {

        return null;
    }

}