public class LinkedList {
	
	private Node head;
	private int size;
	
	public LinkedList()
	{
		head = null;
		size = 0;
	}
	
	public void add(Book b)
	{
		Node newNode = new Node(b);
		
		if(head == null)
		{
			head = newNode;
		}
		else
		{
			Node current = head;
			while(current.next != null)
			{
				current = current.next;
			}
			current.next = newNode;
		}
		size++;
	}
	
	public Book get(int index)
	{
		if(index < 0 || index >= size)
		{
			return null;
		}
		
		Node current = head;
		for(int i = 0; i < index; i++)
		{
			current = current.next;
		}
		return current.data;
	}
	
	public int size()
	{
		return size;
	}
	
	// Linear search by book_id
	public Book findById(int id)
	{
		Node current = head;
		while(current != null)
		{
			if(current.data.getBookId() == id)
			{
				return current.data;
			}
			current = current.next;
		}
		return null;
	}
	
	//Linear search by ISBN
	public Book findByISBN(String isbn)
	{
		Node current = head;
		while(current != null)
		{
			if(current.data.getIsbn().equals(isbn))
			{
				return current.data;
			}
			current = current.next;
		}
		return null;
	}
	
	//Sort by authors
	public void sortByAuthors(boolean ascending)
	{
		if(head == null)
		{
			return;
		}
		
		boolean swapped;
		do
		{
			swapped = false;
			Node current = head;
			
			while(current.next != null)
			{
				int compare = current.data.getAuthors().compareTo(current.next.data.getAuthors());
				
				if((ascending && compare > 0) || (!ascending && compare < 0))
				{
					Book temp = current.data;
					current.data = current.next.data;
					current.next.data = temp;
					swapped = true;
				}
				current = current.next;
			}
		}
		while(swapped);
	}
	
	//Sort by publication year
	public void sortByYear(boolean ascending)
	{
		if(head == null)
		{
			return;
		}
		
		boolean swapped;
		do
		{
			swapped = false;
			Node current = head;
			
			while(current.next != null)
			{
				int compare = current.data.getOriginalPublicationYear() - current.next.data.getOriginalPublicationYear();
				
				if((ascending && compare > 0) || (!ascending && compare < 0 ))
				{
					Book temp = current.data;
					current.data = current.next.data;
					current.next.data = temp;
					swapped = true;
				}
				current = current.next;
			}
		}
		while(swapped);}
		
		// Delete a book by Book ID
		public boolean deleteById(int id)
		{
		    if (head == null)
		    {
		        return false;
		    }

		    // If the first book is the one being deleted
		    if (head.data.getBookId() == id)
		    {
		        head = head.next;
		        size--;
		        return true;
		    }

		    Node current = head;

		    while (current.next != null)
		    {
		        if (current.next.data.getBookId() == id)
		        {
		            current.next = current.next.next;
		            size--;
		            return true;
		        }

		        current = current.next;
		    }

		    return false;
	}
}
