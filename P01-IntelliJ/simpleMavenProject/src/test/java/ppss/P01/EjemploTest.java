package ppss.P01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EjemploTest {
    Ejemplo ejemplo;

    @BeforeEach
    public void setup() {
        ejemplo= new Ejemplo();
    }

    @Test
    void C1_fechaValida_should_be_true_when_date_is_29_2_2020() {
        //Preparamos los datos (Arrange)
        int dia = 29;
        int mes = 2;
        int anyo = 2020;
        boolean resultadoEsperado = true;
        //Ejecutamos (Act)
        boolean resultadoReal = ejemplo.fechaValida(dia,mes,anyo);
        assertEquals(resultadoEsperado, resultadoReal);
    }
    @Test
    void C1_fechaValida_should_be_true_when_dia_31_and_mes_8() {
        //Preparamos los datos (Arrange)
        int dia = 31;
        int mes = 8;
        int anyo = 2019;
        boolean resultadoEsperado = true;
        //Ejecutamos (Act)
        boolean resultadoReal = ejemplo.fechaValida(dia,mes,anyo);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void C1_fechaValida_should_be_false_when_dia_is_negative() {
        //Preparamos los datos (Arrange)
        int dia = -2;
        int mes = 5;
        int anyo = 2019;
        boolean resultadoEsperado = false;
        //Ejecutamos (Act)
        boolean resultadoReal = ejemplo.fechaValida(dia,mes,anyo);
        assertEquals(resultadoEsperado, resultadoReal);
    }
}
