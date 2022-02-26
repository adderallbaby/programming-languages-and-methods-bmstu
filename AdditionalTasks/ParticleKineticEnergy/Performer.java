import java.util.Scanner;
public class Performer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();
        Particle[] particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            int mass;
            int[] velocityVector = new int[3];
            System.out.print("Enter velocity {x,y,z}: ");
            for (int j = 0; j < 3; j++) {
                velocityVector[j] = scanner.nextInt();
            }
            System.out.print("Enter mass: ");
            mass = scanner.nextInt();
            particles[i] = new Particle(velocityVector, mass);
        }
        System.out.println(Particle.getAllParticlesKineticEnergy(particles, n));
    }
}

