/*
 * 	Heinrich Burgers
 * 	15059538
 */
import org.json.JSONException;



/**
 * Interface implemented by DefaultNotification and OtherNotificationTypes
 * @param jArray - Stores content to be sent.
 */
public interface Notification
{
	
	public void createContent(org.json.JSONArray jArray);
	
}