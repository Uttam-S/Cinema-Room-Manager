package cinema;

import java.util.Scanner;

public class Cinema {
    private static String[][] cinemaHall;
    private static final Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int seats;
    private static int totalIncome;
    private static int currentIncome;
    private static int purchasedTicketsCount;

    public static void main(String[] args) {
        createCinemaHall();
        int input;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            input = scanner.nextInt();
            System.out.println();
            switch (input) {
                case 1:
                    printCinemaHall(cinemaHall);
                    break;
                case 2:
                    bookTicket(cinemaHall);
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        } while (input != 0);
    }
    public static void createCinemaHall() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        cinemaHall = new String[rows + 1][seats + 1];
        cinemaHall[0][0] = " ";
        for (int i = 1, cnt = 1; i <= rows; i++) {
            cinemaHall[i][0] = String.valueOf(cnt++);
        }
        for (int j = 1, cnt = 1; j <= seats; j++) {
            cinemaHall[0][j] = String.valueOf(cnt++);
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                cinemaHall[i][j] = "S";
            }
        }
        System.out.println();

        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontIncome = rows / 2 * seats * 10;
            int backIncome = (rows - rows / 2) * seats * 8;
            totalIncome = frontIncome + backIncome;
        }

    }
    public static void printCinemaHall(String[][] cinemaHall) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(cinemaHall[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void bookTicket(String[][] cinemaHall) {
        int rowNumber;
        int seatNumber;
        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber > rows || seatNumber > seats) {
                System.out.println("Wrong input!");
            } else if (cinemaHall[rowNumber][seatNumber].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }
        int ticketPrice;
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            if (rowNumber <= rows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        currentIncome += ticketPrice;
        purchasedTicketsCount += 1;
        System.out.println("Ticket price: $" + ticketPrice);
        cinemaHall[rowNumber][seatNumber] = "B";
    }
    public static void statistics() {
        System.out.println("Number of purchased tickets: " + purchasedTicketsCount);
        double percentageOfPurchased = 100.0 * purchasedTicketsCount / (rows * seats);
        System.out.printf("Percentage: %.2f%s%n", percentageOfPurchased, "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}