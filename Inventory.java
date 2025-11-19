import java.util.*;

public class Inventory {
    private LinkedList<Book> books = new LinkedList<>();
    private Queue<String> orderQueue = new LinkedList<>();

    public void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter book price: ");
        double price = Double.parseDouble(sc.nextLine());

        Book book = new Book(title, author, isbn, price);
        books.add(book);
        System.out.print("Book added successfully!\n\n");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.print("No books in inventory.\n\n");
            return;
        }
        System.out.print("--- All Books in Inventory ---\n");
        for (int i = 0; i < books.size(); i++) {
            System.out.print(books.get(i) + "\n");
        }
        System.out.print("-----------------------------\n\n");
    }

    public void sortBooksByTitle() {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                if (books.get(j).getTitle().compareToIgnoreCase(books.get(j + 1).getTitle()) > 0) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
        System.out.print("Sorting books by title...\nBooks sorted successfully!\n\n");

        for (int i = 0; i < books.size(); i++) {
            System.out.print(books.get(i) + "\n");
        }
        System.out.print("-----------------------------\n\n");
    }

    public void searchBookByTitle(Scanner sc) {
        System.out.print("Enter the title of the book to search for: ");
        String title = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                System.out.print("Book found:\n" + books.get(i) + "\n\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.print("Book not found.\n\n");
        }
    }

    public void addOrderToQueue(Scanner sc) {
        System.out.print("Enter the title of the book to order: ");
        String title = sc.nextLine();
        orderQueue.add(title);
        System.out.print("Order for \"" + title + "\" has been added to the queue.\n\n");
    }

    public void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.print("No orders to process.\n\n");
            return;
        }
        String title = orderQueue.poll();
        System.out.print("Processing next order...\nProcessed order for: " + title + "\n\n");
    }

    public void displayMenu() {
        System.out.print("Please choose an option:\n");
        System.out.print("1. Add a new book\n");
        System.out.print("2. Display all books\n");
        System.out.print("3. Sort books by title\n");
        System.out.print("4. Search for a book by title\n");
        System.out.print("5. Add a customer order to the queue\n");
        System.out.print("6. Process the next customer order\n");
        System.out.print("7. Exit\n");
        System.out.print("\nEnter your choice: ");
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.print("\nWelcome to the Bookstore Inventory Management System!\n\n");

        while (choice != 7) {
            inventory.displayMenu();

            try {
                choice = Integer.parseInt(sc.nextLine());
                System.out.print("\n");

                switch (choice) {
                    case 1: inventory.addBook(sc);
                        break;
                    
                    case 2: inventory.displayAllBooks();
                        break;
                    
                    case 3: inventory.sortBooksByTitle();
                        break;
                    
                    case 4: inventory.searchBookByTitle(sc);
                        break;
                    
                    case 5: inventory.addOrderToQueue(sc);
                        break;
                    
                    case 6: inventory.processNextOrder();
                        break;
                    
                    case 7:
                        System.out.print("Thank you for using the Bookstore Inventory Management System!\n");
                        break;
                    
                    default: 
                        System.out.print("Invalid choice. Please try again.\n\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number from 1 to 7.\n\n");
            }
        }

        sc.close();
    }
}
