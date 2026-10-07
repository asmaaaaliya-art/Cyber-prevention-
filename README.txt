SMS Guard - Beginner Android Project

WHAT IT DOES
- Requests RECEIVE_SMS and READ_SMS permission.
- Detects incoming SMS broadcasts.
- Counts messages by the sender ID/number supplied with the SMS.
- Flags a sender after 5 messages.
- Writes sender/count/message information to Android Logcat.

IMPORTANT ANDROID LIMITATION
This educational app does not claim to delete or prevent SMS delivery. Modern Android restricts SMS handling, and actual inbox-level blocking generally requires the appropriate default-SMS-handler role and additional implementation.

BUILD
1. Open this folder in Android Studio.
2. Let Gradle sync.
3. Run on a test Android device.
4. Grant SMS permission.
5. Send test SMS messages from another device.
6. Open Logcat and filter by SMS_GUARD.

TEST
Send 5 or more messages from the same test sender. Starting at message 5, the app flags the sender.

This app is intended for defensive spam detection and local logging.
