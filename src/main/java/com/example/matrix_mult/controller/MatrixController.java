package com.example.matrix_mult.controller;

import com.example.matrix_mult.domain.Matrix;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MatrixController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("matrix", new Matrix(0, 0));
        
        model.addAttribute("matrix2", new Matrix(0, 0));
        
        return "home";
    }

    @PostMapping(value="/printMatrix1")
    public String printMatrix1(@ModelAttribute("matrix") Matrix matrix, @ModelAttribute("matrix") Matrix matrix2,  BindingResult result, HttpServletRequest request, Model model){
    	

        if(!result.hasErrors()){
        	
            System.out.println("Row for matrix 1: " + matrix.getRowSize());
            System.out.println("Column for matrix 1: " + matrix.getColumnSize());
            
            System.out.println("Row for matrix 2: " + matrix2.getRowSize());
            System.out.println("Column for matrix 2: " + matrix2.getColumnSize());
            model.addAttribute("matrix", matrix);
       	    model.addAttribute("matrix2", matrix2);
       	 
            return "matrix";
        }

        else if(result.hasErrors()){
            System.out.println("Error : " + result.getFieldError());
        }

        return "home";
    }
    
    
    
    @PostMapping(value="/multiplyMatrix")
    public String multiplyMatrix() {
    	
    	return "matrix";
    }

}
