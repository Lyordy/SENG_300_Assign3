import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortingTest {

    public static void main(String[] args) {

        // Test ArrayList
        List<Book> arrayBooks = new ArrayList<>();

        arrayBooks.add(new Book(
                1,
                "111",
                "J.K. Rowling",
                "Harry Potter",
                1997,
                4.5,
                1000
        ));

        arrayBooks.add(new Book(
                2,
                "222",
                "George Orwell",
                "1984",
                1949,
                4.2,
                2000
        ));

        arrayBooks.add(new Book(
                3,
                "333",
                "Suzanne Collins",
                "The Hunger Games",
                2008,
                4.3,
                3000
        ));

        System.out.println("ARRAYLIST - AUTHORS A-Z");

        BookSorter.sortByAuthorAscending(arrayBooks);

        for (Book book : arrayBooks) {
            System.out.println(book);
        }


        // Test LinkedList
        List<Book> linkedBooks = new LinkedList<>();

        linkedBooks.add(new Book(
                1,
                "111",
                "J.K. Rowling",
                "Harry Potter",
                1997,
                4.5,
                1000
        ));

        linkedBooks.add(new Book(
                2,
                "222",
                "George Orwell",
                "1984",
                1949,
                4.2,
                2000
        ));

        linkedBooks.add(new Book(
                3,
                "333",
                "Suzanne Collins",
                "The Hunger Games",
                2008,
                4.3,
                3000
        ));

        System.out.println();
        System.out.println("LINKEDLIST - YEAR NEWEST TO OLDEST");

        BookSorter.sortByYearDescending(linkedBooks);

        for (Book book : linkedBooks) {
            System.out.println(book);
        }
    }
}