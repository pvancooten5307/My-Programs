package Objects;
import java.util.ArrayList;

public class Equation {
	private ArrayList<Term> leftSide;
	private ArrayList<Term> rightSide;
	private boolean cancelRight;
	private ArrayList<Double> solution;
	
//TODO: Make getters for all of the above

 public Equation(ArrayList<Term> leftSide, ArrayList<Term> rightSide){
 //sets values of leftSide and rightSide only
	 this.leftSide = leftSide; 
	 this.rightSide = rightSide; 
 }
 
 public boolean isLinear(){
	 return isOfDegree(1); 
 }
 
//isOfDegree is wrong, check variables match 
 public boolean isOfDegree(int degree){
 //returns 'true' if this equation is linear
 //and every term is either constant or has the same variable
	int maxDegree = 0;
	int minDegree = 0; 
	String variable = ""; 
	for (Term t: leftSide){
		if (t.getDegree() > maxDegree) maxDegree = t.getDegree(); 
		if(t.getDegree() < minDegree) return false; 
	}
		//any negative value changes type of equation to rational 
		for (Term t: rightSide){
			if (t.getDegree() > maxDegree) maxDegree = t.getDegree(); 
			if(t.getDegree() < minDegree) return false; 
			if(!t.isConstant()) variable = t.getVariable(); 
			
		}
		
		if (maxDegree == degree && variablesMatch(variable)) return true; 
		else return false; 
	}

 

		 public void setSolution(ArrayList<Double> solution) {
	this.solution = solution;
}

		private boolean variablesMatch(String s) {
			for (Term t: leftSide){
				if (!t.isConstant() && !t.getVariable().equals(s)){
					return false;
				}
				
			}
			
			for (Term t: rightSide){
				if (!t.isConstant() && !t.getVariable().equals(s)) {
					return false; 
				}
			}
			return true;
			
		}

		 public ArrayList<Term> getLeftSide() {
			return leftSide;
		}

		public ArrayList<Term> getRightSide() {
			return rightSide;
		}

public boolean isQuadratic(){
 //returns 'true' if this equation is quadratic
 //and every term is either constant or has the same variable
	if (isOfDegree(2)){
	return true; 
	}
	
	else return false; 
 }

 public boolean isSolveable(){
 //returns 'true' if this equation is linear or quadratic, 'false' otherwise 
 //(because in this project we are not programming a means of solving anything other than linear and quadratic equations)
	 if (this.isLinear() || this.isQuadratic()){
		 return true; 
	 }
	 
	 else{
		 return false; 
	 }
 }

 public boolean cancelRight(){
 //sets the value of cancelRight and
 //returns 'true' if the equation should be solved by subtracting terms from the right side, false if it is better to subtract terms on the left side
	 	Term highestLeft = getHighestDegreeTerm(leftSide);
	 	Term highestRight = getHighestDegreeTerm(rightSide); 
	 	if (highestRight.getDegree()>highestLeft.getDegree() && highestRight.isPositive()) return false; 
	 	if (highestRight.getDegree()>highestLeft.getDegree() && !highestRight.isPositive()) return true; 
	 	if (highestRight.getDegree()<highestLeft.getDegree() && highestLeft.isPositive())	return true;
	 	if (highestRight.getDegree()<highestLeft.getDegree() && !highestLeft.isPositive())	return false; 
	 	if (highestRight.getDegree()==highestLeft.getDegree() && highestLeft.getCoefficient()>highestRight.getCoefficient()) return true; 
	 	else return false;
 }

 public String toString(){
 /**
  *Take your time on this method!
  *There are many things to consider:
  *Every terms should be separated by a '+' UNLESS it has a negative coefficient. 
  *When a term is negative, the negative sign will appear as a minus.
  */
	 String s = getSideString(leftSide) + " = " + getSideString(rightSide); 
	 return s; 
 }


    private String getSideString(ArrayList<Term> side) {
	String s = "";
	try{
		s = side.get(0).toString();
	}  
	
	catch(Exception e){
		s = "0"; 
	}
	
	for (int i = 1; i < side.size(); i++){
		if(side.get(i).isPositive()) s += " + " + side.get(i); 
		else s+= " - " + side.get(i).toString().replaceFirst("-", ""); 
	}
	
	return s; 
}

	public static Term getHighestDegreeTerm(ArrayList<Term> side){
        //returns the term in the ArrayList that is the highest degree.
        //example
        //3x^2 + 4x^3 - 12x + x^2
        //will return 4x^3 
		int maxDegree = 0;
		Term maxDegreeTerm = new Term(0); 
		for (Term t: side){
			if (t.getExponent() > maxDegree){
				maxDegree = t.getExponent();
				maxDegreeTerm = t; 
				
			}
		}
		return maxDegreeTerm;
    }
	
	/**

	* NEW STUFF FOR 4.1

	*/

	/**

	* adds the additiveInverse of everything on the sideBeingCancelled

	* to both sides of the Equation

	* @param sideBeingCanceled

	*/

