import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;

public class NetworkTest {

    @Test
    public void test01() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }

    @Test
    public void test02()throws UnknownHostException{
        InetAddress atguigu = InetAddress.getByName("www.atguigu.com");
        System.out.println(atguigu);
    }

    @Test
    public void test03()throws UnknownHostException{
//		byte[] addr = {112,54,108,98};
        byte[] addr = {(byte)192,(byte)168,24,56};
        InetAddress atguigu = InetAddress.getByAddress(addr);
        System.out.println(atguigu);
    }

    @Test
    public void test_01(){
        try (Socket socket = new Socket("localhost", 8080)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello, Server!");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("Server says: " + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_02(){
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is listening on port 8080");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("Client says: " + reader.readLine());

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("Hello, Client!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_03(){
        try (DatagramSocket socket = new DatagramSocket(8080)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Server is listening on port 8080");
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Client says: " + received);

            String response = "Hello, Client!";
            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                    responseData, responseData.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_04(){
        try (DatagramSocket socket = new DatagramSocket()) {
            String message = "Hello, Server!";
            byte[] buffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 8080);
            socket.send(packet);

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server says: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_05() throws UnknownHostException{
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        InetAddress google = InetAddress.getByName("www.google.com");
        System.out.println(baidu);
        System.out.println(google);
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Waiting for connect ...");

            Socket socket = serverSocket.accept();
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println(inetAddress.getHostAddress() + "客户端连接成功！！");
            InputStream input = socket.getInputStream();

            byte[] data = new byte[1024];
            StringBuilder s = new StringBuilder();
            int len;
            while ((len = input.read(data)) != -1) {
                s.append(new String(data, 0, len));
            }
            System.out.println(inetAddress.getHostAddress() + "客户端发送的消息是：" + s);

            //4、获取输出流，用来发送数据给该客户端
            OutputStream out = socket.getOutputStream();
            //发送数据
            out.write("欢迎登录".getBytes());
            out.flush();
            socket.close();



        }
    }
}