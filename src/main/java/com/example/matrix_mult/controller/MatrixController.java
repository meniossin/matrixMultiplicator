package com.example.matrix_mult.controller;

import com.example.matrix_mult.domain.Matrix;
import com.example.matrix_mult.domain.MatrixGenerator;

import com.example.matrix_mult.domain.MatrixValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes("mg")
public class MatrixController {


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

    @PostMapping(value="/inputMatrices")
    public String inputMatrices(@ModelAttribute("mg") MatrixGenerator mg, BindingResult result, RedirectAttributes redAttr, Model model){

        MatrixValidator matrixValidator = new MatrixValidator();

        matrixValidator.validate(mg, result);
        if(result.hasErrors()){
            System.out.println("Error : " + result.getFieldError());
            model.addAttribute("error", result.getFieldError().getDefaultMessage());
            return "home";
        }

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


    @GetMapping(value = "/inputMatrices")
    public String goBackToInput(@ModelAttribute("mg") MatrixGenerator mg){
        return "matrix";
    }


    @PostMapping(value = "/multiplyMatrix", params="print_matrix1")
    public String printMatrix1(@ModelAttribute("mg") MatrixGenerator mg, Model model,  BindingResult result){

        if(result.hasErrors() || mg.getMatrix1().getData() == null ){
            return "matrix";
        }

        mg.getMatrix1().setDataToString();
        model.addAttribute("print", mg.getMatrix1().getDataToString());
        return "print_matrix";
    }


    @PostMapping(value = "/multiplyMatrix", params="print_matrix2")
    public String printMatrix2(@ModelAttribute("mg") MatrixGenerator mg, Model model,  BindingResult result){

        if(result.hasErrors() || mg.getMatrix2().getData() == null ){
            return "matrix";
        }

        mg.getMatrix2().setDataToString();
        model.addAttribute("print", mg.getMatrix2().getDataToString());
        return "print_matrix";
    }
    
    
    @PostMapping(value="/multiplyMatrix")
    public String multiplyMatrix(@ModelAttribute("mg") MatrixGenerator mg, Model model,  BindingResult result) {

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

}
