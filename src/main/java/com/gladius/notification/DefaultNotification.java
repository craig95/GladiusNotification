import org.json.JSONException;
import java.awt.List;

public class DefaultNotification implements Notification 
{
	
	
	/**
	 * Interface implemented by DefaultNotification and OtherNotificationTypes
	 * @param jArray - Stores content to be sent.
	 */
	public void createContent(org.json.JSONArray jArray)
	{
		String subject;
		String htmlContent;
		
		htmlContent = "<p>";
		try{
			/*
			for(int i = 0; i < jArray.length(); i++)
			{
				htmlContent += jArray.getString(i);
			}
			*/
                    
                        htmlContent += "This is email content";
			htmlContent += "</p>"; 
                        
                        subject = "NavUP test notification";
		}
		catch (JSONException e)
		{
			htmlContent = "";
		}
		/*
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < jArray.length(); i++){
		    list.add(jArray.getJSONObject(i).getString("name"));
		}*/

	}
}