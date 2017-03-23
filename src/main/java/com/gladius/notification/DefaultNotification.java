package com.gladius.notification;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.imageio.IIOException;
import javax.management.Notification;
import java.io.IOException;
import java.io.StringWriter;

public class DefaultNotification implements NotificationObject
{
	public String subject;
	public String htmlContent;

	/**
	 * Interface implemented by DefaultNotification and OtherNotificationTypes
	 * @param jArray - Stores content to be sent.
	 */
	public void createContent(JSONArray jArray)
	{
		htmlContent = "<h1>";
		htmlContent += "Email Heading";
		htmlContent += "</h1>";
		htmlContent += "<p>";
		htmlContent += "This is the email content";
		htmlContent += "</p>";

		subject = "NavUP Test Notification";

//		try{
			/*
			for(int i = 0; i < jArray.length(); i++)
			{
				htmlContent += jArray.getString(i);
			}
			*/
                    

//		}
//		catch (JSONException e)
//		{
//			htmlContent = "";
//		}
		/*
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < jArray.length(); i++){
		    list.add(jArray.getJSONObject(i).getString("name"));
		}*/

	}

	public String getHTML() {
		return htmlContent;
	}

	public String getSubject() {
		return subject;
	}
}