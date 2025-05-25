package ppss.ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class IT_Sample_maxValue {
    Sample sut;

    @BeforeEach
    void setUp() {
        sut = new Sample();
    }


    @Test
    void C1_maxValue_with_empty_data() {
        int [] datos= { };
        Integer resultado_esperado = null;

        Integer resultado_real = sut.maxValue(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }

    @Test
    void C3_maxValue_with_min_incorrect_value() {
        int [] datos= {8};
        Integer resultado_esperado = null;

        Integer resultado_real = sut.maxValue(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }

    @Test
    void C4_maxValue_with_max_incorrect_value() {
        int [] datos= {93};
        Integer resultado_esperado = null;

        Integer resultado_real = sut.maxValue(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }
    @Test
    void C5_AÑADIDOporMI(){
        //Añade un nuevo tests de integración para maxValue(), en concreto usa como entrada un array
        //con valor null. El resultado esperado debe ser null.
        Assertions.assertNull(sut.maxValue(null));
    }

}