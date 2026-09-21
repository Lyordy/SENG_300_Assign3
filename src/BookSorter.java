import java.util.List;
import java.util.Comparator;

public class BookSorter {

    public static void insertionSort(List<Book> books, Comparator<Book> comparator) {

        for (int i = 1; i < books.size(); i++) {

            Book currentBook = books.get(i);

            int j = i - 1;

            while (j >= 0 &&
                   comparator.compare(books.get(j), currentBook) > 0) {

                books.set(j + 1, books.get(j));
                j--;
            }

            books.set(j + 1, currentBook);
        }
    }

    public static void sortByAuthorAscending(List<Book> books) {

        insertionSort(
            books,
            Comparator.comparing(Book::getAuthors)
        );
    }

    public static void sortByAuthorDescending(List<Book> books) {

        insertionSort(
            books,
            Comparator.comparing(Book::getAuthors).reversed()
        );
    }

    public static void sortByYearAscending(List<Book> books) {

        insertionSort(
            books,
            Comparator.comparingInt(Book::getOriginalPublicationYear)
        );
    }

    public static void sortByYearDescending(List<Book> books) {

        insertionSort(
            books,
            Comparator.comparingInt(Book::getOriginalPublicationYear).reversed()
        );
    }
}