package sw1.polynomials;

/**
 * Represents a multiplication of variables in a-z with an integral coefficient
 */
public class Monomial {

	private static final int MAX_VARIABLES = 'z' - 'a' + 1;

	private int coefficient;
	private int[] variablesPowers;

	/**
	 * @post this.getCoefficient() == coefficient
	 * @post for every v, 'a'<=v<='z', isVariable(v) == false
	 */
	public Monomial(int coefficient) {
		this.coefficient = coefficient;
		variablesPowers = new int[MAX_VARIABLES];
		// TODO complete
	}

	/**
	 * @return the coefficient of this monomial
	 */
	public int getCoefficient() {
		return coefficient;
		// TODO complete
	}

	/**
	 * @post getCoefficient() == coefficient
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
		// TODO complete
	}

	/**
	 * @return true iff the input is a variable of this monomial (and appears in
	 *         toString).
	 */
	public boolean isVariable(char variable) {
		return variablesPowers[variable - 'a'] != 0;
		// TODO complete
	}

	/**
	 * @pre isVariable(variable)
	 * @return the degree of variable in this monomial
	 */
	public int getDegree(char variable) {
		return variablesPowers[variable - 'a'];
		// TODO complete
	}

	/**
	 * @pre degree >= 0
	 * @pre 'a'<=variable<='z'
	 * @post getDegree(variable) = degree
	 */
	public void setDegree(char variable, int degree) {
		variablesPowers[variable - 'a'] = degree;
		// TODO complete
	}

	/**
	 * @pre other!= null
	 * @return true iff the set of variables and the degree of each variable is
	 *         the same for this and other.
	 */
	public boolean hasSameDegrees(Monomial other) {
		for(char var = 'a'; var <= 'z'; var++){
			if(getDegree(var) != other.getDegree(var)){
				return false;
			}
		}
		return true;
		// TODO complete
	}
	
	/**
	 * @pre assignment != null
	 * @pre assignment.length == 26
	 * @return the result of assigning assignment[0] to a, assignment[1] to b
	 *         etc., and computing the value of this Monomial
	 */
	public int evaluate(int[] assignment) {
		int val = coefficient;
		for(char var = 'a'; var <= 'z'; var++){
			val *= Math.pow(assignment[var - 'a'], variablesPowers[var]);
		}
		return val;
		// TODO complete
	}

	/**
	 * Returns a string representation of this monomial by the mathematical
	 * convention. I.e., the coefficient is first (if not 1), then every
	 * variable in an alphabetic order followed by ^ and its degree (if > 1).
	 * For example, 13b^2x^3z
	 */
	public String toString() {
		if(coefficient == 0){
			return "0";
		}

		String result = "";
		for(char var = 'a'; var <= 'z'; var++){
			int power = variablesPowers[var - 'a'];
			if(power != 0){
				result += var;
				if(power != 1){
					result += "^"+power;
				}
			}
		}

		if(coefficient != 1){
			result = coefficient + result;
		}else if(result.isEmpty()){
			result = "1";
		}

		return result;
		// TODO complete
	}
	
	/**
	 * Returns a "safe" copy of this monomial, i.e., if the copy is changed,
	 * this will not change and vice versa
	 */
	public Monomial getCopy() {
		Monomial copy = new Monomial(coefficient);
		for(char var = 'a'; var <= 'z'; var++){
			copy.setDegree(var, variablesPowers[var]);
		}
		return copy;
		// TODO complete
	}
}
