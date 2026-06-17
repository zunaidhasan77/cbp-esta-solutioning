package tss.automation.utilities;
import jakarta.mail.*;
import java.util.Properties;
public class EmptyInbox {

    public static void emptyEmail() {
        final String username = "viewsofttest@gmail.com";
        final String password = "msoahnoivxtxijil";  // your Gmail app password

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.ssl.trust", "imap.gmail.com");

        try {
            Session session = Session.getInstance(props);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.getMessages();
            System.out.println("Total messages to delete: " + messages.length);

            for (Message message : messages) {
                message.setFlag(Flags.Flag.DELETED, true);
            }

            // This will permanently delete messages marked DELETED
            inbox.close(true);
            store.close();

            System.out.println("Inbox emptied successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
