package banking;

import java.util.Scanner;

public class Banking {
    public static void main(String[] args) {
    	

        Database db = new Database();   
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Open Account (Create)");
            System.out.println("2. View Accounts (Read)");
            System.out.println("3. Update Balance (Update)");
            System.out.println("4. Close Account (Delete)");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Address: ");
                String addr = sc.nextLine();

                System.out.print("City: ");
                String city = sc.nextLine();

                System.out.print("Pincode: ");
                int pin = sc.nextInt();

                System.out.print("Initial Deposit: ");
                int dep = sc.nextInt();
                sc.nextLine(); 

                db.createAccount(name, addr, city, pin, dep);
            }

            else if (choice == 2) {
                db.listAccounts();
            }

            else if (choice == 3) {
                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter new total deposit: ");
                int amt = sc.nextInt();
                sc.nextLine(); 

                db.updateDeposit(name, amt);
            }

            else if (choice == 4) {
                System.out.print("Enter name to close: ");
                String name = sc.nextLine();

                db.closeAccount(name);
            }

            else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }

            else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
