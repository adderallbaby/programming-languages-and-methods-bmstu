

public class Set {

    int[] SetElements;
    public Set(int[] SetElements){
        this.SetElements = SetElements;
    }
    public static Set intersect(Set set1, Set set2){
        Set set3 = new Set(new int[64]);
        for(int i = 0; i < 64; i++){
            if(set1.SetElements[i] == set2.SetElements[i] && set1.SetElements[i] !=0){
                set3.SetElements[i] = 1;
            }
        }
        return set3;
    }
    public static Set unit(Set set1, Set set2){
        Set set3 = new Set(new int[64]);
        for(int i = 0; i < 64; i++){
            if(set1.SetElements[i] == 1 || set2.SetElements[i] == 1){
                set3.SetElements[i] = 1;
            }
        }
        return set3;
    }
    public static String isInTheSet(Set set1, int x){
        String flag = "no";
        if(set1.SetElements[x-1] == 1){
            flag = "yes";
        }
        return flag;
    }

}
