package com.gladius.notification;

public class NotificationHandler {
    public static void main(String[] args) {
        Mailer mailer = new Mailer("gladius.notification@gmail.com", "smtp.gmail.com", "587", true, "gladius.notification@gmail.com", "9FM-mZD-wtC-trd");
        System.out.println(mailer.sendNotification("NavUP Notification", "<h1>Hello World</h1>", "crvheerden@gmail.com"));
    }
}