package com.example.matrix_mult.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Matrix {


    @NotNull
    @Size(min=1, max=40)
    private int rowSize; // number of rows

    @NotNull
    @Size(min=1, max=40)
    private int columnSize; //number of columns

    private int[][] data;// M-by-N array

    private String dataToString;

    /**CONSTRUCTOR**/
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


    public void setDataToString(String dataString){
        this.dataToString = dataString;
    }
    
   

    /**METHODS**/
    // print matrix to standard output
    public String show() {

    	for (int i = 0; i < this.rowSize; i++) {
    		 for (int j = 0; j < this.columnSize; j++) {
             	dataToString += this.data[i][j] + " ";
             }
    		 dataToString += System.lineSeparator();
    	}
           
        return dataToString;
    }


    // return C(n x p) = A(n x m) * B(m x p)
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.columnSize != B.rowSize) throw new RuntimeException("Illegal matrix dimensions.");

        Matrix C = new Matrix(A.rowSize, B.columnSize);

        for (int i = 0; i < this.rowSize; i++)
            for (int j = 0; j < B.columnSize; j++)
                for (int k = 0; k < this.columnSize; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

}
