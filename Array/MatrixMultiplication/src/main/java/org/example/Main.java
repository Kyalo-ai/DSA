package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {


        int[][] A = {{1,2,3},{4,5,6}};
        int[][] B = {{7,8},{9,18},{11,9}};
        int[][] C = new int[2][2];


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        System.out.println(C[0][0] + " " + C[0][1]);
        System.out.println(C[1][0] + " " + C[1][1]);
    }
}