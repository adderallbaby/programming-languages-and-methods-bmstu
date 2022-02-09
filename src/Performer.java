import java.util.Scanner;


public class Performer{
    public static Set intersect(Set set1, Set set2){
        Set set3 = new Set(new int[64]);
        for(int i = 0; i < 64; i++){
            if(set1.setarray[i] == set2.setarray[i] && set1.setarray[i] !=0){
                set3.setarray[i] = 1;
            }
        }
        return set3;
    }
    public static Set unit(Set set1, Set set2){
        Set set3 = new Set(new int[64]);
        for(int i = 0; i < 64; i++){
            if(set1.setarray[i] == 1 || set2.setarray[i] == 1){
                set3.setarray[i] = 1;
            }
        }
        return set3;
    }
    public static String isInTheSet(Set set1, int x){
        String flag = "no";
        if(set1.setarray[x-1] == 1){
            flag = "yes";
        }
        return flag;
    }
    public static void main(String[] args){
        int n1, n2, x;
        Set set1 = new Set(new int[64]);
        Set set2 = new Set(new int[64]);


        Scanner scanner = new Scanner(System.in);
        n1 = scanner.nextInt();
        n2= scanner.nextInt();
        x = scanner.nextInt();
        for(int i = 0; i < n1; i++){
            set1.setarray[scanner.nextInt()- 1] = 1;
        }
        for(int i = 0; i < n2; i++){
            set2.setarray[scanner.nextInt()- 1] = 1;
        }
        for(int i = 0; i < 64; i++){
            if(intersect(set1,set2).setarray[i] == 1)
                System.out.print(i + 1 + " ");
        }
        System.out.println();
        for(int i = 0; i < 64; i++){
            if(unit(set1,set2).setarray[i] == 1)
                System.out.print(i + 1 + " ");
        }
        System.out.println();
        System.out.println(isInTheSet(set1, x));
    }
}






