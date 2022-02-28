import java.util.Scanner;
public class Performer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();
        Vector[] listOfVectors = new Vector[n];
        for (int i = 0; i < n; i++) {
            double[] vectorCoordinates = new double[3];
            System.out.print("Enter vector coordinates {x,y,z}: ");
            for (int j = 0; j < 3; j++) {
                vectorCoordinates[j] = scanner.nextInt();
            }
            Vector currentVector = new Vector(vectorCoordinates);
            listOfVectors[i] = currentVector;
        }
        Vector.sortByAngleToXOY(listOfVectors, n);
        for(int i = 0; i < n; i++){
            Vector.printCoordinatesString(listOfVectors[i]);
            System.out.println("\n");
        }
    }
}

