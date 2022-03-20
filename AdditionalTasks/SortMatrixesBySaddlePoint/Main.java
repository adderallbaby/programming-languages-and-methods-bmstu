import java.util.Arrays;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        int m, n, i, j, k = 0, com = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter m and n");
        m = in.nextInt();
        n = in.nextInt();
        Matrix[] mat = new Matrix[3];
        while ((com == 1) || (com == 2))
        {
            System.out.println("Enter 1 if you want to sort and see sorted matrices, enter 2 if you want to add a matrix");
            com = in.nextInt();
            if (com == 2)
            {
                int[][] arr = new int[m][n];
                for (i = 0; i < m; i++)
                    for (j = 0; j < n; j++)
                        arr[i][j] = in.nextInt();
                mat[k] = new Matrix(m, n, arr);
                Matrix.getValue(mat[k]);
                k++;
            }
            if (com == 1)
            {
                System.out.println(k);

                Arrays.sort(mat);
                for (i = 0 ; i < k ; i++)
                    Matrix.displayMatrix(mat[i]);
            }
        }
    }
}
