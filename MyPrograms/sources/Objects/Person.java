package Objects;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String lastName; 
	private String middleName;
	public static int MAX_ALLOWED_BOOKS = 3;
	ArrayList<Book> checkedOutBooks;
	boolean male;
	String pronoun; 
	Balance balance;
	//we don't want name changed without Person knowing
	//ArrayList<term> NewAL = new ArrayList<term>(); 
	
	
	
	
	public boolean isMale() {
		return male;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Balance getBalance() {
		return balance;
	}

	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}

	public Person(String firstName, String lastName, boolean male){
		this.firstName = firstName; //assign parameter to field so information is "remembered"
		this.lastName = lastName; //this specifies the one in the class, not the local variable
		if(male) this.pronoun = "his"; 
		else this.pronoun = "her";
		checkedOutBooks = new ArrayList<Book>();
		balance = new Balance(); 
	}
	
	public Person(String firstName, String middleName, String lastName, boolean male){
		this.firstName = firstName; //assign parameter to field so information is "remembered"
		this.middleName = middleName; 
		this.lastName = lastName; //this specifies the one in the class, not the local variable
		if(male) this.pronoun = "his"; 
		else this.pronoun = "her";
		balance = new Balance(); 
	}
	
	
	
	public String toString(){
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}
	
	public void checkOutBook(Book book1){
		book1.setCheckedOut(true); 
		book1.setCheckOutDate(System.currentTimeMillis());
		book1.setDueDate(System.currentTimeMillis()+30000);
		checkedOutBooks.add(book1);	
	}
	
	public void returnBook(Book book1){
		book1.setCheckedOut(false); 
		book1.updateCondition(System.currentTimeMillis());
		balance.subtractLateFees((System.currentTimeMillis() - book1.getDueDate())/1000);
		book1.setCheckOutDate(0);
		book1.setDueDate(0);
		checkedOutBooks.remove(book1);	
	}
	
	public void renewBook(Book book1){
		book1.setDueDate(System.currentTimeMillis()+30000);
	}
	
	public String getGenderPossessivePronoun(){
		return pronoun; 
		
	}
	
	public String getLibraryDescription(){
		String statement = "";
		if (balance.getAmount() >= 0){
			statement = "'s balance is $";
		}
		else{
			statement = "owes $";
		}
		String tmp = firstName + " " + lastName + " is viewing the library collection. " + firstName + " " + statement + Math.abs(balance.getAmount()) + ".";
		return tmp; 
	}
	
	
	
	
}


//<Book> is a a generic type. It tells the constructor WHAT is in the ArrayList. We 
//use generics to save ourselves the trouble of type casting
//new ArrayList<?> constructor does not need to specify length(Default is 10, but it doesn't matter, it adjust as you add objects to it
//note that ArrayList is indexed
//you cannot make array list of primitives 
//Array list of ints, use wrapper class
//int is Integer, doube is Double, etc

//add things to an ArrayList (remember, for arrays, we did: 
//array[0] = book1;//specify index


//System.out.println("The first book on the shelf is" + shelf.get(0)); //Lose points if you get this wrong
//
//for(Book b:shelf) System.out.println(b); 
//System.out.println("The length size of the shelf is" + shelf.size() + " books");
//
//
//
////shelf.remove(4);
//shelf.remove(book1); 
//
//for (int i = shelf.size(); i > -1 ; i--){
//	if (shelf.get(i).toString().indexOf("the") > -1 || shelf.get(i).toString().indexOf("The") > -1){
//		shelf.remove(i); 
//	}
//}
//
//
//System.out.println("\nUsing a standard for loop:");
//for (int j = 0; j<shelf.size(); j++){
//	System.out.println(shelf.get(j)); 
//}
//
//System.out.println(book2.getTitle()+ " is the " + shelf.indexOf(book2)); 
//
////identifying whether or not an Object is in the list
////getting the index of an Object in an ArrayList
//
//if (shelf.contains(book2)){
//	System.out.println(book2.getTitle() + " is book #" + shelf.indexOf(book2) + "on the shelf.");	
//}
//
