import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListBooks {

    private ArrayList<Book> books;

    // Constructor
    public ArrayListBooks() {
        books = new ArrayList<>();
    }

    // Add a book to the ArrayList
    public void add(Book book) {
        books.add(book);
    }

    // Get a book by index
    public Book get(int index) {
        if (index < 0 || index >= books.size()) {
            return null;
        }

        return books.get(index);
    }

    // Get the number of books
    public int size() {
        return books.size();
    }

    // Search by book ID
    public Book findById(int id) {
        for (Book book : books) {
            if (book.getBookId() == id) {
                return book;
            }
        }

        return null;
    }

 // Binary search by book ID
 // The list must be sorted by book ID first
 public Book binarySearchById(int id) {

     int left = 0;
     int right = books.size() - 1;

     while (left <= right) {

         int middle = (left + right) / 2;

         Book middleBook = books.get(middle);

         if (middleBook.getBookId() == id) {
             return middleBook;
         }

         if (middleBook.getBookId() < id) {
             left = middle + 1;
         } else {
             right = middle - 1;
         }
     }

     return null;
 }
    
//Binary search by ISBN
//The list must be sorted by ISBN first
public Book binarySearchByISBN(String isbn) {

  int left = 0;
  int right = books.size() - 1;

  while (left <= right) {

      int middle = (left + right) / 2;

      Book middleBook = books.get(middle);

      int comparison = middleBook.getIsbn().compareTo(isbn);

      if (comparison == 0) {
          return middleBook;
      }

      if (comparison < 0) {
          left = middle + 1;
      } else {
          right = middle - 1;
      }
  }

  return null;
}
 
	//Sort books by Book ID for binary search
	public void sortByBookId() {
		books.sort((book1, book2) ->
		Integer.compare(book1.getBookId(), book2.getBookId())
 );
}
	
	public void sortByISBN()
	{
	    books.sort((book1, book2) ->
	        book1.getIsbn().compareTo(book2.getIsbn()));
	}

    // Search by ISBN
    public Book findByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    // Get the ArrayList
    public ArrayList<Book> getBooks() {
        return books;
    }

    // Load books from the CSV file
    public void loadBooks(String filename) {

        String line;

        String splitRegex =
                ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {

            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {

                try {

                    String[] fields = line.split(splitRegex, -1);

                    int bookId = Integer.parseInt(fields[0]);

                    String isbn = fields[5];

                    String authors = fields[7];

                    int year = 0;

                    if (!fields[8].trim().isEmpty()) {
                        year = (int) Double.parseDouble(fields[8]);
                    }

                    String title = fields[10];

                    double averageRating =
                            Double.parseDouble(fields[12]);

                    int ratingsCount =
                            Integer.parseInt(fields[13]);

                    Book book = new Book(
                            bookId,
                            isbn,
                            authors,
                            title,
                            year,
                            averageRating,
                            ratingsCount
                    );

                    books.add(book);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {

                    // Skip a bad row and continue loading
                    continue;
                }
            }

        } catch (IOException e) {

            System.out.println("Error loading books.csv: "
                    + e.getMessage());}
        }
        
     // Delete a book by Book ID
        public boolean deleteById(int id)
        {
            for (int i = 0; i < books.size(); i++)
            {
                if (books.get(i).getBookId() == id)
                {
                    books.remove(i);
                    return true;
                }
            }

            return false;
    }
}