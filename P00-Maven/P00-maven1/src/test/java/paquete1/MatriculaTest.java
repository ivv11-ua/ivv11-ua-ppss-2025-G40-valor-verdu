package paquete1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Tests para la clase Matricula")
public class MatriculaTest {
    Matricula mat;

    @BeforeEach
    public void setup() {
        mat= new Matricula();
    }
    
    @Test
    @DisplayName("Test con edad=23")
    public void C1_calculaTasaMatricula_should_return_250_when_edad_23_and_fNumerosa_and_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 23;
        boolean familiaNumerosa = true;
        boolean repetidor = true;
        float resultadoEsperado = 250.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        /* Aclaración: el tercer parámetro del método Assert.assertEquals es necesario si
           estamos comparando "floats"
           en este caso el método devuelve cierto si:
                  resultadoEsperado = resultadoReal +/- 0.002
         */
        //Verificamos el resultado (Assert) 
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void C2_calculaTasaMatricula_should_return_250_when_edad_23_and_fNumerosa_and_not_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 23;
        boolean familiaNumerosa = true;
        boolean repetidor = false;
        float resultadoEsperado = 250.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void C3_calculaTasaMatricula_should_return_2000_when_edad_23_and_not_fNumerosa_and_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 23;
        boolean familiaNumerosa = false;
        boolean repetidor = true;
        float resultadoEsperado = 2000.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void C4_calculaTasaMatricula_should_return_250_when_edad_40_and_fNumerosa_and_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 40;
        boolean familiaNumerosa = true;
        boolean repetidor = true;
        float resultadoEsperado = 250.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void C5_calculaTasaMatricula_should_return_400_when_edad_60_and_fNumerosa_and_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 60;
        boolean familiaNumerosa = true;
        boolean repetidor = true;
        float resultadoEsperado = 400.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void C6_calculaTasaMatricula_should_return_250_when_edads_70_and_fNumerosa_and_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 70;
        boolean familiaNumerosa = true;
        boolean repetidor = true;
        float resultadoEsperado = 250.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }

    @Test
    public void C7_calculaTasaMatricula_should_return_250_when_edad_40_and_fNumerosa_and_not_repetidor() {
        //Preparamos los datos (Arrange)
        int edad = 40;
        boolean familiaNumerosa = true;
        boolean repetidor = false;
        float resultadoEsperado = 250.00f;
        //Ejecutamos (Act)
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }
}
