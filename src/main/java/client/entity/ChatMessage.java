package client.entity;

import java.util.Date;

public class ChatMessage {
    private final String name;
    private final String message;
    private final Date date;

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChatMessage{");
        sb.append("name='").append(name).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
