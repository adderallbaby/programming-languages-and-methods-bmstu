

public class Matrix implements Comparable<Matrix>
{
    int m, n;
    int[][] mat;
    int value;

    public Matrix(int m, int n, int [][] arr)
    {
        this.m = m;
        this.n = n;
        this.mat = arr;
    }

    public static int getValue(Matrix a)
    {
        int i, j, fl = 0, max = a.mat[0][0];
        int[] mini = new int[a.m];
        int[] maxj = new int[a.n];
        for (i = 0 ; i < a.m ; i++)
        {
            for (j = 0 ; j < a.n ; j++)
            {
                if (a.mat[i][j] > max)
                    max = a.mat[i][j];
                if (i == 0)
                    maxj[j] = a.mat[i][j];
                if (j == 0)
                    mini[i] = a.mat[i][j];
                if (a.mat[i][j] > maxj[j])
                    maxj[j] = a.mat[i][j];
                if (a.mat[i][j] < mini[i])
                    mini[i] = a.mat[i][j];
            }
        }
        for (i = 0 ; i < a.m ; i++)
            for (j = 0 ; j < a.n ; j++)
            {
                if ((fl == 0) && (mini[i] == maxj[j]))
                {
                    a.value = mini[i];
                    fl = 1;
                }
                if ((fl == 1) && (mini[i] == maxj[j]) && (mini[i] > a.value))
                    a.value = mini[i];
            }
        if (fl == 0)
            a.value = max;
        return a.value;
    }

    public static void displayMatrix(Matrix mat)
    {
        int i, j;
        for (i = 0 ; i < mat.m ; i++)
        {
            for (j = 0 ; j < mat.n ; j++)
                System.out.print(mat.mat[i][j] + " ");
            System.out.print("\n");
        }
        System.out.print(mat.value + "\n\n");
    }

    public int compareTo(Matrix o) {
        if(getValue(o) > getValue(new Matrix(m,n,mat))){
            return 1;
        }else if(getValue(o) < getValue(new Matrix(m,n,mat))){
            return -1;
        }else return 0;
    }
}
