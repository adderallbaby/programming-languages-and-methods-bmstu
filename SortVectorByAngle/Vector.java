
public class Vector {
    private double[] vectorCoordinates = new double[3];
    public Vector(double[] vectorCoordinates){
        this.vectorCoordinates = vectorCoordinates;
    }
    public static void quickSort(Vector[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        double opora = getCos(array[middle]);

        int i = low, j = high;
        while (i <= j) {
            while (getCos(array[i]) < opora) {
                i++;
            }

            while (getCos(array[j]) > opora) {
                j--;
            }

            if (i <= j) {
                Vector temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
    public static double getScalarMultiply(Vector vector1, Vector vector2){
        double multiply = 0;
        for(int i = 0; i < 3; i++){
            multiply += vector1.vectorCoordinates[i] * vector2.vectorCoordinates[i];
        }
        return multiply;
    }
    public static Vector getVectorProjection(Vector vector){
        double[] returnable =  new double[3];
        returnable[0] = vector.vectorCoordinates[0];
        returnable[1] = vector.vectorCoordinates[1];
        returnable[2] = 0;
        Vector vector1 = new Vector(returnable);
        return vector1;
    }
    public static double getVectorLength(Vector vector) {
        double length = 0;
        for (int i = 0; i < 3; i++){
            length += vector.vectorCoordinates[i] * vector.vectorCoordinates[i];
        }
        return length;
    }
    public static double getCos(Vector vector1){
        Vector vector2 = getVectorProjection(vector1);
        double cos = getScalarMultiply(vector1, vector2) / (getVectorLength(vector1) *getVectorLength(vector2));
        return cos;
        }
    public static void sortByAngleToXOY(Vector[] listOfVectors, int length){
        quickSort(listOfVectors, 0,length - 1);
    }
    public static void printCoordinatesString(Vector vector){
        for (int i = 0; i < 3; i++){
            System.out.print(vector.vectorCoordinates[i]);
            System.out.print(" ");
        }
    }
}
