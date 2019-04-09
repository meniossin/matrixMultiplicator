package com.example.matrix_mult.controller;

import com.example.matrix_mult.domain.Matrix;
import com.example.matrix_mult.domain.MatrixGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes("mg")
public class MatrixController {



    private final String validationError = "The number of columns of first matrix must be equal to the number of rows of second matrix";

    public String getValidationError() {
        return validationError;
    }

    @GetMapping("/")
    public String home(Model model) {
      
        
    	Matrix m1 = new Matrix(0,0);
    	Matrix m2 = new Matrix(0,0);
    	
    	MatrixGenerator mg = new MatrixGenerator();
    	mg.setMatrix1(m1);
    	mg.setMatrix2(m2);
       
    	model.addAttribute("mg", mg);

        return "home";
    }

    @PostMapping(value="/printMatrix1")
    public String printMatrix1(@ModelAttribute("mg") MatrixGenerator mg,  BindingResult result, HttpServletRequest request, Model model){
    	

        if(!result.hasErrors()){

            System.out.println("matrix 1: " + mg.getMatrix1());
            System.out.println("Row for matrix 1: " + mg.getMatrix1().getRowSize());
            System.out.println("Column for matrix 1: " + mg.getMatrix1().getColumnSize());

            System.out.println("matrix 2: " + mg.getMatrix2());
            System.out.println("Row for matrix 2: " + mg.getMatrix2().getRowSize());
            System.out.println("Column for matrix 2: " +mg.getMatrix2().getColumnSize());
            
        	mg.getMatrix1().setData(new int[mg.getMatrix1().getRowSize()][mg.getMatrix1().getColumnSize()]);
        	mg.getMatrix2().setData(new int[mg.getMatrix2().getRowSize()][mg.getMatrix2().getColumnSize()]);

        	model.addAttribute("mg",mg);
            return "matrix";
        }

        else if(result.hasErrors()){
            System.out.println("Error : " + result.getFieldError());
        }

        return "home";
    }
    
    
    @PostMapping(value="/multiplyMatrix")
    public String multiplyMatrix(@ModelAttribute("mg") MatrixGenerator mg, Model model,  BindingResult result, HttpServletRequest request) {

        if(!result.hasErrors() && mg.getMatrix1() != null && mg.getMatrix2() != null){


            Matrix matrixResult = mg.getMatrix1().times(mg.getMatrix2());

            System.out.println("Result matrix:" + matrixResult.setDataToString());
            model.addAttribute("result", matrixResult.getDataToString());
            return "resultMatrix";
        }

        else{
            System.out.println("Error : " + result.getFieldError());

        }

        return "matrix";

    }

    @ExceptionHandler(value = {RuntimeException.class})
    public String MatrixExceptionHandler(Exception ex, Model model) {
        String msg = ex.getMessage();
        model.addAttribute("validationError", msg);

        return  "home";

    }

}
