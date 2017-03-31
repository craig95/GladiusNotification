package com.gladius.notification;

import java.util.ArrayList;

/**
 * Created by craigvanheerden on 3/31/17.
 */
public interface Notification {
    String sendNotification(long userID, String message, String noticeType);
    String sendNotification(ArrayList<Long> userISs, String message, String noticeType);
}
