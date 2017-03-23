/*
 * 	Heinrich Burgers
 * 	15059538
 */
package com.gladius.notification;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.imageio.IIOException;
import javax.management.Notification;
import java.io.IOException;
import java.io.StringWriter;


public interface NotificationObject
{
    /**
     * Interface implemented by DefaultNotification and OtherNotificationTypes
     * @param jArray - Stores content to be sent.
     */
	public void createContent(JSONArray jArray);
	public String getHTML();
	public String getSubject();
	
}