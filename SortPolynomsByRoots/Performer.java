import java.util.Arrays;
import java.util.Scanner;
public class Performer {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Polynom[] polynoms = new Polynom[N];
        for (int i = 0; i < N; i++) {

            System.out.print("Введите степень полинома: ");
            int step = scanner.nextInt();
            double[] coeffs = new double[step + 1];
            for (int j = 0; j <= step; j++){
                System.out.print("Введите коэффицент при степени: " + (step - j ));
                coeffs[j] = scanner.nextInt();
            }
            Polynom newp = new Polynom(step, coeffs);

            polynoms[i] = newp;
        }
        Arrays.sort(polynoms);
        for(int i = 0; i < N; i++){
            Polynom.printPoly(polynoms[i]);
            System.out.println("\n");
        }
    }
    }





