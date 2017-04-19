  /**
     * Public interface to send a message to a array list of users.
     * @param userIDs array list of IDs of the users the message needs to be sent to.
     * @param message a message in string format that needs to be sent to the user.
     * @param noticeType the type of notification sms(to be determined), email or push notification.
     * @return will return true if the message was successfully sent and false if message failed to send.
     */
    /*
     * TODO: First it should validate the parameters sent to it with the validation function, if the validation function returns true it should continue.
     * TODO: Second check the type of message and gather all the information needed to send that type of notification.
     * TODO:    If type is email it should create an array of InternetAddresses(http://stackoverflow.com/questions/13854037/send-mail-to-multiple-recipients-in-java#answer-13854096) from the emails of the users using the getEmail function.
     * TODO:    If type is sms it should create an ArrayList of phone numbers which it needs to get from the getNumber function.
     * TODO:    If type push... we still need to confirm with integration on how that would work.
     * TODO: Third it should send the data collected for the specific notification type to the relevant handler for that notification type.
     * TODO:    If type is email a new EmailerThread should be created and run.
     * TODO:    If type is sms a new SMSerThread should be created and run. The format of addresses should be xxxxxxxxxx@<Email_to_SMS_API_Domain> where xxxxxxxxxx is the users phone number and <Email_to_SMS_API_Domain> is the variable with the same name.
     * TODO:    If type is push... we still need to confirm with integration on how that would work.
     * TODO: Fourth return true or false depending if all of this was successful or not.
     */
    public boolean sendNotification(ArrayList<Long> userIDs, String message, String noticeType) {
        /*Validate the paramaters*/
        ArrayList<Long> tempArray = new ArrayList<Long>();
        tempArray.add(userID);
        String valid = validate(userIDs, message, noticeType);
        if (valid == "valid notification") { //validation succeeded
            if (noticeType == "email") {
                InternetAddress[] addresses;
                try {
                    addresses.addRecipients(Message.RecipientType.CC, userIDs);
                } 
                catch (AddressException e) {
                    return false;
                }
                EmailerThread emailerThread = new EmailerThread("NavUP Notification", message, addresses);
                emailerThread.run();
                return true;
            } 
            else if (noticeType == "sms") {
                InternetAddress[] addresses;
                try {
                    addresses = InternetAddress.parse("gladius.notification@gmail.com");
                } 
                catch (AddressException e) {
                    return false;
                }
                SMSerThread smserThread = new SMSerThread("NavUP Notification", message, addresses);
                smserThread.run();
                return true;
            }
        } else { //validation failed
            return false;
        }
        return false;
    }
    