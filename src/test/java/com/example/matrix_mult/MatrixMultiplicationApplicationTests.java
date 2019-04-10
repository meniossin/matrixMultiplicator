package com.example.matrix_mult;

import com.example.matrix_mult.controller.MatrixController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrixMultiplicationApplicationTests {

    @Autowired
    MatrixController controller;
    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
