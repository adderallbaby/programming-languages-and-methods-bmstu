import java.util.Scanner;


public class Performer{

    public static void main(String[] args){
        int n1, n2, x;
        Set set1 = new Set(new int[64]);
        Set set2 = new Set(new int[64]);


        Scanner scanner = new Scanner(System.in);
        n1 = scanner.nextInt();
        n2= scanner.nextInt();
        x = scanner.nextInt();
        for(int i = 0; i < n1; i++){
            set1.SetElements[scanner.nextInt()- 1] = 1;
        }
        for(int i = 0; i < n2; i++){
            set2.SetElements[scanner.nextInt()- 1] = 1;
        }
        for(int i = 0; i < 64; i++){
            if(Set.intersect(set1,set2).SetElements[i] == 1)
                System.out.print(i + 1 + " ");
        }
        System.out.println();
        for(int i = 0; i < 64; i++){
            if(Set.unit(set1,set2).SetElements[i] == 1)
                System.out.print(i + 1 + " ");
        }
        System.out.println();
        System.out.println(Set.isInTheSet(set1, x));
    }
}






