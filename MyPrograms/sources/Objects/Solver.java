package Objects;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Solver {
	private static SolverGUI gui;
	public static void main(String[] args){
		//TODO: for now, you may use this method for testing
		//your other methods
		//Here is something to try:
//		System.out.println("The terms in the equation \"3x^2 -12x +13 = 2x^2 -17x +7\" are:");
//		Equation parsedEquation = interpretInput("3x^2 -12x +13 = 2x^2 -17x +7");
//		for(Term t: parsedEquation.getLeftSide()){
//			System.out.println(t.toString());
//		}
//		System.out.println("...on the left, and on the right:");
//		for(Term t: parsedEquation.getRightSide()){
//			System.out.println(t.toString());
//		}
		//Once your methods are all working, you can use the following to initiate the GUI
		SolverGUI s = new SolverGUI();
		s.setVisible(true);
		s.setSize(900, 500);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static Equation interpretInput(String s){
		//parse s into two ArrayList<Term>
		//expect user input to appear like this:
		//3x^2 -12x +13 = 2x^2 -17x +7
		//A few things to note:
		//Assume the user will always include exactly one '=',
		//    (Invalid input will be handled by the GUI)
		//Terms are always separated by a '+','-' or '='
		//Do not consider subtraction. Rather, subtraction is ADDING a Term with a NEGATIVE coefficient
		//Recall that a term like "x" has a coefficient of '1' and an exponent of '1'
		//Recall that there is a specific constructor for constant terms

		String str = s.replaceAll("\\s", "");
		String[] leftAndRight = str.split("=");
		ArrayList<Term> leftSide = createAndAddTerms(leftAndRight[0]); 
		ArrayList<Term> rightSide = createAndAddTerms(leftAndRight[1]); 
		return new Equation(leftSide, rightSide); 


	}

	private static ArrayList<Term> createAndAddTerms(String termString){
		ArrayList<Term> terms = new ArrayList<Term>();
		int i = 0; //index of first digit (in case the String starts with "-")
		boolean positiveTerm = true; //assume term is true
		if(termString.startsWith("-")){
			positiveTerm = false; 
			i++; //start at index 1 if character at 0 is "-"
		}
		while(termString.length() > 0){ //delete one term at a time as it gets added
			int endOfTerm = termString.length();
			int indexOfPlus = termString.indexOf("+");
			int indexOfMinus = termString.indexOf("-");
			//if there is no plus signal, above value is -1;
			if(indexOfPlus < 0){
				indexOfPlus = endOfTerm;
			}
			if(indexOfMinus < 0){
				indexOfMinus = endOfTerm;
			}
			if(indexOfMinus < indexOfPlus){
				endOfTerm = indexOfMinus;
			}
			else{
				endOfTerm = indexOfPlus; //guarantees whatever comes first, end, "+", or "-"
			}
			try{
				Term a = parseTerm(termString.substring(i, endOfTerm));
				if(!positiveTerm){
					a = a.getAddInverse(); //HOMEWORK 
				}
				if(a != null){
					terms.add(a);
				}
				//check if next term is + or -
				if(indexOfMinus < indexOfPlus){
					positiveTerm = false;
				}
				else{
					positiveTerm = true;
				}
				//cut out the term that was added, including the NEXT +/- symbol
				termString = termString.substring(endOfTerm+1, termString.length());
			}
			catch(Exception e){
				//if the user is trying to do something that throws an error, skip this term
				termString = termString.substring(endOfTerm, termString.length());
				//cuts out the term
			}
		}
		return terms;
	}

	/**

	 * @param sample string from user: 34a^3

	 * @return new Term representing input from user

	 * Use this method for writing interpretInput

	 */

	private static Term parseTerm(String s) {

		if(s.matches("\\d*\\w\\^\\d+" +

		 "|" +

		 "\\d*\\w" +

		 "|" +

				"\\d+")){//regex identifies anything like 23x^2 or y^11 or 12b or z or 13

			double coef;

			String variable;

			Term t;

			if(s.matches("\\d*")){

				coef = Double.parseDouble(s);

				t = new Term(coef);

			}

			else if(s.matches("\\d*\\w+(\\^\\d+)?")){

				String noDigits = s.replaceAll("\\d", "");

				int indOfVar = s.indexOf(noDigits);

				if(s.substring(0,indOfVar).length()==0){

					coef = 1.0;

				}

				else coef = Double.parseDouble(s.substring(0, indOfVar));

				variable=s.substring(indOfVar, indOfVar+1);

				if(s.indexOf("^")>-1){

					int exponent = Integer.parseInt(s.substring(s.indexOf("^")+1));

					t =new Term(coef,variable,exponent); 

				}else{

					t =new Term(coef,variable,1); 

				}

			}else{

				t=null;

			}

			return t;

		}

		else return null;

	}


	public static String getConfirm(Equation eq) {


		//returns a message confirming the input given by the user, such as "Your input was interpreted as "+eq.toString();
		String s = "Your input was interpreted as" + eq.toString(); 
		return s;

	}



	public static String getNoTricks(String usersInput) {


		//returns a message for when the user types input that cannot be interpreted by your Solver

		return "Your string cannot be interpreted by the Solver"; 


	}
	/**

	 * NEW STUFF FOR 4.1

	 *

	 */



	/**

	 * 

	 * @param eq

	 * This method is only called if eq is linear or quadratic

	 * Use the methods described in 4.1 to solve the equation

	 * Note that each method will need to be called twice, for the left and right side

	 * Don't forget to call gui.clearSteps() at the beginning of the method

	 * and gui.addStep after each step of the process

	 */

	public static void solve(Equation eq) {
		ArrayList<Term> cancelSide = eq.getLeftSide(); 
		ArrayList<Term> termSide = eq.getRightSide();
		if (eq.cancelRight()){
			cancelSide = eq.getRightSide();
			termSide = eq.getLeftSide();
		}
		
		eq.toZeroOnOneSide(cancelSide);
		gui.addStep(eq, "Cancel all terms on one side of equation");
		eq.simplify(eq.getLeftSide());
		eq.simplify(eq.getRightSide());
		gui.addStep(eq,"Simplify."); 



	}



}
