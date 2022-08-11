package client;

import network.TCPConnection;
import network.TCPConnectionListener;
import server.service.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {

    private final static String CONFIG_FILE_PATH = "src/main/resources/configClient" +
            ".properties";

    private static String ipAddr;
    private static int portClient;
    private static int width;
    private static int height;
    private static final Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        readClientSettings(CONFIG_FILE_PATH);
        SwingUtilities.invokeLater(() -> new ClientWindow());
    }

    private final JTextArea log = new JTextArea();
    private final JTextField fieldNickName = new JTextField("");
    private final JTextField fieldInput = new JTextField();

    private TCPConnection connection;

    private ClientWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        log.setEditable(false);
        log.setLineWrap(true);
        add(log, BorderLayout.CENTER);
        fieldInput.addActionListener(this);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldNickName, BorderLayout.NORTH);

        setVisible(true);
        try {
            connection = new TCPConnection(this, ipAddr, portClient);
        } catch (IOException e) {
            printMsg("TCPConnection exception: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        String msgNickName = fieldNickName.getText();

        if (msg.equalsIgnoreCase("/exit")) {
            onDisconnect(connection);
            System.exit(0);
        }

        if (msgNickName.equals("") || msgNickName.equalsIgnoreCase("login")) {
            /*printMsgLogin*/
            printMsg("Please enter your username.");
            fieldNickName.setText("login");
            logger.log("No login. Please enter your username.");
            return;
        }
        connection.setNameConnection(msgNickName);
        logger.log("Login entered: " + fieldNickName.getText());
        if (msg.equals("")) return;
        fieldInput.setText(null);
        connection.sendChatMessage("[" + fieldNickName.getText() + "]: " + msg);
    }

    private static void readClientSettings(String path) {

        Properties prop = null;
        try {
            FileInputStream propsInput = new FileInputStream(path);
            prop = new Properties();
            prop.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ipAddr = prop.getProperty("DB_IP_ADDR");
        portClient = Integer.parseInt(prop.getProperty("DB_PORT"));
        width = Integer.parseInt(prop.getProperty("DB_WIDTH"));
        height = Integer.parseInt(prop.getProperty("DB_HEIGHT"));
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMsg("Connection ready...");
        logger.log("Server connection established");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
        logger.log("Message received: " + value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMsg("Connection close");
        logger.log("Server disconnection");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("TCPConnection exception: " + e);
        logger.log("Error " + e);
    }

    private synchronized void printMsg(String msg) {
        SwingUtilities.invokeLater(() -> {
            log.append(msg + "\n");
            log.setCaretPosition(log.getDocument().getLength());
        });
    }
}
