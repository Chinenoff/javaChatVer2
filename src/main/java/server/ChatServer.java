package server;

import client.entity.ChatMessage;
import network.TCPConnection;
import network.TCPConnectionListener;
import server.repository.Storage;
import server.service.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;


public class ChatServer implements TCPConnectionListener {

    private final static String CONFIG_FILE_PATH = "src/main/resources/configServer" +
            ".properties";
    private static final Logger logger = Logger.getInstance();
    private final Storage messages = new Storage();
    private final ArrayList<TCPConnection> connections = new ArrayList<>();
    private static int portServer;

    protected ChatServer(int portServer) {
        logger.log("Server running...");
        try (ServerSocket serverSocket = new ServerSocket(portServer)) {
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept());

                } catch (IOException e) {
                    logger.log("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ChatServer(readServerSettings(CONFIG_FILE_PATH));
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("User connected: " + tcpConnection);
        logger.log("User connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        ChatMessage chatMessage = parsStringToChatMessage(value);
        messages.getMessages().add(chatMessage);
        writeLogProgramm("[ " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(chatMessage.getDate()) + " ] " + chatMessage.getName() + " : " + chatMessage.getMessage());
        sendToAllConnections(chatMessageToString(chatMessage));
        logger.log("Message receive: " + chatMessage.getName() + " : " + chatMessage.getMessage());
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("User disconnected: " + tcpConnection);
        logger.log("User disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        logger.log("TCPConnection exception: " + e);
    }

    private void sendToAllConnections(String value) {
        for (TCPConnection connection : connections) connection.sendChatMessage(value);
    }

    private String chatMessageToString(ChatMessage chatMessage) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //logger.log( "[ " + value.getName() + " sendToAllConnections ] " + value.getMessage());
        return "[" + formatter.format(chatMessage.getDate()) + "]  [" + chatMessage.getName() +
                "] " + chatMessage.getMessage();
    }

    private ChatMessage parsStringToChatMessage(String value) {
        String resultName = value.substring(1, value.indexOf("]"));
        String resultMessage = value.substring(value.indexOf(":") + 2);
        return new ChatMessage(resultName, resultMessage);
    }

    private static int readServerSettings(String path) {
        try {
            FileInputStream propsInput = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(propsInput);
            portServer = Integer.parseInt(prop.getProperty("DB_PORT_SERVER"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return portServer;
    }

    public static void writeLogProgramm(String myTextLog) {
        File myLogFile = new File("src/main/java/server/repository/logMessage.txt");
        try (FileWriter writer = new FileWriter(myLogFile, true)) {
            // запись всей строки
            writer.write(myTextLog);
            // запись по символам
            writer.append('\n');
            writer.append('!');
            // дозаписываем и очищаем буфер
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
