package kz.kaznu.ruslan.clientservercalculator.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendCalculation(String calculation) throws IOException {
        writer.write(calculation);
        writer.newLine();
        writer.flush();
    }

    public String getResult() throws IOException {
        return reader.readLine();
    }

    public void close() {
        try {
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
