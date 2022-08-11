package server.repository;

import client.entity.ChatMessage;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Storage {
    private final Queue<ChatMessage> messages = new ConcurrentLinkedQueue<>();

    public Queue<ChatMessage> getMessages() {
        return messages;
    }

    public String showMessage(ChatMessage message) {
        return String.format("**%s**: %s", message.getName(), message.getMessage());
    }
}
