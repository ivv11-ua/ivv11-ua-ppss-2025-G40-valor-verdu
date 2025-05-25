package ppss.ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_Sample_contValues {
    Sample sut;

    @BeforeEach
    void setUp() {
        sut = new Sample();
    }
    @Test
    public void C1_countValues_should_return_40_when_there_are_four_10_data_values(){
        int [] datos= {10,10,10,10};
        int resultado_esperado = 40;

        int resultado_real = sut.countValues(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }
}