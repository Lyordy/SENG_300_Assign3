import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSV_Reader {
	
	public static LinkedList loadBooks(String filename)
	{
		LinkedList list = new LinkedList();
		
		String line;
		String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			br.readLine();
			
			while((line = br.readLine()) != null)
			{
				String[] fields = line.split(splitRegex, -1);
				
				int book_id = Integer.parseInt(fields[0]);
				String isbn = fields[5];
				String authors = fields[7];

				int year = 0;

				if (!fields[8].trim().isEmpty()) {
 					year = (int) Double.parseDouble(fields[8]);
				}
				
				String title = fields[10];
				double avgRating = Double.parseDouble(fields[12]);
				int ratingsCount = Integer.parseInt(fields[13]);
				
				Book b = new Book(book_id, isbn, authors, title, year, avgRating, ratingsCount);
				list.add(b);
			}
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
}
