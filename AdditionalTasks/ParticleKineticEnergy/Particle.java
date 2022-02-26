
public class Particle {
    private int[] velocityVector = new int[3];
    private int mass;
    public Particle(int[] velocityVector, int mass){
        this.velocityVector = velocityVector;
        this.mass = mass;
    }
    public static double getCurrentParticleKineticEnergy(Particle particle){
        double energy = 0;
        long velocity = 0;
        for (int i = 0; i < 3; i++){
            velocity += particle.velocityVector[i] * particle.velocityVector[i];
        }

        energy  = (velocity * particle.mass)/2;
        return energy;
    }
    public static double getAllParticlesKineticEnergy(Particle[] particles, int length){
        double allEnergy = 0;
        for (int i = 0; i < length; i++){
            allEnergy += getCurrentParticleKineticEnergy(particles[i]);
        }
        return allEnergy;
    }
}
