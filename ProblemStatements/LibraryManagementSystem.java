import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Book {
    String id;
    String title;
    String author;
    boolean isAvailable;

    Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}

class BorrowRecord {
    String bookId;
    String userId;
    LocalDate borrowDate;
    LocalDate dueDate;

    BorrowRecord(String bookId, String userId) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14); // 2 weeks
    }
}

class Library {
    Map<String, Book> books = new HashMap<>();
    Map<String, BorrowRecord> borrowed = new HashMap<>(); // bookId -> record

    public void addBook(Book book) {
        books.put(book.id, book);
        System.out.println("Book added: " + book.title);
    }

    public void listBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books.values()) {
            System.out.println(book.id + " - " + book.title + " by " + book.author +
                    (book.isAvailable ? " [Available]" : " [Borrowed]"));
        }
    }

    public void borrowBook(String bookId, String userId) {
        if (!books.containsKey(bookId)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(bookId);
        if (!book.isAvailable) {
            System.out.println("Book is already borrowed.");
            return;
        }

        book.isAvailable = false;
        borrowed.put(bookId, new BorrowRecord(bookId, userId));
        System.out.println("Book borrowed successfully! Due Date: " + borrowed.get(bookId).dueDate);
    }

    public void returnBook(String bookId) {
        if (!borrowed.containsKey(bookId)) {
            System.out.println("This book was not borrowed.");
            return;
        }

        BorrowRecord record = borrowed.get(bookId);
        LocalDate returnDate = LocalDate.now();
        long daysLate = ChronoUnit.DAYS.between(record.dueDate, returnDate);
        double fine = daysLate > 0 ? daysLate * 5 : 0;

        borrowed.remove(bookId);
        books.get(bookId).isAvailable = true;

        System.out.println("Book returned. " + (fine > 0 ? "Late Fee: ₹" + fine : "No late fee."));
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        lib.addBook(new Book("B101", "Atomic Habits", "James Clear"));
        lib.addBook(new Book("B102", "The Alchemist", "Paulo Coelho"));
        lib.addBook(new Book("B103", "Java Programming", "Herbert Schildt"));

        while (true) {
            System.out.println("\n1. List Books\n2. Borrow Book\n3. Return Book\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                lib.listBooks();
            } else if (choice == 2) {
                System.out.print("Enter Book ID: ");
                String bookId = sc.nextLine();
                System.out.print("Enter Your User ID: ");
                String userId = sc.nextLine();
                lib.borrowBook(bookId, userId);
            } else if (choice == 3) {
                System.out.print("Enter Book ID to return: ");
                String bookId = sc.nextLine();
                lib.returnBook(bookId);
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }

        sc.close();
    }
}