	public void toZeroOnOneSide(ArrayList<Term> sideBeingCanceled){
		ArrayList<Term> addIn = new ArrayList<Term>();
		for (Term t:sideBeingCanceled){
			addIn.add(t.getAddInverse());
			
		}
		
		for (Term t:addIn){
			leftSide.add(t);
			rightSide.add(new Term(t.getCoefficient(), t.getVariable(), t.getExponent()));
		}
		
//			Term t = getHighestDegreeTerm(sideBeingCanceled);
//			 leftSide.add(t.getAddInverse()); 
//			 rightSide.add(t.getAddInverse()); 
			 
			


	}



	/**

	* 

	* @param side - simplifies the specified side of the equation

	* Notes: This method should check every Term on the specified side of the equation 

	* with every other Term to check if they are like terms (use Term.areLikeTerms(Term s, Term t)

	* If they are, it should reassign the current Term to the combined result (use Term.combine(Term s, Term t)

	* and remove the one with which it combined.

	* Finally, if the resulting term has a coefficient of zero should be removed.

	* 

	* For example, Suppose side contains 5x + 3 -5x. Call the three terms a, b and c, respectively

	* 1. It checks to see if 5x and 3 (a and b) are like terms, they are not

	* 2. It checks to see if 5x and -5x (a and c) are like terms, they are

	* 3. Since 5x and -5x are like terms, a = Term.cobine(a, c) then leftSide.remove(c)

	* 4. Now side contains 0x + 3. Since term a has a coefficient of zero, remove it.

	* 5. Now side contains 3. 

	* 

	* ONE MORE NOTE: Since you will be removing items from side, use a standard for loop

	* and remember that when something is moved, everything "slides over"

	*/

	public void simplify(ArrayList<Term> side){
		ArrayList<Term> removeThese = new ArrayList<Term>(); 
		for (int i = 0; i < side.size(); i++){   //for(Term t: side)
			for (int j = i+1; j <side.size(); j++){ //for (Term s: side)
				//if (t!=s && t.getCoefficient!=0){
			if (Term.areLikeTerms(side.get(i), side.get(j))){ // (s,t)
				side.add(Term.combine(side.get(i), side.get(j))); //combine(s,t)
				side.remove(side.get(i)); //t.setCoefficeint(term.combine(s,t);
				side.remove(side.get(j)); //s.setCoefficient(0); 
				//removeThese.add(s); 
				}
			
		}
			
			side.removeAll(removeThese); 
		
		for (Term t: side){
			if (t.getCoefficient() == 0) side.remove(t); 
		}
		
		if (side.size() > 1) {toZeroOnOneSide(side);}// not sure if 0 or 1; does 0 count as a term?

	}
		
	}


	/**

	* 

	* @param sideWithVariable - we can assume the side with a variable is of the form ax + b

	* @return the solution

	* 

	* Example: 3x + 21 = 0

	* 1. Identify the constant term in the sideWithVariable (21)

	* 2. Identify the variable term in the sideWithVariable (3x)

	* 3. Add the additive inverse of the constant Term to both sides of the equation (3x = -21)

	* 4. Multiple both sides by the additive inverse of the coefficient of the variable term (.33333333)

	*/

	public void solveLinear(ArrayList<Term> sideWithVariable){
		Term constant = new Term(0,"",0);
		Term variable = new Term(0,"",0);
		
		for (int i = 0; i < sideWithVariable.size(); i++){
			if (sideWithVariable.get(i).isConstant()){
				variable = sideWithVariable.get(i);
			}
			
			else constant = sideWithVariable.get(i); 
		}
		
		leftSide.add(constant.getAddInverse());
		rightSide.add(constant.getAddInverse());
		simplify(rightSide);
		simplify(leftSide); 
		multiplyScalar(leftSide, variable.getCoefficient());
		multiplyScalar(rightSide, variable.getCoefficient());

	}
	
	public void solveQuadratic(ArrayList<Term> side){
		//don't understand how to find coefficient of each term, or split the terms in general to find the discriminant
		// if getDegree = 2; that is a
		// if getDegree = 1; that is b
		// if isConstant = 0; that is c 
		int discriminant = 0;
		// == b^2-4ac
		if (discriminant >= 0) factor(side);
		//else quadratic formula
		
	}


	private void factor(ArrayList<Term> side) {
		// TODO Auto-generated method stub
		int num = 0; //should be = a*c
		int bTerm = 0; //b term in the equation
		  int i = 1;
		  int factorOne = 0;
		  int factorTwo = 0; 
		  String varOnSide = "";
		  String factoredSolution = "(" + varOnSide + factorOne + ")(" + varOnSide + factorTwo +")" ;
		  String solutions = varOnSide + "=" + factorOne*-1 + "and " + varOnSide + "=" + factorTwo*-1;
		  
		  for (Term t: side){
			  if (varOnSide.equals("")){
			  varOnSide = t.getVariable();
			  }
		  }

		  while(i <= num) {
		    if(num%i == 0){
		      if (num/i+i == bTerm){
		    	  factorOne = num/i;
		    	  factorTwo = i; 
		      }
		    }
		  }

		System.out.println(factoredSolution);
		System.out.println(solutions);
	}

	/**

	* 

	* @param side

	* @param scalar

	* multiplies all Terms on the given side by the given scalar

	* (Hint: use setCoefficient(double))

	*/

	public void multiplyScalar(ArrayList<Term> side, double scalar){

		for (Term t: side){
			t.setCoefficient(scalar);
		}

	}

}
