public class Book {

    private int bookId;
    private String isbn;
    private String authors;
    private String title;
    private int originalPublicationYear;
    private double averageRating;
    private int ratingsCount;

    public Book(int bookId, String isbn, String authors, String title,
                int originalPublicationYear, double averageRating, int ratingsCount) {

        this.bookId = bookId;
        this.isbn = isbn;
        this.authors = authors;
        this.title = title;
        this.originalPublicationYear = originalPublicationYear;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public int getOriginalPublicationYear() {
        return originalPublicationYear;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + authors
                + " | " + originalPublicationYear;
    }
}