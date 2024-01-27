package org.cinema;

import java.util.Scanner;

public class Main {
    static String[][] state;
    static int numberOfRows;
    static int numberOfSeats;
    static Scanner scanner = new Scanner(System.in);
    static int sumDollarsForTickets;
    static int price;

    public static void main(String[] args) {
        initState();
        int digit = Integer.MIN_VALUE;
        while (digit != 0) {
            System.out.println("1. Show the seats\n" + "2. Buy a ticket\n" + "3. Statistics\n" + "0. Exit");
            digit = scanner.nextInt();
            switch (digit) {
                case 1:
                    printSeats();
                    System.out.println();
                    break;
                case 2:
                    buyTicket();
                    System.out.println();
                    break;
                case 3:
                    currentIncome();
                    break;
                case 0:
                default:
                    break;
            }
        }

    }

    private static void initState() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        if (numberOfRows > 9 || numberOfRows < 0) {
            System.exit(1);
        }
        System.out.println("Enter the number of seats in each row:");
        numberOfSeats = scanner.nextInt();
        if (numberOfSeats > 9 || numberOfSeats < 0) {
            System.exit(1);
        }
        state = new String[numberOfRows][numberOfSeats];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                state[i][j] = "S";
            }
        }
    }

    public static void printSeats() {
        System.out.println("Cinema:");
        for (int i = 1; i <= numberOfSeats; i++) {
            if (i == 1) {
                System.out.print("  " + i);
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < numberOfSeats; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void currentIncome() {
        double percentage;
        int totalIncome;
        int numberOfPurchasedTickets = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                if (state[i][j] == "B") {
                    numberOfPurchasedTickets++;
                }
            }
        }
        int totalSeats = numberOfRows * numberOfSeats;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int firstRows = numberOfRows / 2;
            int secondRows = numberOfRows - firstRows;
            totalIncome = firstRows * numberOfSeats * 10 + secondRows * numberOfSeats * 8;
        }
        percentage = (double) numberOfPurchasedTickets * 100 / totalSeats;
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.print("Percentage: ");
        System.out.printf("%.2f", percentage);
        System.out.print("%\n");
        System.out.println("Current income: $" + sumDollarsForTickets);
        System.out.println("Total income: $" + totalIncome + "\n");
    }

    public static void buyTicket() {
        while (true) {
            System.out.println("Enter the number of rows:");
            int numberOfRows = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            int numberOfSeats = scanner.nextInt();
            if (numberOfRows > 9 || numberOfRows <= 0 || numberOfSeats > 9 || numberOfSeats <= 0) {
                System.out.println("Wrong input!");
                return;
            }
            if (state[numberOfRows - 1][numberOfSeats - 1] == "S") {
                state[numberOfRows - 1][numberOfSeats - 1] = "B";
                ticketPrice(numberOfRows);
                sumDollarsForTickets = sumDollarsForTickets + price;

                return;
            } else {
                System.out.println("That ticket has already been purchased!\n");
            }
        }
    }

    public static void ticketPrice(int rowNumber) {
        int totalSeats = numberOfRows * numberOfSeats;
        if (totalSeats <= 60) {
            price = 10;
        } else {
            int firstRows = numberOfRows / 2;
            if (rowNumber <= firstRows) {
                price = 10;
            } else price = 8;
        }
        System.out.println("Ticket price: $" + price);
    }
}
