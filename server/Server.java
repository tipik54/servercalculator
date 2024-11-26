package kz.kaznu.ruslan.clientservercalculator.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            handleClient(socket);
        }
    }

    public static void handleClient(Socket socket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            // Читаем запрос от клиента
            String input = reader.readLine();
            System.out.println("Received from client: " + input);

            // Выполняем вычисления
            String response = calculate(input);

            // Отправляем результат клиенту
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String calculate(String input) {
        if (input.length() >= 3) {
            try {
                double num1 = Double.parseDouble(input.substring(0, input.indexOf('+')));
                char operator = input.charAt(input.indexOf('+'));
                double num2 = Double.parseDouble(input.substring(input.indexOf('+') + 1));
                // Можно добавлять другие операторы, например, '-', '*', '/'
                return performOperation(num1, operator, num2);
            } catch (NumberFormatException e) {
                return "Error: Invalid number format";
            }
        } else {
            return "Error: Invalid input format";
        }
    }

    private static String performOperation(double num1, char operator, double num2) {
        switch (operator) {
            case '+':
                return String.valueOf(num1 + num2);
            case '-':
                return String.valueOf(num1 - num2);
            case '*':
                return String.valueOf(num1 * num2);
            case '/':
                if (num2 != 0) {
                    return String.valueOf(num1 / num2);
                } else {
                    return "Error: деление на ноль";
                }
            default:
                return "Error: Нет такой функции";
        }
    }

}

