package ppss.ejercicio2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_MyClass {
    MyClass myclass;
    @BeforeEach
    public void setUp() {
        myclass = new MyClass();
    }

    @Test
    public void calcularKeyOK() {
        int resultado_esperado = 10;
        int resultado_real;

        myclass.setId(2);
        myclass.setSecret(5);
        myclass.setName("My secret key");

        resultado_real = myclass.calculateKey();

        Assertions.assertEquals(resultado_esperado,resultado_real);
    }

}
