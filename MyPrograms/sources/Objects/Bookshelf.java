package Objects;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.Comparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Bookshelf {
	public static void main(String[] args){
		Person author2 = new Person("Anthonty","Burgess",true);
		Person author1 = new Person ("Noah", "Webster", true);
		Person author3 = new Person ("Philip", "K", "Dick", true);
		Book book1 = new Book("Dictionary", 1001, author1);
		Book book2 = new Book("A Clockwork Orange", 749, author2);
		Book book3 = new Book ("Do Androids Dream of Electric Sheeps", 500, author3);
		Book book5 = new Book("The Minority Report", 589, author3);
		book1.setColor(Color.black);
		book2.setColor(Color.orange);
		book3.setColor(Color.green);
		book5.setColor(Color.blue);
		

		ArrayList<Book> shelf = new ArrayList<Book>();
		ArrayList<Person> libraryCardHolders = new ArrayList<Person>(); 
		int[] books = new int[5]; 


//		System.out.println(book1);
//		System.out.println(book2);
//
//		book1.setJacketColor(Color.orange);
//		creepBks(); 


		shelf.add(book1); 
		shelf.add(book2);
		shelf.add(book3);
		shelf.add(new Book("The Man in the High Castle", 600, author1)); 
		shelf.add(0, book5);
		
		libraryCardHolders.add(author1);
		libraryCardHolders.add(author2);
		
		
		Library lib = new Library(shelf, libraryCardHolders);//use "books" or "shelf" whatever your ArrayList is 

		lib.setSize(new Dimension(500,500));

		lib.setVisible(true);

		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Collections.sort(shelf, new Comparator<Book>(){
			//Descending order by height, give each custom color
//			public int compare(Book a, Book b) {
//					String aLast = a.getAuthor().getLastName();
//					String bLast = b.getAuthor().getLastName();
//				return aLast.compareTo(bLast);
			
			public int compare(Book a, Book b) {
				int aHeight = a.getHeight();
				int bHeight = b.getHeight();
				return aHeight - bHeight;
			
//			public int compare(Book a, Book b) {
//				String aColor = a.getJacketColor().toString();
//				String bColor = b.getJacketColor().toString();
//			return aColor.compareTo(bColor);
				
				
			}
			}); 

				//printAll(shelf); 
				System.out.println(author1.getGenderPossessivePronoun());
		
		}
	
		public static void sortByAuthor(final boolean ascending, ArrayList<Book> shelf){
			Collections.sort(shelf, new Comparator<Book>(){
				//Descending order by height, give each custom color
				public int compare(Book a, Book b) {
						String aLast = a.getAuthor().getLastName();
						String bLast = b.getAuthor().getLastName();
					if (ascending) return aLast.compareTo(bLast);
					else return bLast.compareTo(aLast); 
				}
				
			});
		}
		

		private static void printAll(ArrayList list){
			for (int i= 0; i <list.size(); i++){
				System.out.println(list.get(i)); 
			}

		}

		public static void sortByTitle(final boolean ascending,
				ArrayList<Book> shelf) {
			Collections.sort(shelf, new Comparator<Book>(){
				
				public int compare(Book a, Book b) {
						String aTitle = a.getTitle();
						String bTitle = b.getTitle();
					if (ascending) return aTitle.compareTo(bTitle);
					else return bTitle.compareTo(aTitle); 
				}
				
			});
			
		}

		public static void sortByHeight(final boolean ascending,
				ArrayList<Book> shelf) {
			Collections.sort(shelf, new Comparator<Book>(){
				
				public int compare(Book a, Book b) {
						int aHeight = a.getHeight();
						int bHeight = b.getHeight();
					if (ascending) return aHeight - bHeight;
					else return bHeight - aHeight ; 
				}
				
			});
			
		}

		public static void sortByPageNumber(final boolean ascending,
				ArrayList<Book> shelf) {
				Collections.sort(shelf, new Comparator<Book>(){
				
				public int compare(Book a, Book b) {
						int aNum = a.getNumberOfPages();
						int bNum = b.getNumberOfPages();
					if (ascending) return aNum - bNum;
					else return bNum - aNum ; 
				}
				
			});
			
			
		}

//		public static void creepBks(){
//			for (Book b:shelf){
//				ArrayList<Book> creepyBooks = new ArrayList<Book>();
//				if(b.getAuthor().toString().equals("Philip K. Dick")) {
//					creepyBooks.add(b); 
//					b.setOnFire();
//				}
//				System.out.println("The books in the creepy collection include");
//				printAll(creepyBooks); 
//
//
//			}
//
//		}

	}



