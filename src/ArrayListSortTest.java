public class ArrayListSortTest {

    public static void main(String[] args) {

        ArrayListBooks books = new ArrayListBooks();

        books.loadBooks("books.csv");

        System.out.println("Books loaded: " + books.size());

        System.out.println("\n--- Before Sorting ---");

        for (int i = 0; i < 5; i++) {
            System.out.println(books.get(i));
        }

        // Test author ascending
        BookSorter.sortByAuthorAscending(books.getBooks());

        System.out.println("\n--- Author Ascending ---");

        for (int i = 0; i < 5; i++) {
            System.out.println(books.get(i));
        }

        // Test author descending
        BookSorter.sortByAuthorDescending(books.getBooks());

        System.out.println("\n--- Author Descending ---");

        for (int i = 0; i < 5; i++) {
            System.out.println(books.get(i));
        }

        // Test year ascending
        BookSorter.sortByYearAscending(books.getBooks());

        System.out.println("\n--- Year Ascending ---");

        for (int i = 0; i < 5; i++) {
            System.out.println(books.get(i));
        }

        // Test year descending
        BookSorter.sortByYearDescending(books.getBooks());

        System.out.println("\n--- Year Descending ---");

        for (int i = 0; i < 5; i++) {
            System.out.println(books.get(i));
        }

        System.out.println("\nArrayList + Sorting test finished.");
    }
}