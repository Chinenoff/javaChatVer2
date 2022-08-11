package server;

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

class ChatServerTest extends Assert {

    /**
     * Set up logic.
     * @throws Exception Thrown in case of any error.
     */
    @Before
    public void beforeTest() throws Exception {
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

    /**
     * Example test method.
     */
    @Test
    public void testServer() {
        boolean stop = false;
        ServerSocket serverSocket = null;
        final String hostName = "127.0.0.2";
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
    }

    //****************

    //private static ChatServer chatServer;
    private static ArrayList<TCPConnection> connections;
    private static Storage messages;
    private final static String CONFIG_FILE_PATH = "src/main/resources/configServer" +
            ".properties";
    private static int portServer;
    private Object TCPConnection;

    /*@BeforeClass
    public static void testMethod() throws IOException {
        //chatServer = new ChatServer();
        //connections = new ArrayList<>();
        //messages = new Storage();
        //TCPConnection tcpConnection = new TCPConnection(chatServer, new Socket("localhost",
        // 4004));
        *//*Mockito.mock(TCPConnection.class);*//*
        *//*Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));*//*
        *//*Mockito.when(new TCPConnection(new ChatServer(), new Socket("localhost", 4004) ))
                .thenReturn(new TCPConnection(new ChatServer(), new Socket("localhost", 4004) ));*//*
    }

    @BeforeAll
    static void setUp() {
        *//*chatServer = new ChatServer();
        connections = new ArrayList<>();
        messages = new Storage();*//*
    }

    *//*@BeforeEach
    void setUp() {
    }*//*

    @AfterEach
    void tearDown() {
    }*/

    @Test
    @DisplayName("Тест метода main")
    void main() {
        //файл существует
        Assertions.assertTrue(new File("src/main/resources/configServer.properties").isFile());
        //файл может быть прочитан
        Assertions.assertTrue(new File("src/main/resources/configServer.properties").canRead());
    }

    /*@Test
    void onConnectionReady() throws IOException {
       *//* @Mock
        TCPConnection tcpConnection;*//*
        //TCPConnection tcpConnection = mock(TCPConnection.class);
        ChatServer chatServer = Mockito.mock(ChatServer.class);
        *//*ChatServer serverMock = mock(ChatServer.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");*//*
        //when(i.next()).thenReturn("Hello").thenReturn("World");
        //Mockito.mock(TCPConnection.class);
        //TCPConnection tcpConnection = new TCPConnection(chatServer, new Socket("localhost",
        // 4004));
        //Mockito.when(new TCPConnection(new ChatServer(), new Socket("localhost", 4004) ))
               // .thenReturn(tcpConnection);



        ArrayList<TCPConnection> connectionsExpected = new ArrayList<>();
        ArrayList<TCPConnection> connectionsActual = new ArrayList<>();

        int expectedNull = connectionsExpected.size();
        //chatServer.onConnectionReady(new TCPConnection(2));
        int actualNull = connectionsActual.size();
        Assert.assertEquals(expectedNull, actualNull);

        //connectionsExpected.add(tcpConnection);
        //int expected = connectionsExpected.size();
        *//*chatServer.onConnectionReady(tcpConnection);
        int actual = connectionsActual.size();
        Assert.assertEquals(expected, actual);*//*

        //eventListener.onConnectionReady(TCPConnection.this);
        //TCPConnection tcpConnection = new TCPConnection(new Socket(1));
        //assertEquals(2, math.getCalls());
        //assertsEquals([String message], expected, actual
    }

    @Test
    void onReceiveString() {
    }

    @Test
    void onDisconnect() {
    }

    @Test
    void onException() {
    }

    @Test
    void writeLogProgramm() {
    }*/
}