package ppss.ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_Sample_maxValue {
    SampleTestable_isValid sut;

    @BeforeEach
    void setUp() {
        sut = new SampleTestable_isValid();
    }

    @Test
    void C1_maxValue_should_return_10_when_all_data_values_are_10() {
        int [] datos= {10,10,10,10};
        sut.result_isValid = true;
        Integer resultado_esperado = 10;

        Integer resultado_real = sut.maxValue(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }

    @Test
    void C2_maxValue_should_return_null_when_one_data_value_is_greater_to_80() {
        int [] datos= {10,10,81,10};
        sut.result_isValid = false;
        Integer resultado_esperado = null;

        Integer resultado_real = sut.maxValue(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }

    @Test
    void C3_maxValue_should_return_30_when_rest_of_data_has_lower_values() {
        int [] datos= {10,20,30,10};
        sut.result_isValid = true;
        Integer resultado_esperado = 30;

        Integer resultado_real = sut.maxValue(datos);
        Assertions.assertEquals(resultado_esperado,resultado_real);
    }
}