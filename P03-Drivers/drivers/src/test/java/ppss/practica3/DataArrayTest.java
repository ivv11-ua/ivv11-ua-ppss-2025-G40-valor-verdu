package ppss.practica3;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import javax.xml.crypto.Data;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DataArrayTest{

    @Test
    void C1_delete_should_return_1_3_7_when_DataArray_is_1_3_5_7_and_elementoAborrar_is_4 (){
        //datos de entrada
        DataArray sut = new DataArray(new int[]{1,3,5,7});

        //resultados esperados
        int[] arrayEsperado = {1,3,7};
        int numElemEsperado = 3;

        //llamar a sut
        assertDoesNotThrow(() -> sut.delete(5), "Excepción lanzada");
        assertAll("TestC1",
                () -> assertArrayEquals(arrayEsperado, sut.getColeccion()),
                () -> assertEquals(numElemEsperado, sut.size())
        );
    }
    @Test
    void C2_delete_should_return_1_3_5_7_when_DataArray_is_1_3_3_5_7_and_elementoAborrar_is_3 (){
        //datos de entrada
        DataArray sut = new DataArray(new int[]{1,3,3,5,7});

        //resultados esperados
        int[] arrayEsperado = {1,3,5,7};
        int numElemEsperado = 4;

        //llamar a sut
        assertDoesNotThrow(() -> sut.delete(3), "Excepción lanzada");
        assertAll("TestC2",
                () -> assertArrayEquals(arrayEsperado, sut.getColeccion()),
                () -> assertEquals(numElemEsperado, sut.size())
        );
    }
    @Test
    void C3_delete_should_return_1_2_3_5_6_7_8_9_10_when_DataArray_is_1_2_3_4_5_6_7_8_9_10_and_elementoAborrar_is_4 (){
        //datos de entrada
        DataArray sut = new DataArray(new int[]{1,2,3,4,5,6,7,8,9,10});

        //resultados esperados
        int[] arrayEsperado = {1,2,3,5,6,7,8,9,10};
        int numElemEsperado = 9;

        //llamar a sut
        assertDoesNotThrow(() -> sut.delete(4), "Excepción lanzada");
        assertAll("TestC3",
                () -> assertArrayEquals(arrayEsperado, sut.getColeccion()),
                () -> assertEquals(numElemEsperado, sut.size())
        );
    }
    @Test
    void C4_delete_should_return_Exception_when_DataArray_is_vacio_and_elementoAborrar_is_8(){
        //datos de entrada
        DataArray sut = new DataArray();

        //resultados esperados (no hace falta)
        //int[] arrayEsperado = {1,2,3,5,6,7,8,9,10};
        //int numElemEsperado = 9;

        //llamar a sut
        DataException exception = assertThrows(DataException.class,
                () -> sut.delete(8));
        assertEquals("No hay elementos en la colección", exception.getMessage());
    }
    @Test
    void C5_delete_should_return_Exception_when_DataArray_is_1_3_5_7_and_elementoAborrar_is_menos5(){
        //datos de entrada
        DataArray sut = new DataArray(new int[]{1,3,5,7});

        //resultados esperados (no hace falta)
        //int[] arrayEsperado = {1,2,3,5,6,7,8,9,10};
        //int numElemEsperado = 9;

        //llamar a sut
        DataException exception = assertThrows(DataException.class,
                () -> sut.delete(-5));
        assertEquals("El valor a borrar debe ser > 0", exception.getMessage());
    }
    @Test
    void C6_delete_should_return_Exception_when_DataArray_is_vacio_and_elementoAborrar_is_0(){
        //datos de entrada
        DataArray sut = new DataArray();

        //resultados esperados (no hace falta)
        //int[] arrayEsperado = {1,2,3,5,6,7,8,9,10};
        //int numElemEsperado = 9;

        //llamar a sut
        DataException exception = assertThrows(DataException.class,
                () -> sut.delete(0));
        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", exception.getMessage());
    }
    @Test
    void C7_delete_should_return_Exception_when_DataArray_is_1_3_5_7_and_elementoAborrar_is_8(){
        //datos de entrada
        DataArray sut = new DataArray(new int[]{1,3,5,7});

        //resultados esperados (no hace falta)
        //int[] arrayEsperado = {1,2,3,5,6,7,8,9,10};
        //int numElemEsperado = 9;

        //llamar a sut
        DataException exception = assertThrows(DataException.class,
                () -> sut.delete(8));
        assertEquals("Elemento no encontrado", exception.getMessage());
    }
    @ParameterizedTest(name = "delete_With_Exceptions_[{index}] Message exception should be \"{0}\" when we want delete {2}")
    @MethodSource("cp_dataArray1")
    @Tag("parametrizado")
    @Tag("conExcepciones")
    @DisplayName("delete_With_Exceptions_")

    void C8_deleteWithExceptions(String message, int[] data, int elem) {
        DataArray sut = new DataArray(data);

        // Ejecutamos la operación y validamos la excepción
        DataException exception = assertThrows(DataException.class,
                () -> sut.delete(elem));

        // Verificamos que el mensaje de la excepción sea el esperado
        assertEquals(message, exception.getMessage());
    }

    // Fuente de datos para el test parametrizado
    private static Stream<Arguments> cp_dataArray1() {
        return Stream.of(
                Arguments.of("No hay elementos en la colección", new int[]{}, 8),
                Arguments.of("El valor a borrar debe ser > 0", new int[]{1, 3, 5, 7}, -5),
                Arguments.of("Colección vacía. Y el valor a borrar debe ser > 0", new int[]{}, 0),
                Arguments.of("Elemento no encontrado", new int[]{1, 3, 5, 7}, 8)
        );
    }



    @ParameterizedTest
    @MethodSource("cp_dataArray2")
    @Tag("parametrizado")
    void C9_deleteWithoutExceptions(int[] esperado, int numElem, int[] data, int elemBorrar) {

        DataArray sut = new DataArray(data);
        // llamar a sut

        assertDoesNotThrow(() -> sut.delete(elemBorrar), "Excepción lanzada");
        assertAll("TestC9",
                () -> assertArrayEquals(esperado, sut.getColeccion()),
                () -> assertEquals(numElem, sut.size())
        );

    }

    private static Stream<Arguments> cp_dataArray2() {

        return Stream.of(
                Arguments.of(new int[]{1,3,7}, 3, new int[]{1,3,5,7}, 5),
                Arguments.of(new int[]{1,3,5,7}, 4, new int[]{1,3,3,5,7}, 3),
                Arguments.of(new int[]{1,2,3,5,6,7,8,9,10}, 9, new int[]{1,2,3,4,5,6,7,8,9,10}, 4)
        );
    }

}
