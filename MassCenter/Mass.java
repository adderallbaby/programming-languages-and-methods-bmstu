
public class Mass {
    public int mass,x,y;
    public Mass(int x,int y,int mass){
        this.x = x;
        this.y = y;
        this.mass = mass;
    }
    public static Mass getCenter(Mass[] masses, int n){
        int x = 0;
        int y = 0;
        int avgmass = 0;
        for(int i = 0; i < n; i++){
            x+= masses[i].x * masses[i].mass;
            y+= masses[i].y * masses[i].mass;
            avgmass += masses[i].mass;
        }
        Mass resPoint = new Mass(x/avgmass, y/avgmass, avgmass/n);
        return resPoint;
    }
}
