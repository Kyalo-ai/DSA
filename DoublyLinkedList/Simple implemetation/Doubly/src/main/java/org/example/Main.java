package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        DoublyBrowser browser = new DoublyBrowser();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("=== Simple Browser ===");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Visit new page");
            System.out.println("2. Go back");
            System.out.println("3. Go forward");
            System.out.println("4. Show current");
            System.out.println("5. Show history");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("URL: ");
                    String url = scanner.nextLine();
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    browser.visit(url, title);
                    break;

                case 2:
                    browser.back();
                    break;

                case 3:
                    browser.forward();
                    break;

                case 4:
                    browser.showCurrent();
                    break;

                case 5:
                    browser.showHistory();
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
