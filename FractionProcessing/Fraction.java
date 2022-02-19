public class Fraction {
    public int numerator;
    public int denominator;
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    static int gcd(int numerator, int denominator) {
        if (denominator == 0) {
            return numerator;
        }
        return gcd(denominator, numerator % denominator);
    }
    static Fraction simplify(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        if (gcd == 1) {
            return new Fraction(numerator, denominator);
        } else {
            return new Fraction(numerator / gcd, denominator / gcd);
        }
    }
        public static String printFraction (Fraction fraction) {
            return fraction.numerator + "/" + fraction.denominator;
        }
        public static Fraction multiply (Fraction fraction1, Fraction fraction2){
            return simplify(fraction1.numerator* fraction2.numerator, fraction1.denominator * fraction2.denominator);
    }
        public static Fraction sumFractions(Fraction fraction1, Fraction fraction2) {
            return simplify(fraction2.numerator * fraction1.denominator + fraction1.numerator * fraction2.denominator , fraction1.denominator * fraction2.denominator);
    }
    }



