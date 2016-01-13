
//
//import java.awt.Color;
//
//public class Book {
//	private String title; 
//	private int numberOfPages; 
//	private Person author; //custom class in this package
//	private Color jacketColor; 
//	private boolean wasLitOnFire;
//	private int height;
//	private int thickness; 
//	
//	public Book(String title,int numberOfPages, Person author){
//		this.title = title;
//		this.numberOfPages = numberOfPages;
//		this.author = author;
//		jacketColor = Color.gray; 
//		wasLitOnFire = false; 
//		height = (int) (Math.random()*100 + 150); 
//		thickness = (int)numberOfPages/10; 
//	}
//	
//	public int getThickness() {
//		return thickness;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
	

//	public void setJacketColor(Color jacketColor) {
//		this.jacketColor = jacketColor;
//	}
//
//	public String toString(){
//		if (wasLitOnFire)return  title+", by an author's name you cannot make out "+". "+numberOfPages+" pages.";
//		else return title+", by "+author+". "+numberOfPages+" pages.";
//	}
//	
//	public 	String getTitle(){
//		return title; 
//	}
//	
//	public int getNumberOfPages(){
//		return numberOfPages; 
//	}
//	
//	public Person getAuthor() {
//		return author;
//	}
//
//	public void setOnFire(){
//		jacketColor = Color.black;
//		title = "The title of this book is too charred to make out"; 
//		wasLitOnFire = true; 
//		
//	}
//}
		package Objects;
		import java.awt.Color;
		
		
		
		
		public class Book{
		
		
		
		String title;
		
		Person author;
		

		int numberOfPages;
		
		int thickness;
		
		int height;
		
		Color color;
		
		boolean checkedOut;
		
		long checkOutDate;
		
		long dueDate;
		
		static public String[] conditions = new String[]{"The book is brand new.", "The book is gently used.", "The book is in good condition.", "The book is used.", "The book is worn.", "Jeez. This book is in terrible condition."};
		
		String description;
		
		int accumulatedUse;
		
		
		public String getDescription() {
			return description;
		}



		public int getAccumulatedUse() {
			return accumulatedUse;
		}
		
		
		
		public boolean isCheckedOut() {
			return checkedOut;
		}



		public void setCheckedOut(boolean checkedOut) {
			this.checkedOut = checkedOut;
		}



		public long getCheckOutDate() {
			return checkOutDate;
		}



		public void setCheckOutDate(long checkOutDate) {
			this.checkOutDate = checkOutDate;
		}



		public long getDueDate() {
			return dueDate;
		}



		public void setDueDate(long dueDate) {
			this.dueDate = dueDate;
		}



		public static int DEFAULT_HEIGHT = 300;
		
		
		public Book(String title, int pages, Person author){
		
		    this.title=title;
		
		    this.author=author;
		
		    this.numberOfPages=pages;
		
		    thickness = numberOfPages/10;//every ten pages is one "unit", this is arbitrary
		
		    color = Color.GRAY;//default color
		
		    height = DEFAULT_HEIGHT+(int)(Math.random()*100);//this may as well be arbirary
		
		    checkedOut = false;
		    
		    checkOutDate = 0;
		    
		    dueDate = 0; 
		    
		    description = conditions[0]; 
		    
		    accumulatedUse = 0; 
		}
		
		
		
		public String getTitle() {
		
		    return title;
		
		}
		
		
		
		public Person getAuthor() {
		
		    return author;
		
		}
		
//		public Color getJacketColor() {
//			return jacketColor;
//		}
		
		
		
		public int getNumberOfPages() {
		
		    return numberOfPages;
		
		}
		
		public int getHeight() {
		
		    return height;
		
		}
		
		
		
		public void setHeight(int height) {
		
		    this.height = height;
		
		}
		
		
		
		public int getThickness() {
		
		    return thickness;
		
		}
		
		
		/**
		
		* returns the description of the book in the format: "title", by author, (numberOfPages pages)
		
		*/
		
		@Override
		
		public String toString(){
		
		    return "\""+title+"\", by "+author+", ("+numberOfPages+" pages)";
		
		}
		
		
		
		
		
		
		
		public void setColor(Color color){
		
		    this.color=color;
		
		}
		
		
		
		public Color getColor() {
		
		    return color;
		
		}
		
		public long getSecondsRemaining(){
			return (dueDate - System.currentTimeMillis())/1000;
			
		}
		
		public void updateCondition(long timeOfReturn){
			long difference = (timeOfReturn - checkOutDate)/1000; //not sure
			accumulatedUse += difference; 
			if (accumulatedUse > 10){
				description = conditions[1];
			}
			
			if (accumulatedUse > 20){
				description = conditions[2];
			}
			
			if (accumulatedUse > 30){
				description = conditions[3];
			}
		}
		
		
		}
