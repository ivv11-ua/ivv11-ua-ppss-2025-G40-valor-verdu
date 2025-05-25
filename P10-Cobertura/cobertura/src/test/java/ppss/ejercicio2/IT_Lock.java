package ppss.ejercicio2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IT_Lock {
    MyClass certificado;

    @BeforeEach
    void setUp() {
         certificado = new MyClass();
    }

    @Test
    public void unlockOK() {
        certificado.setName("Clave");
        certificado.setId(2);
        certificado.setSecret(5);
        Lock candado = new Lock(10);
        boolean resultado_esperado = true;
        boolean resultado_real;

        resultado_real = candado.unlock(certificado.calculateKey());

        Assertions.assertEquals(resultado_esperado, resultado_real);
   }

    @Test
    public void unlockFailed() {
        certificado.setName("Clave");
        certificado.setId(2);
        certificado.setSecret(6);
        boolean resultado_esperado = false;
        boolean resultado_real;
        Lock candado = new Lock(10);

        resultado_real = candado.unlock(certificado.calculateKey());

        Assertions.assertEquals(resultado_esperado, resultado_real);
    }
}
