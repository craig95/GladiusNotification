##
# This script spawns a new thread every minute which will check a mailbox for new emails. The email's subject should
# be the phone number to which the SMS needs to be sent, the email body will be the content of the SMS and should be in
# plain text containing no special characters. For the SMS to be sent there needs to be a 3G modem plugged into a USB
# slot.
#
# @author   Craig van Heerden
# @version  1.0
# @since    7-04-2017
##

import serial
import sys
import imaplib
import os
from threading import Thread
import time

##
# This is the MailThread class that will check the mailbox for new emails. It uses IMAP to access the mailbox and sends
# commands to the 3G modem through the Raspberry Pi's ttyUSB0 serial port. For more information on the SMS and the 3G
# modem visit this website: https://sonnguyen.ws/send-sms-from-raspberry-pi-with-usb-3g/
##
class MailThread(Thread):
    ##
    # The constructor for MailThread
    # @param username the username that will be needed to connect to the Google IMAP server.
    # @param password the password that will be needed to connect to the Google IMAP server.
    # @param debug indicates if the script should output more information for debug purposes.
    # @param environment indicates which environment the script is being run, if "development" it will won't send a sms
    #                    but do everything up to that point to test the script. Else if "production" it will send a sms.
    ##
    def __init__(self, username, password, debug, environment):
        Thread.__init__(self)
        self.username = username
        self.password = password
        self.debug = debug
        self.environment = environment

    ##
    # The run function for the thread.
    ##
    def run(self):
        # Login to INBOX
        imap = imaplib.IMAP4_SSL("imap.gmail.com", 993)
        imap.login(self.username, self.password)
        imap.select()

        # Use search(), not status()
        (status, response) = imap.search(None, 'UNSEEN')
        unread_msg_nums = response[0].split()

        # Print the count of all unread messages
        if self.debug:
            print(len(unread_msg_nums))

        messages = []
        for e_id in unread_msg_nums:
            _, response = imap.fetch(e_id, '(BODY[HEADER.FIELDS (SUBJECT)] BODY[TEXT])')
            messages.append(response)

        for message in messages:
            print(((message[0][1]).decode()).rstrip() + ' to be sent to ' + (((message[1][1]).decode()).split())[1])
            if self.environment == "production":
                self.send_text((((message[1][1]).decode()).split())[1], ((message[0][1]).decode()).rstrip())

    #Function to send SMS
    def send_text(self, number, text, path='/dev/ttyUSB0'):
        ser = serial.Serial(path, timeout=1)
        # set text mode
        ser.write(str('AT+CMGF=%d\r' % 1).encode())
        # set number
        ser.write(str('AT+CMGS="%s"\r' % number).encode())
        # send message
        ser.write(str('%s\x1a' % text).encode())
        if self.debug:
            print(ser.readlines())
        ser.close()

##
# This is where the script will start. It requests the username and password from OS environment variables. If these
# are not present it will exit. It then checks the environments which it needs to be run in and if it needs to be run
# in debug mode. It then starts a infinite loop which spawns a new MailThread every 60 seconds and runs it.
##
try:
    username = os.environ['USERNAME']
    password = os.environ['PASSWORD']

    if str(sys.argv[1]).lower() == "development" or str(sys.argv[1]).lower() == "production":
        if str(sys.argv[2]).lower() == "true":
            debug = True
        elif str(sys.argv[2]).lower() == "false":
            debug = False
        else:
            print("Debug needs to be true or false")
            exit()
        threads = []
        print("Server Running")
        while True:
                newThread = MailThread(username, password, debug, sys.argv[1])
                newThread.start()
                threads.append(newThread)
                time.sleep(60)
    else:
        print("Not a valid environment.")
except:
    print("Please ensure you have set the USERNAME and PASSWORD environment variables.")