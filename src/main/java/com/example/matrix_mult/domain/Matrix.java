package com.example.matrix_mult.domain;

import java.util.Arrays;


public class Matrix {

    private int rowSize; // number of rows

    private int columnSize; //number of columns

    private int[][] data;// M-by-N array

    private String dataToString;

    /**CONSTRUCTORS**/
    public Matrix(int rowSize, int columnSize ){
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.data = new int [rowSize][columnSize];
        this.dataToString = "";
    }
    
    public Matrix() {
    	
    }

    /**GETTERS**/
    public int getRowSize(){
        return this.rowSize;
    }

    public int getColumnSize(){
        return  this.columnSize;
    }
    
    public int[][] getData(){
    	return this.data;
    }

    public String getDataToString(){return this.dataToString;}

    /**SETTERS**/
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setColumnSize(int columnSize){
        this.columnSize = columnSize;
    }

    public void setData(int[][] data){
        this.data = data;
    }


    public String setDataToString(){
        dataToString = Arrays.deepToString(data);

        return dataToString;
    }
    

    /**METHODS**/

    // return C(n x p) = A(n x m) * B(m x p)
    public Matrix times(Matrix B) {
        Matrix A = this;

        Matrix C = new Matrix(A.rowSize, B.columnSize);

        for (int i = 0; i < this.rowSize; i++)
            for (int j = 0; j < B.columnSize; j++)
                for (int k = 0; k < this.columnSize; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

}
