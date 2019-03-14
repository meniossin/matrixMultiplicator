package com.example.matrix_mult;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MatrixGenerator {

    int[][] matrix;

    @NotNull
    @Size(min=1, max=40)
    private int rowSize;

    @NotNull
    @Size(min=1, max=40)
    private int columnSize;

    public MatrixGenerator(int _rowSize, int _columnSize ){
        this.rowSize = _rowSize;
        this.columnSize = _columnSize;
        this.matrix = new int [rowSize][columnSize];
    }


    public int getRowSize(){
        return this.rowSize;
    }

    public int getColumnSize(){
        return  this.columnSize;
    }

    public void printMatrix(){

        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.columnSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


}
