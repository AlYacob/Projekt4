import java.io.*;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client {
    private static final String server_Adresse = "localhost";
    private static final int server_Port = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(server_Adresse, server_Port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            String auftrag;
            while ((auftrag = in.readLine()) != null && !auftrag.isEmpty()) {
                System.out.println(auftrag);
            }

            System.out.print("Geben Sie Ihre code ein: ");
            String code = consoleReader.readLine();
            out.println(code);

            String response = in.readLine();
            System.out.println(response);

        } catch (IOException e) {
            System.err.println("fehler bei der Kommunikation" + e.getMessage());
        }
    }
}