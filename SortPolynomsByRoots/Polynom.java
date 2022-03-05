public class Polynom implements Comparable <Polynom>{
    private int N;
    private double[] coeff = new double[N];

    public Polynom(int N, double[] coeff){
        this.N = N;
        this.coeff = coeff;


    }
    public static int getSolution(int x, Polynom polynom){
        int answer = 0;
        for(int i = 0; i <= polynom.N; i++){
            answer += polynom.coeff[polynom.N - i] * Math.pow(x, i);
        }
        if (answer == 0) return 1; else return 0;
    }

    public static int getAmountOfAnswers(Polynom polynom) {
        int am = 0;
        for (int i = 0; i < 11; i++) {
            if (getSolution(i, polynom) == 1) {
                am += 1;

            }

        }
        return am;
    }

    @Override
    public int compareTo(Polynom o) {
        if(getAmountOfAnswers(o)> getAmountOfAnswers(new Polynom(N, coeff))){
            return 1;
        }else if (getAmountOfAnswers(o)< getAmountOfAnswers(new Polynom(N, coeff))) return -1;
        else {
            return 0;
        }
    }
    public static void printPoly(Polynom polynom){
        for(int i = 0; i <= polynom.N; i++){
            System.out.print(polynom.coeff[i] + " ");
        }
        System.out.print(getAmountOfAnswers(polynom));
    }
}
