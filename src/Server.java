import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 12345;
    private static final String code = "008";
    private static final String File_path = "Auftrag.txt";

    private static String lesenAuftrag() throws IOException {
        StringBuilder auftrag = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(Server.File_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                auftrag.append(line).append("\n");
            }
        }
        return auftrag.toString();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server startet. warte auf Verbindungen");

            while (true)
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {
                System.out.println("client verbenden.");

                String auftrag = lesenAuftrag();
                out.println(auftrag);

                String clientCode = in.readLine();
                if (code.equals(clientCode)) {
                    out.println("viel erfolg");
                }
                else {
                    out.println("falsche code");
                }
            } catch (IOException e) {
                System.err.println("fehler bei Connection mit client " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("fehler beim Server" + e.getMessage());
        }
    }


}