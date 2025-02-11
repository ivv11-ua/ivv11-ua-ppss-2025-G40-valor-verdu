package paquete2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests para la clase Triangulo")
public class TrianguloTest {
    Triangulo tri;

    @BeforeEach
    public void setup() {
       tri= new Triangulo();
    }
    
    @Test
    public void C1_tipo_triangulo_should_be_Equilatero_when_three_sides_are_equals() {
       //Preparamos los datos (Arrange)
       int a = 1;
       int b = 1;
       int c = 1;
       String resultadoEsperado = "Equilatero";
       //Ejecutamos (Act)
       String resultadoReal = tri.tipo_triangulo(a,b,c);
       //Verificamos el resultado (Assert)
       /* Aclaración: El método Assert.assertEquals devuelve cierto si el
           resultadoEsperado = resultadoReal */
       assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C2_tipo_triangulo_should_be_No_triangulo_when_sum_of_a_and_b_is_lower_than_c() {
        //Preparamos los datos (Arrange)
        int a = 1;
        int b = 1;
        int c = 11;
        String resultadoEsperado = "No es un triangulo";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C3_tipo_triangulo_should_be_Isosceles_when_last_two_sides_are_equals() {
        //Preparamos los datos (Arrange)
        int a = 14;
        int b = 10;
        int c = 10;
        String resultadoEsperado = "Isosceles";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C4_tipo_triangulo_should_be_Escaleno_when_three_sides_are_different() {
        //Preparamos los datos (Arrange)
        int a = 14;
        int b = 10;
        int c = 12;
        String resultadoEsperado = "Escaleno";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void C5_tipo_triangulo_should_be_b_Fuera_rango_when_b_0() {
        //Preparamos los datos (Arrange)
        int a = 1;
        int b = 0;
        int c = 2;
        String resultadoEsperado = "Valor b fuera del rango permitido";
        //Ejecutamos (Act)
        String resultadoReal = tri.tipo_triangulo(a,b,c);
        //Verificamos el resultado (Assert)
        assertEquals(resultadoEsperado, resultadoReal);
    }
}
