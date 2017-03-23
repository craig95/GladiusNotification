package com.gladius.notification;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.StringWriter;

public class NotificationHandler {
    private Mailer mailer;

    public NotificationHandler() {
        mailer = new Mailer("gladius.notification@gmail.com", "smtp.gmail.com", "587", true, "gladius.notification@gmail.com", "9FM-mZD-wtC-trd");
    }

    public String sendNotification(String json_string) throws IOException {
        NotificationObject notification = createNotification(toJSONArray(json_string));
        Boolean notification_sent = new Boolean(mailer.sendNotification(notification.getSubject(), notification.getHTML(), "crvheerden@gmail.com"));
        JSONObject obj = new JSONObject();

        obj.put("notification_sent", notification_sent);
        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
        } catch (IIOException e) {
            return null;
        }
        String return_json_string = out.toString();
        return return_json_string;
    }

    private JSONArray toJSONArray(String json_string) {
        return new JSONArray();
    }

    private boolean validateNotification(JSONArray obj) {
        return true;
    }

    private NotificationObject createNotification(JSONArray obj) {
        NotificationObject notification = new DefaultNotification();
        notification.createContent(obj);
        return notification;
    }

    public static void main(String[] args) throws IOException{
        NotificationHandler notificationHandler = new NotificationHandler();
        System.out.println(notificationHandler.sendNotification("{\"just_a_test\": \"test_stuff\"}"));
        while (true) {}
    }
}