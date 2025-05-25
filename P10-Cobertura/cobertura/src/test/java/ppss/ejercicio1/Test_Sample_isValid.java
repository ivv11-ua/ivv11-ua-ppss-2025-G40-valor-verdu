package ppss.ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_Sample_isValid {
    Sample sut;

    @BeforeEach
    void setUp() {
        sut = new Sample();
    }

    @Test
    public void C1_isValid_should_return_true_when_all_data_values_are_valid(){
        int [] datos= {10,10,10,10};
        boolean resultado_esperado = true;

        boolean resultado_real = sut.isValid(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }

}