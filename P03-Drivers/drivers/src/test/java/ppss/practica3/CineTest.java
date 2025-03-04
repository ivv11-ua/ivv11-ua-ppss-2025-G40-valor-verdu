package ppss.practica3;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CineTest {
  int solicitados;
  boolean reservaEsperada, reservaReal;
  Cine cine;


  @Test
  @DisplayName("Tests asociados a la clase Cine")
  void C1_reservaButacas_should_return_Exception_when_fila_empty_and_want_3() {
        boolean[] asientos = {};
        solicitados = 3;

        reservaEsperada = false;
        boolean[] asientosEsperados = {};

        cine = new Cine();
        reservaReal = cine.reservaButacasV1(asientos, solicitados);

        assertAll("TestC1",
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
  }
  @Test
  void C2_reservaButacas_should_return_false_when_fila_empty_and_want_zero() {
      boolean[] asientos = {};
      solicitados = 0;

      reservaEsperada = false;
      boolean[] asientosEsperados = {};

      cine = new Cine();
      reservaReal = cine.reservaButacas(asientos, solicitados);

      assertAll("TestC2",
              () -> assertEquals(reservaEsperada, reservaReal),
              () -> assertArrayEquals(asientosEsperados, asientos)
      );
  }
    @Test
    void C3_reservaButacas_should_return_true_when_fila_has_3_seats_free_and_want_2() {
        boolean[] asientos = {false, false, false, true, true};
        solicitados = 2;

        reservaEsperada = true;
        boolean[] asientosEsperados = {true, true, false, true, true};
        cine = new Cine();
        reservaReal = cine.reservaButacasV1(asientos, solicitados);

        assertAll("TestC3",
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
    }
    @Test
    void C4_reservaButacas_should_return_false_when_no_free_seats_and_want_1() {
        boolean[] asientos = {true, true, true};
        solicitados = 1;

        reservaEsperada = false;
        boolean[] asientosEsperados = {true, true, true};
        cine = new Cine();
        reservaReal = cine.reservaButacasV1(asientos, solicitados);

        assertAll("TestC3",
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
    }

    @ParameterizedTest(name = "reservaButacas_[{index}] {argumentsWithNames}")
    @MethodSource("cp_reserva")
    @Tag("parametrizado")
    @DisplayName("reservaButacas_")
    void C5_reservaButacas(boolean reservaEsperada, boolean[] asientosEsperados, boolean[] asientos, int solicitados, String mensaje) {
        Cine sut = new Cine();

        // Llamar al sut
        reservaReal = sut.reservaButacas(asientos, solicitados);




        assertAll("TestC5",
                () -> assertEquals(reservaEsperada, reservaReal, "Fallo en: " + mensaje),
                () -> assertArrayEquals(asientosEsperados, asientos, "Fallo en: " + mensaje)
        );
    }

    private static Stream<Arguments> cp_reserva() {
        return Stream.of(
                Arguments.of(false, new boolean[]{}, new boolean[]{}, 0, "should be false when we want 0 and fila has no seats"),
                Arguments.of(true, new boolean[]{true, true, false, true, true}, new boolean[]{false, false, false, true, true}, 2, "should be true when we want 2 and there are 2 free seats"),
                Arguments.of(false, new boolean[]{true, true, true}, new boolean[]{true, true, true}, 1, "should be false when we want 1 and all seats are already reserved")
        );
    }


}