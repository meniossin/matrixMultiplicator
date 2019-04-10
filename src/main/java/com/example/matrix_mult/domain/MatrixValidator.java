package com.example.matrix_mult.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MatrixValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MatrixGenerator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MatrixGenerator mg = (MatrixGenerator) target;

        if(mg.getMatrix1().getColumnSize() != mg.getMatrix2().getRowSize()){
            errors.rejectValue("matrix1.columnSize", "500", null, "The number of columns of first matrix must be equal to the number of rows of second matrix.");
        }
    }
}
