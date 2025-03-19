package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class MatriculaAlumnoTest {
    MatriculaAlumnoTestable mat;
    OperacionStub op;

    @BeforeEach
    void setUp(){
        mat = new MatriculaAlumnoTestable();
        op = new OperacionStub();
    }

    @Test
    void validarAsignaturas(){
        //1.Datos
        String dni = "00000000T";
        String [] asignaturas = new String[]{"MD", "ZZ","FBD", "P1"};

        ArrayList<String> cursadas = new ArrayList<>();
        cursadas.add("P1");
        cursadas.add("FC");
        cursadas.add("FFI");
        ArrayList<String> no_existen = new ArrayList<>();
        no_existen.add("YYY");
        no_existen.add("ZZ");

        //Inyeccion
        op.setAsignatura(cursadas,no_existen);
        mat.setOp(op);

        //2.Invocar a la SUT
        JustificanteMatricula real = assertDoesNotThrow(
                ()->mat.validaAsignaturas(dni,asignaturas));

        //3.Comparamos
        JustificanteMatricula esperado = new JustificanteMatricula();
        esperado.setDni(dni);

        ArrayList<String> AsigsEsperadas = new ArrayList<>();
        AsigsEsperadas.add("MD");
        AsigsEsperadas.add("FBD");
        esperado.setAsignaturas(AsigsEsperadas);

        ArrayList<String> erroresEsperados = new ArrayList<>();
        erroresEsperados.add("Asignatura ZZ no existe");
        erroresEsperados.add("Asignatura P1 ya cursada");
        esperado.setErrores(erroresEsperados);

        assertAll(
                ()->assertEquals(esperado.getDni(), real.getDni()),
                ()->assertEquals(esperado.getAsignaturas(), real.getAsignaturas()),
                ()->assertEquals(esperado.getErrores(), real.getErrores())
        );
    }
}
