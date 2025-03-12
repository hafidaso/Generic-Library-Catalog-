import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The LibraryItem class represents a generic library item.
 *
 * @param <T> the type used for itemID (for example, Integer, String, etc.)
 */
class LibraryItem<T extends Comparable<T>> {
    private T itemID;
    private String title;
    private String author;

    public LibraryItem(T itemID, String title, String author) {
        this.itemID = itemID;
        this.title = title;
        this.author = author;
    }

    public T getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "LibraryItem [ID=" + itemID + ", Title=" + title + ", Author=" + author + "]";
    }

    /**
     * Equality based on itemID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryItem<?> other = (LibraryItem<?>) obj;
        return itemID.equals(other.itemID);
    }

    @Override
    public int hashCode() {
        return itemID.hashCode();
    }
}

/**
 * The GenericCatalog class provides a generic collection for library items.
 *
 * @param <E> the type of library items stored which must extend LibraryItem<?>
 */
class GenericCatalog<E extends LibraryItem<?>> {
    private ArrayList<E> items;

    public GenericCatalog() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the catalog.
     *
     * @param item the library item to add
     */
    public void addItem(E item) {
        items.add(item);
        System.out.println("Item added successfully: " + item);
    }

    /**
     * Removes an item from the catalog using its itemID.
     *
     * @param itemID the ID of the item to remove
     */
    public void removeItem(Object itemID) {
        E foundItem = null;
        for (E item : items) {
            if (item.getItemID().equals(itemID)) {
                foundItem = item;
                break;
            }
        }
        if (foundItem != null) {
            items.remove(foundItem);
            System.out.println("Item removed successfully: " + foundItem);
        } else {
            System.out.println("Error: Item with ID '" + itemID + "' not found in the catalog.");
        }
    }

    /**
     * Returns the details of the item with the specified itemID.
     *
     * @param itemID the item's ID
     * @return the library item if found, or null if not found
     */
    public E getItem(Object itemID) {
        for (E item : items) {
            if (item.getItemID().equals(itemID)) {
                return item;
            }
        }
        System.out.println("Error: Item with ID '" + itemID + "' not found in the catalog.");
        return null;
    }

    /**
     * Displays all items in the catalog.
     */
    public void displayCatalog() {
        if (items.isEmpty()) {
            System.out.println("The catalog is currently empty.");
        } else {
            System.out.println("Current Catalog Items:");
            for (E item : items) {
                System.out.println(item);
            }
        }
    }
}

/**
 * The LibraryCatalogApp class provides the command-line user interface and main method.
 */
public class LibraryCatalogApp {
    private static GenericCatalog<LibraryItem<?>> catalog = new GenericCatalog<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("Welcome to the Generic Library Catalog System!");

        while (choice != 0) {
            printMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        addLibraryItem(scanner);
                        break;
                    case 2:
                        removeLibraryItem(scanner);
                        break;
                    case 3:
                        viewCatalog();
                        break;
                    case 0:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Input error: Please enter a valid number.");
                scanner.nextLine(); // clear the invalid input
            }
        }

        scanner.close();
    }


    private static void printMenu() {
        System.out.println("\nLibrary Catalog Menu:");
        System.out.println("1. Add a new library item");
        System.out.println("2. Remove a library item");
        System.out.println("3. View current catalog");
        System.out.println("0. Exit");
    }

    /**
     * Prompts the user to add a new library item.
     * Accepts itemID, title, and author from the command line.
     *
     * @param scanner the Scanner object for user input
     */
    private static void addLibraryItem(Scanner scanner) {
        try {
            System.out.print("Enter item ID (integer): ");
            Integer id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            // Create new LibraryItem with Integer as ID
            LibraryItem<Integer> newItem = new LibraryItem<>(id, title, author);
            catalog.addItem(newItem);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input for item ID. Please enter an integer.");
        }
    }

    /**
     * Prompts the user to remove a library item from the catalog.
     *
     * @param scanner the Scanner object for user input
     */
    private static void removeLibraryItem(Scanner scanner) {
        try {
            System.out.print("Enter the item ID to remove (integer): ");
            Integer id = Integer.parseInt(scanner.nextLine());
            catalog.removeItem(id);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input for item ID. Please enter an integer.");
        }
    }

    private static void viewCatalog() {
        catalog.displayCatalog();
    }
}
