import static java.lang.Math.acos;
import static java.lang.Math.sqrt;

public class Vector implements Comparable <Vector>{
    private double[] vectorCoordinates = new double[3];
    public Vector(double[] vectorCoordinates){
        this.vectorCoordinates = vectorCoordinates;
    }
    @Override
    public int compareTo(Vector vector) {
        if (acos(getCos(vector)) > acos(getCos(new Vector(vectorCoordinates)))){
            return 1;
        }else  {
                if((acos(getCos(vector)) < acos(getCos(new Vector(vectorCoordinates))))){
                return -1;
            }else return 0;}
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2){double multiply = 0;
        for(int i = 0; i < 3; i++){multiply += vector1.vectorCoordinates[i] * vector2.vectorCoordinates[i];}
        return multiply;}
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
        for (int i = 0; i < 3; i++){length += vector.vectorCoordinates[i] * vector.vectorCoordinates[i];}return sqrt(length);}
    public static double getCos(Vector vector1){
        Vector vector2 = getVectorProjection(vector1);
        double cos = getScalarMultiply(vector1, vector2) / (getVectorLength(vector1) *getVectorLength(vector2));
        return cos;
        }

    public static void printCoordinatesString(Vector vector){
        for (int i = 0; i < 3; i++){
            System.out.print(vector.vectorCoordinates[i]);
            System.out.print(" ");

        }

    }


}
