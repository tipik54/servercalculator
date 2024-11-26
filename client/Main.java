package kz.kaznu.ruslan.clientservercalculator.client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {

            Client client = new Client("localhost", 8088);
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите первое число:");
            String num1 = scanner.nextLine();
            System.out.println("Введите второе число:");
            String num2 = scanner.nextLine();
            System.out.println("Введите математическую операцию (+, -, *, /):");
            String operator = scanner.nextLine();

            String calculation = num1 + operator + num2;

            client.sendCalculation(calculation);

            String result = client.getResult();
            System.out.println("Результат: " + result);


            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

