package tss.automation.utilities;
import jakarta.mail.*;
import jakarta.mail.search.SearchTerm;
import jakarta.mail.search.SubjectTerm;

import java.util.*;

public class FetchLatestESTACodeEmail {


    public static String findLatest4digitCode() {
        final String username = "viewsofttest@gmail.com";
        final String password = "msoahnoivxtxijil";  // Gmail App Password
        final String subjectText = "Validate Email Address with 4 Digit Code";

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.ssl.trust", "imap.gmail.com");

        int maxRetries = 12; // 12 * 5s = 60 seconds
        int delayMillis = 5000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Session session = Session.getInstance(props);
                Store store = session.getStore();
                store.connect("imap.gmail.com", username, password);

                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);

                SearchTerm subjectTerm = new SubjectTerm(subjectText);
                Message[] messages = inbox.search(subjectTerm);

                List<Message> filteredMessages = new ArrayList<>();
                for (Message msg : messages) {
                    Address[] froms = msg.getFrom();
                    if (froms != null && froms.length > 0 &&
                            froms[0].toString().toLowerCase().contains("@cbp.dhs.gov")) {
                        filteredMessages.add(msg);
                    }
                }

                if (!filteredMessages.isEmpty()) {
                    Message latestMessage = filteredMessages.get(filteredMessages.size() - 1);
                    String body = getTextFromMessage(latestMessage);
                    String code = extractFourCharCode(body);
                    if (code != null) {
                        System.out.println("Extracted 4-digit code: " + code);
                        return code;
                    }
                }

                inbox.close(false);
                store.close();

                System.out.println("Attempt " + attempt + ": Email not yet received, retrying in 5 seconds...");

                Thread.sleep(delayMillis); // wait before retrying
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("Failed to fetch OTP after multiple attempts.");
        return null;
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            String html = (String) message.getContent();
            return htmlToText(html);
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            return getTextFromMultipart(multipart);
        }
        return "[No text content found]";
    }

    private static String getTextFromMultipart(Multipart multipart) throws Exception {
        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart part = multipart.getBodyPart(i);
            if (part.isMimeType("text/plain")) {
                return (String) part.getContent();
            } else if (part.isMimeType("text/html")) {
                String html = (String) part.getContent();
                return htmlToText(html);
            } else if (part.getContent() instanceof Multipart) {
                String result = getTextFromMultipart((Multipart) part.getContent());
                if (result != null && !result.isEmpty()) {
                    return result;
                }
            }
        }
        return null;
    }

    private static String htmlToText(String html) {
        return html.replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", " ").trim();
    }

    private static String extractFourCharCode(String text) {
        String[] lines = text.split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].toLowerCase().contains("digit code")) {
                for (int j = i + 1; j < lines.length; j++) {
                    String candidate = lines[j].trim();
                    if (candidate.matches("^[A-Za-z0-9]{4}$")) {
                        return candidate;
                    }
                }
            }
        }
        return null;
    }
}
