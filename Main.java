package VotingSystem;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        VotingSystem vs = new VotingSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== ONLINE VOTING SYSTEM ====");
            System.out.println("1. Admin Login");
            System.out.println("2. User");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    vs.adminMenu();
                    break;
                case 2:
                    vs.userMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
