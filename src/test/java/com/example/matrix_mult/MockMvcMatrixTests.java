package com.example.matrix_mult;


import com.example.matrix_mult.domain.Matrix;
import com.example.matrix_mult.domain.MatrixGenerator;
import com.example.matrix_mult.domain.MatrixValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcMatrixTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHomeEndpoint() throws Exception{
        MatrixGenerator mg = new MatrixGenerator();
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();

        mg.setMatrix1(matrix1);
        mg.setMatrix1(matrix2);

        this.mvc.perform(get("/").sessionAttr("mg", mg)).andExpect(status().isOk()).
                andExpect(model().attributeExists("mg")).andExpect(view().name("home"));
    }

    @Test
    public void testPrintMatrix() throws Exception{

        MatrixGenerator mg = new MatrixGenerator();
        Matrix matrix1 = new Matrix(2,2);
        Matrix matrix2 = new Matrix(2,3);

        mg.setMatrix1(matrix1);
        mg.setMatrix2(matrix2);

        mg.getMatrix1().setRowSize(2);
        mg.getMatrix1().setColumnSize(2);

        mg.getMatrix2().setRowSize(2);
        mg.getMatrix2().setColumnSize(3);

        this.mvc.perform(post("/printMatrix1").sessionAttr("mg",mg)).
                andExpect(content().contentType("text/html;charset=UTF-8")).
                andExpect(view().name("matrix")).
                andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void testMultiplyMatrix() throws Exception{
        MatrixGenerator mg = new MatrixGenerator();
        Matrix matrix1 = new Matrix(2,2);
        Matrix matrix2 = new Matrix(2,3);

        mg.setMatrix1(matrix1);
        mg.setMatrix2(matrix2);

        mg.getMatrix1().setRowSize(2);
        mg.getMatrix1().setColumnSize(2);

        mg.getMatrix2().setRowSize(2);
        mg.getMatrix2().setColumnSize(3);

        this.mvc.perform(post("/multiplyMatrix").sessionAttr("mg",mg)).
                andExpect(content().contentType("text/html;charset=UTF-8")).
                andExpect(view().name("resultMatrix")).
                andExpect(status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void testMatrixValidation() throws Exception{
        MatrixGenerator mg = new MatrixGenerator();
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();

        mg.setMatrix1(matrix1);
        mg.setMatrix2(matrix2);

        mg.getMatrix1().setRowSize(2);
        mg.getMatrix1().setColumnSize(2);

        mg.getMatrix2().setRowSize(3);
        mg.getMatrix2().setColumnSize(3);
        
        Errors errors = new BeanPropertyBindingResult(mg,"mg");
        MatrixValidator validator = new MatrixValidator();
        validator.validate(mg, errors);

        this.mvc.perform(post("/printMatrix1").sessionAttr("mg",mg)).
                andExpect(content().contentType("text/html;charset=UTF-8")).
                andExpect(view().name("home")).andExpect(model().attributeExists("error"));

    }
}
