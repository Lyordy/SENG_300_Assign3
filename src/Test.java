public class Test 
{
	public static void main(String[] args)
	{
		LinkedList books = CSV_Reader.loadBooks("books.csv");
		
		System.out.println("Loaded books: " + books.size());
		
		//Test get()
		System.out.println("\nFirst book:");
		System.out.println(books.get(0));
		
		//Test findById
		Book b = books.findById(1);
		System.out.println("\nFind book with ID 1:");
		System.out.println(b);
		
		//Test findByISBN
		Book isbnBook = books.findByISBN("0345453743");
		System.out.println("\nFind book with ISBN 0345453743:");
		System.out.println(isbnBook);
		
		//Test sorting by authors
		System.out.println("\nSorting by authors (ascending)...");
		books.sortByAuthors(true);
		System.out.println("First after sorting:");
		System.out.println(books.get(0));
		
		//Test sorting by year
		System.out.println("\nSorting by year (descending)...");
		books.sortByYear(false);
		System.out.println("First after sorting:");
		System.out.println(books.get(0));
	}
}
