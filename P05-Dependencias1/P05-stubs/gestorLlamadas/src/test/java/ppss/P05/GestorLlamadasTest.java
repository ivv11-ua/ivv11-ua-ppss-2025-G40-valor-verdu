package ppss.P05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorLlamadasTest {
    double resultadoEsperdo, resultadoReal;
    GestorLLamadasTestable sut = new GestorLLamadasTestable();

    @Test
    void calculaConsumoC1(){
        //PREPARAR DATOS DE ENTRADA
        CalendarioStub stub = new CalendarioStub(12); //crear doble
        int minutos = 10;
        sut.setCalendario(stub);

        resultadoEsperdo = 207;
        resultadoReal = sut.calculaConsumo(minutos); // inyectar doble

        // verificar resultados
        assertEquals(resultadoEsperdo, resultadoReal);
    }
    @Test
    void calculaConsumoC2(){
        //PREPARAR DATOS DE ENTRADA
        CalendarioStub stub = new CalendarioStub(21); //crear doble
        int minutos = 10;
        sut.setCalendario(stub);

        resultadoEsperdo = 122;
        resultadoReal = sut.calculaConsumo(minutos); // inyectar doble

        // verificar resultados
        assertEquals(resultadoEsperdo, resultadoReal);
    }
}
