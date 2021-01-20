package cinema;

import java.util.*;

public class Cinema {

    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static char[][] createCinema(int rows, int seatsInRow) {
        char[][] cinema = new char[rows][seatsInRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInRow; j++) {
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }
    
    public static void showSeats(char[][] cinema, int rows, int seatsInRow) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsInRow; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < rows; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < seatsInRow; j++) {
                System.out.printf("%c ", cinema[i][j]);
            }
            System.out.print("\n");
        }
    }
    
    public static int buyTicket(char[][] cinema, int rows, int seatsInRow, int rowNumber, int seatNumber) {
        System.out.print("\nTicket Price: ");
        if (rows * seatsInRow <= 60) {
            System.out.print("$10\n");
            cinema[rowNumber - 1][seatNumber - 1] = 'B';
            return 10;
        } else {
            if (rowNumber <= rows / 2) {
                System.out.print("$10");
                cinema[rowNumber - 1][seatNumber - 1] = 'B';
                return 10;   
            } else {
                System.out.print("$8");
                cinema[rowNumber - 1][seatNumber - 1] = 'B';
                return 8; 
            }
        }
    }
    
    public static int calculateTotalIncome(int rows, int seatsInRow) {
        if (rows * seatsInRow <= 60) {
            return 10 * rows * seatsInRow;
        } else {
            return (10 * (rows / 2) * seatsInRow) + ((rows - (rows / 2)) * 8 * seatsInRow);
        }
    }
    
    public static void main(String[] args) {
        Scanner scannner = new Scanner(System.in);
        
        System.out.println("Enter the number of rows:");
        int rows = scannner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scannner.nextInt();
        
        char[][] cinema = createCinema(rows, seatsInRow);
        int rowNumber = 0;
        int seatNumber = 0;
        int noOfTickets = 0;
        int currentIncome = 0;
        boolean repeat = false;
        boolean flag = true;        
        do {
            printMenu();
            int choice = scannner.nextInt();
            switch (choice) {
                case 1: showSeats(cinema, rows, seatsInRow);
                break;
                case 2: do {
                            System.out.println("\nEnter a row number:");
                            rowNumber = scannner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            seatNumber = scannner.nextInt();
                            if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seatsInRow) {
                                System.out.println("\nWrong input!");
                                repeat = true;
                            } else if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
                                System.out.println("\nThat ticket has already been purchased!");
                                repeat = true;
                            } else {
                                repeat = false;
                            }
                        } while (repeat);
                        noOfTickets++;
                        currentIncome += buyTicket(cinema, rows, seatsInRow, rowNumber, seatNumber);
                break;   
                case 3: System.out.println("\nNumber of purchased tickets: " + noOfTickets);
                        double perc = (noOfTickets * 1.0 / (rows * seatsInRow)) * 100;
                        System.out.printf("Percentage: %.2f%s", perc, "%");
                        System.out.printf("\nCurrent income: $%d", currentIncome);
                        System.out.printf("\nTotal income: $%d\n", calculateTotalIncome(rows, seatsInRow));
                break;
                case 0: flag = false; 
                break;       
            }
        } while (flag);
    }
}