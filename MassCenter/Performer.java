
import java.util.Arrays;
import java.util.Scanner;

public class Performer {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mass newMass1 = new Mass(1,1,10);
        Mass newMass2 = new Mass(1,5,10);
        Mass newMass3 = new Mass(5,5,10);
        Mass newMass4 = new Mass(5,1,10);

        Mass[] masses2 = new Mass[1000];
        masses2[0] = newMass1;
        masses2[1] = newMass2;
        masses2[2] = newMass3;
        masses2[3] = newMass4;

        Mass resultMass = Mass.getCenter(masses2,4);
        System.out.println(resultMass.mass);
        System.out.println(Arrays.toString(new int[]{resultMass.y, resultMass.x}));

    }
}
