import java.util.Arrays;

/**
 * Created by Amit on 11/11/2015.
 */
public class ArrayUtils {

    public static void main(String[] args){
        System.out.println(Arrays.toString(shiftArrayToTheRight(new int[]{1, 2, 3, 4, 5}, 1)));
        System.out.println(Arrays.toString(shiftArrayToTheRight(new int[]{1, 2, 3, 4, 5}, 3)));

        System.out.println(matrixTrace(new int[][]{{1,2,3},{1,2,3},{1,2,3}}));
        System.out.println(matrixTrace(new int[][]{{1,3,4},{1,2,5},{1,2,3}}));

        int[][] m;

        m = matrixSwitchRows(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 0, 2);
        printMatrix(m);
        m = matrixSwitchRows(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 1, 1);
        printMatrix(m);
        m = matrixSwitchRows(new int[][]{{1,2},{4,5},{7,8}}, 2, 1);
        printMatrix(m);

        m = matrixScalarRow(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 0, 2);
        printMatrix(m);
        m = matrixScalarRow(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, 2, 1);
        printMatrix(m);
        m = matrixScalarRow(new int[][]{{1,2},{4,5},{7,8}}, 2, 1);
        printMatrix(m);

        m = matrixMultiplication(new int[][]{{1,2,3},{4,5,6},{7,8,9}}, new int[][]{{1,0,0},{0,1,0},{0,0,1}});
        printMatrix(m);
        m = matrixMultiplication(new int[][]{{1,2},{3,4},{5,6}}, new int[][]{{1,1},{2,2}});
        printMatrix(m);
    }

    public static void printMatrix(int[][] m){
        for(int[] row: m){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static int[] shiftArrayToTheRight(int[] array, int move){
        if(move <= 0){ //Is zero legal or not?
            return array;
        }

        int[] result = new int[array.length];
        for(int i = 0; i < array.length; i++){
            result[(i+move)%array.length] = array[i];
        }

        return result;
    }

    public static int matrixTrace(int[][] m){
        int result = 0;
        for(int i = 0; i < m.length; i++){
            result += m[i][i];
        }
        return result;
    }

    public static int[][] matrixSwitchRows(int[][] m, int i, int j){
        for(int k = 0; k < m[i].length; k++){
            int temp = m[i][k];
            m[i][k] = m[j][k];
            m[j][k] = temp;
        }
        return m;
    }

    public static int[][] matrixScalarRow(int[][] m, int s, int i){
        for(int j = 0; j < m[i].length; j++){
            m[i][j] = s*m[i][j];
        }
        return m;
    }

    public static int[][] matrixMultiplication(int[][] m, int[][] n){
        int[][] result = new int[m.length][];
        for(int i = 0; i < m.length; i++){
            result[i] = new int[n[0].length];

            for(int j = 0; j < n[0].length; j++){
                int res = 0;
                for(int k = 0; k < n.length; k++){
                    res += m[i][k]*n[k][j];
                }
                result[i][j] = res;
            }
        }
        return result;
    }
}
