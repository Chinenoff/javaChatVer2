package server;

import client.ClientWindow;
import network.TCPConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import server.repository.Storage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.mock;

public class ChatServerAllTest {
    /**
     * Set up logic.
     * @throws Exception Thrown in case of any error.
     */
    @Before
    public void beforeTest() throws Exception {
        //ChatServer chatServer = new ChatServer(23555);
        System.out.println("Preparing for test execution: " + getClass().getSimpleName());
    }

    /**
     * Tear down logic.
     * @throws Exception Thrown in case of any error.
     */
    @After
    public void afterTest() throws Exception {
        System.out.println("Tearing down test execution: " + getClass().getSimpleName());
    }

    @Test
    @DisplayName("Тест OnConnectionReady")
    public void OnConnectionReady() {
        //ChatServer chatServer = new ChatServer(23555);
        Mockito.mock(ClientWindow.class);
        //TCPConnection tcpConnection = new TCPConnection(chatServer, new Socket("localhost",
        // 4004));
        //Mockito.mock(TCPConnection.class);
       /* //ChatServer chatServer = new ChatServer();
        boolean stop = false;
        ServerSocket serverSocket = null;
        final String hostName = "127.0.0.1";
        try {
            InetAddress addr = InetAddress.getLocalHost();
// Get IP Address
            byte[] ipAddr = addr.getAddress();
            // Get hostname
            if (hostName.compareTo(addr.getHostName())==0){
                serverSocket = new ServerSocket(4000);
                while (!stop){
                    Socket client =  serverSocket.accept();
                    System.out.println(getClass().getSimpleName() +" Incomming DATA! " + client.getInetAddress());
                    ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                    final Date date  = (Date)in.readObject();
                    System.out.println(getClass().getSimpleName() +" Socket read Object from client " + date);
                    in.close();
                    out.close();
                    stop = true;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getClass().getSimpleName() + "Output from TestC.testMethod1().");
    }*/
    }
}
