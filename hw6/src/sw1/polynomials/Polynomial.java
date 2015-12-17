package sw1.polynomials;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Polynomial {

    @Nullable
    Monomial[] monomials;

    /**
     * Creates a polynomial with (safe copies of) the given monomials
     *
     * @pre monomials != null
     * @pre for all i, 0 <= i < monmials.length : monomials[i] != null
     * @post for all i, 0 <= i < monmials.length : monomials[i].getCoefficient()
     * == getMonomial(i).getCoefficient()
     * @post for all i,v, 0 <= i < monmials.length, 'a'<=v<='z' :
     * monomials[i].getDegree(v) == getMonomial(i).getDegree(v)
     */
    public Polynomial(Monomial[] monomials) {
        this.monomials = monomials;
        // TODO complete
    }

    /**
     * @return the number of monomials in this polynomial
     */
    public int getMonomialCount() {
        return monomials.length;
        // TODO complete
    }

    /**
     * @return a safe copy of the monomial at the given index
     * @pre 0<=index < getMonomialCount()
     */
    public Monomial getMonomial(int index) {
        return monomials[index].getCopy();
        // TODO complete
    }

    /**
     * @pre other != null
     * @post Creates a new Polynomial which is the sum of this polynomial and
     * other. E.g., the sum of 13b^2x^3z+15 and -4b^2x^3z is
     * 9b^2x^3z+15
     */
    public Polynomial add(Polynomial other) {
        List<Monomial> monoms = new ArrayList<Monomial>();
        for(int i = 0; i < monomials.length; i++){
            monoms.add(monomials[i].getCopy());
        }

        for (int i = 0; i < other.getMonomialCount(); i++) {
            Monomial m = other.getMonomial(i);

            int index = indexOfEquivalentMonomial(monoms.iterator(), m);
            if (index != -1) {
                int coefficient = monoms.get(i).getCoefficient() + m.getCoefficient();
                if (coefficient != 0) {
                    monoms.get(i).setCoefficient(coefficient);
                } else {
                    monoms.remove(index);
                }
            } else {
                monoms.add(m.getCopy());
            }
        }

        return new Polynomial(monoms.toArray(new Monomial[monoms.size()]));
    }

    /**
     * @pre other != null
     * @post Creates a new Polynomial which is the product of this polynomial
     * and other. E.g., the product of 13b^2x^3z+15 and -4b^2x^3z is
     * -52b^4x^6z^2-60b^2x^3z
     */
    public Polynomial multiply(Polynomial other) {
        List<Monomial> monoms = new ArrayList<Monomial>();

        for (int i = 0; i < getMonomialCount(); i++) {
            for (int j = 0; j < other.getMonomialCount(); j++) {
                Monomial m = getMonomial(i).multiply(other.getMonomial(j));
                int index = indexOfEquivalentMonomial(monoms.iterator(), m);
                if (index == -1) {
                    monoms.add(m.getCopy());
                } else {
                    Monomial monom = monoms.get(index);
                    monom.setCoefficient(monom.getCoefficient() + m.getCoefficient());
                }
            }
        }

        return new Polynomial(monoms.toArray(new Monomial[monoms.size()]));
        // TODO complete
    }

    /**
     * @return the result of assigning assignment[0] to a, assignment[1] to b
     * etc., and computing the value of this Polynomial
     * @pre assignment != null
     * @pre assignment.length == 26
     */
    public int evaluate(int[] assignment) {
        int val = 0;
        for (int i = 0; i < getMonomialCount(); i++) {
            val += getMonomial(i).evaluate(assignment);
        }
        return val;
        // TODO complete
    }


    /**
     * Returns a string representation of this polynomial by the mathematical
     * convention, but without performing normalization (summing of monomials).
     * I.e., each monomial is printed according to Monomial.toString(), for
     * example 13b^2x^3z+15-4b^2x^3z
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < getMonomialCount(); i++) {
            String s = getMonomial(i).toString();
            if (s.charAt(0) != '-' && !result.isEmpty()) {
                result += "+" + s;
            } else {
                result += s;
            }
        }

        return result;
        // TODO complete
    }

    private static int indexOfEquivalentMonomial(Iterator<Monomial> iterator, Monomial m) {
        int i = 0;
        while (iterator.hasNext()) {
            if (iterator.next().hasSameDegrees(m)) {
                return i;
            }

            i++;
        }

        return -1;
    }
}
