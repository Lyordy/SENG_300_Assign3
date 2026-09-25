public class PerformanceTest {

    public static void main(String[] args) {

        int numberOfSearches = 1000;

        // Load ArrayList
        ArrayListBooks arrayBooks = new ArrayListBooks();
        arrayBooks.loadBooks("books.csv");

        // Load LinkedList
        LinkedList linkedBooks = CSV_Reader.loadBooks("books.csv");

        // Binary search requires the ArrayList to be sorted by Book ID
        arrayBooks.sortByBookId();

        // Use 1000 book IDs from the dataset
        int[] searchIds = new int[numberOfSearches];

        for (int i = 0; i < numberOfSearches; i++) {
            searchIds[i] = arrayBooks.get(i * 10).getBookId();
        }

        // --------------------------------
        // ArrayList Linear Search
        // --------------------------------

        long start = System.nanoTime();

        int foundLinearArray = 0;

        for (int id : searchIds) {
            if (arrayBooks.findById(id) != null) {
                foundLinearArray++;
            }
        }

        long end = System.nanoTime();

        long arrayLinearTime = end - start;


        // --------------------------------
        // ArrayList Binary Search
        // --------------------------------

        start = System.nanoTime();

        int foundBinaryArray = 0;

        for (int id : searchIds) {
            if (arrayBooks.binarySearchById(id) != null) {
                foundBinaryArray++;
            }
        }

        end = System.nanoTime();

        long arrayBinaryTime = end - start;


        // --------------------------------
        // LinkedList Linear Search
        // --------------------------------

        start = System.nanoTime();

        int foundLinearLinked = 0;

        for (int id : searchIds) {
            if (linkedBooks.findById(id) != null) {
                foundLinearLinked++;
            }
        }

        end = System.nanoTime();

        long linkedLinearTime = end - start;


        // --------------------------------
        // Display Results
        // --------------------------------

        System.out.println("===== SEARCH PERFORMANCE TEST =====");
        System.out.println("Number of searches: " + numberOfSearches);

        System.out.println();

        System.out.printf(
            "ArrayList Linear Search: %d ns (%.3f ms)%n",
            arrayLinearTime,
            arrayLinearTime / 1_000_000.0
        );

        System.out.printf(
            "ArrayList Binary Search: %d ns (%.3f ms)%n",
            arrayBinaryTime,
            arrayBinaryTime / 1_000_000.0
        );

        System.out.printf(
            "LinkedList Linear Search: %d ns (%.3f ms)%n",
            linkedLinearTime,
            linkedLinearTime / 1_000_000.0
        );

        System.out.println();

        System.out.println("ArrayList linear searches found: " + foundLinearArray);
        System.out.println("ArrayList binary searches found: " + foundBinaryArray);
        System.out.println("LinkedList linear searches found: " + foundLinearLinked);
    }
}