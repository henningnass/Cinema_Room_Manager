package cinema;
import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void main(String[] args) {
        int numberRows;
        int numberSeatsEachRow;
        int menueItem = 0;

        char[][] seats;


        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        System.out.print(">");
        numberRows = scanner.nextInt();

        System.out.println();

        System.out.println("Enter the number of seats in each row:");
        System.out.print(">");
        numberSeatsEachRow = scanner.nextInt();

        seats = new char[numberRows][numberSeatsEachRow];
        initSeats(seats, numberRows, numberSeatsEachRow);

        System.out.println();

        do {
            printMenue();
            System.out.print(">");
            menueItem = scanner.nextInt();

            switch(menueItem) {
                case 1: printReservations(seats, numberRows, numberSeatsEachRow);
                    break;
                case 2: buyTicket(seats, numberRows, numberSeatsEachRow);
                    break;
                case 3: printStatistics(seats, numberRows, numberSeatsEachRow);
                    break;
                case 0: break;

            }
        } while (menueItem != 0);

    }

    private static void initSeats(char[][] seats, int numberRows, int numberSeatsEachRow) {
        for (int i = 1; i <= numberRows; i++) {
            for (int j = 1; j <= numberSeatsEachRow; j++) {
                seats[i-1][j-1] = 'S';
            }
        }
    }

    private static void buyTicket(char[][] seats, int numberRows, int numberSeatsEachRow) {
        Scanner scanner = new Scanner(System.in);
        int row;
        int seat;

        while (true) {
            System.out.println("Enter a row number:");
            System.out.print(">");
            row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            System.out.print(">");
            seat = scanner.nextInt();

            System.out.println();
            if (row < 1 || row > numberRows  || seat < 1 || seat > numberSeatsEachRow) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (seats[row-1][seat-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                System.out.println("Ticket price: $" + calculateTicketPrice(numberRows, numberSeatsEachRow, row, seat));
                seats[row-1][seat-1] = 'B';
                System.out.println();
                break;
            }
        }

    }

    private static void printStatistics(char[][] seats, int numberRows, int numberSeatsEachRow) {
        int numberOfPurchasedTickets = 0;
        double income = 0.0;
        double totalIncome = 0.0;
        double ticketPrice;
        double percentage;
        for (int r = 1; r <= numberRows; r++) {
            for (int s = 1; s <= numberSeatsEachRow; s++) {
                ticketPrice = calculateTicketPrice(numberRows, numberSeatsEachRow, r, s);
                totalIncome += ticketPrice;
                if (seats[r-1][s-1] == 'B') {
                    numberOfPurchasedTickets++;
                    income += ticketPrice;
                }
            }
        }
        percentage = (numberOfPurchasedTickets * 1.0) / (numberRows * numberSeatsEachRow) * 100.0;
        System.out.printf("Number of purchased tickets: %d", numberOfPurchasedTickets);
        System.out.println();
        System.out.printf("Percentage: %.2f%c", percentage, '%');
        System.out.println();
        System.out.printf("Current income: $%.0f", income);
        System.out.println();
        System.out.printf("Total income: $%.0f", totalIncome);
        System.out.println();
    }

    private static void printReservations(char[][] seats, int numberRows, int numberSeatsEachRow) {
        System.out.println("Cinema: ");
        System.out.print("  ");
        for (int pos = 1; pos <= numberSeatsEachRow; pos++) {
            System.out.print(pos + " ");
        }
        System.out.println();
        for (int row = 1; row <= numberRows; row++) {
            System.out.print(row + " ");
            for (int seat = 1; seat <= numberSeatsEachRow; seat++) {
                System.out.print(seats[row-1][seat-1] + " ");
            }
            System.out.println();
        }
    }

    private static double calculateTicketPrice(int numberRows, int numberSeatsEachRow, int row, int seat) {
        int totalNumberOfSeats;
        final int seatsInSmallRoomLimit = 60;

        final double priceSmallRoom = 10.0;
        final double priceLargeRoomReduced = 8.0;
        final double priceLargeRoom = 10.0;
        double price;

        totalNumberOfSeats = numberRows * numberSeatsEachRow;

        if(totalNumberOfSeats <= seatsInSmallRoomLimit) {
            price = priceSmallRoom;
        }
        else {
            price = (row <= numberRows / 2) ? priceLargeRoom : priceLargeRoomReduced;
        }

        return price;
    }

    private static void printMenue() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}
