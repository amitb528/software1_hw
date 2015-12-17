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
     * toString).
     */
    public boolean isVariable(char variable) {
        return getDegree(variable) != 0;
        // TODO complete
    }

    /**
     * @return the degree of variable in this monomial
     * @pre isVariable(variable)
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
     * @return true iff the set of variables and the degree of each variable is
     * the same for this and other.
     * @pre other!= null
     */
    public boolean hasSameDegrees(Monomial other) {
        for (int i = 0; i < MAX_VARIABLES; i++) {
            char var = (char) ('a' + i);
            if (getDegree(var) != other.getDegree(var)) {
                return false;
            }
        }
        return true;
        // TODO complete
    }

    /**
     * @return the result of assigning assignment[0] to a, assignment[1] to b
     * etc., and computing the value of this Monomial
     * @pre assignment != null
     * @pre assignment.length == 26
     */
    public int evaluate(int[] assignment) {
        int val = getCoefficient();
        for (int i = 0; i < MAX_VARIABLES; i++) {
            val *= Math.pow(assignment[i], variablesPowers[i]);
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
        if (getCoefficient() == 0) {
            return "0";
        }

        String result = "";
        for (int i = 0; i < MAX_VARIABLES; i++) {
            int power = variablesPowers[i];
            if (power != 0) {
                result += (char) ('a' + i);
                if (power != 1) {
                    result += "^" + power;
                }
            }
        }

        if (Math.abs(getCoefficient()) != 1) {
            result = getCoefficient() + result;
        } else if (result.isEmpty()) {
            result = Integer.toString(getCoefficient());
        } else if (getCoefficient() == -1){
            result = "-" + result;
        }

        return result;
        // TODO complete
    }

    /**
     * Returns a "safe" copy of this monomial, i.e., if the copy is changed,
     * this will not change and vice versa
     */

    public Monomial getCopy() {
        Monomial copy = new Monomial(getCoefficient());
        for (int i = 0; i < MAX_VARIABLES; i++) {
            copy.setDegree((char) ('a' + i), variablesPowers[i]);
        }
        return copy;
        // TODO complete
    }

    /**
     * Returns a new Monomial representing the multiplication of this and other.
     */
    public Monomial multiply(Monomial other) {
        Monomial result = new Monomial(getCoefficient() * other.getCoefficient());

        for (int i = 0; i < MAX_VARIABLES; i++) {
            result.variablesPowers[i] = variablesPowers[i] + other.variablesPowers[i];
        }

        return result;
    }
}
