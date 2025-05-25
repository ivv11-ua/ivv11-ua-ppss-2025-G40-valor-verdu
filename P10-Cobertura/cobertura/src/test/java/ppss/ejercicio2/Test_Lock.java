package ppss.ejercicio2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_Lock {

    @Test
    public void testUnLockOK() {
        Lock candado = new Lock(10);
        int clave_desbloqueo=10;
        boolean resultado_esperado = true;
        boolean resultado_real;

        resultado_real = candado.unlock(clave_desbloqueo);
        Assertions.assertEquals(resultado_esperado, resultado_real);
    }

    @Test
    public void testUnLockFailed() {
        Lock candado = new Lock(10);
        int clave_desbloqueo = 11;
        boolean resultado_esperado = false;
        boolean resultado_real;

        resultado_real = candado.unlock(clave_desbloqueo);
        Assertions.assertEquals(resultado_esperado, resultado_real);
    }

}
