package ppss.practica3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {

    FicheroTexto sut = new FicheroTexto();

    @Test
    void C1_contarCaracteres_should_return_Exception_when_file_does_not_exist() {
        //Esto verifica si la excepción que se lanza en el metodo contarCaracteres es del tipo FicheroException
        FicheroException exception = assertThrows(FicheroException.class,
                () -> sut.contarCaracteres("ficheroC1.txt"));

        assertEquals("ficheroC1.txt (No existe el archivo o el directorio)", exception.getMessage());
    }
    @Test
    void C2_contarCaracteres_should_return_3_when_file_has_3_chars(){
        //Se pone el assertDoesNotThrows para asegurarse que no lazna ninguna excepción, y
        //si se lanzará no afectaría al código, simplemente fallaría el test
        assertDoesNotThrow(
                () -> assertEquals(3, sut.contarCaracteres("src/test/resources/ficheroCorrecto.txt")));
    }
    @Tag("excluido")
    @Test
    void C3_contarCaracteres_should_return_Exception_when_file_cannot_be_read(){
        Assertions.fail();
    }
    @Tag("excluido")
    @Test
    void C4_contarCaracteres_should_return_Exception_when_file_cannot_be_closed(){
        Assertions.fail();
    }
}
