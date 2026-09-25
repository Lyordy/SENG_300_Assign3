public class ArrayListTest {

    public static void main(String[] args) {

        // Create the ArrayList book collection
        ArrayListBooks books = new ArrayListBooks();

        // Load books from books.csv
        books.loadBooks("books.csv");

        // Display how many books were loaded
        System.out.println("Number of books loaded: " + books.size());

        // Display the first 10 books
        System.out.println("\nFirst 10 books:");

        for (int i = 0; i < Math.min(10, books.size()); i++) {

            Book book = books.get(i);

            System.out.println(
                    (i + 1) + ". "
                    + book.getTitle()
                    + " | "
                    + book.getAuthors()
                    + " | "
                    + book.getOriginalPublicationYear()
            );
        }

        // Test searching by Book ID
        System.out.println("\nTesting search by Book ID:");

        Book foundBook = books.findById(1);

        if (foundBook != null) {
            System.out.println("Found: " + foundBook);
        } else {
            System.out.println("Book ID 1 was not found.");
        }

        // Test searching by ISBN
        System.out.println("\nTesting search by ISBN:");

        Book foundISBN = books.findByISBN("0439023483");

        if (foundISBN != null) {
            System.out.println("Found: " + foundISBN);
        } else {
            System.out.println("ISBN was not found.");
        }

        System.out.println("\nArrayList test finished.");
    }
}