package network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPConnection {
    private final Socket socket;
    private final Thread receiveThread;
    private final TCPConnectionListener eventListener;
    private final BufferedReader in;
    private final BufferedWriter out;
    private String nameConnection;

    public TCPConnection(TCPConnectionListener eventListener, String ipAddr, int port) throws IOException {
        this(eventListener, new Socket(ipAddr, port));
    }

    public TCPConnection(TCPConnectionListener eventListener, Socket socket) throws IOException { //, String nickName
        this.eventListener = eventListener;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),
                StandardCharsets.UTF_8));

        receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPConnection.this);
                    while (!receiveThread.isInterrupted()) {
                        String msg = in.readLine();
                        eventListener.onReceiveString(TCPConnection.this, msg);
                    }
                } catch (IOException e) {
                    eventListener.onException(TCPConnection.this, e);
                } finally {
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }
        });
        receiveThread.start();
    }

    public synchronized void sendChatMessage(String chatMessage) {
        try {
            out.write(chatMessage + "\r" + "\n");
            out.flush();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect() {
        receiveThread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this, e);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }

    public String getNameConnection() {
        return nameConnection;
    }

    public void setNameConnection(String nameConnection) {
        this.nameConnection = nameConnection;
    }
}