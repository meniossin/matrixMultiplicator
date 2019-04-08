package com.example.matrix_mult.domain;

public class MatrixGenerator {
	
	private Matrix matrix1;
	private Matrix matrix2;
	
	public MatrixGenerator() {
		
	}
	
	public void setMatrix1(Matrix matrix) {
		this.matrix1 = matrix;
	}
	
	public Matrix getMatrix1() {
		return this.matrix1;
	}
	
	public void setMatrix2(Matrix matrix) {
		this.matrix2 = matrix;
	}
	
	public Matrix getMatrix2() {
		return this.matrix2;
	}
	
}
