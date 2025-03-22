package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.junit.jupiter.api.Assertions.*;


public class ReservaMockTest {
    private Reserva mockReserva;
    private FactoriaBOs mockFactoriaBOs;
    private IOperacionBO mockOperacionBO;
    IMocksControl control;

    String login, password,socio;
    String[] isbns;

    @BeforeEach
    public void setUp() {
        control = EasyMock.createStrictControl();
        mockReserva = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("compruebaPermisos").mock(control);
        mockFactoriaBOs = control.mock(FactoriaBOs.class);
        mockOperacionBO = control.mock(IOperacionBO.class);
        mockReserva.setFd(mockFactoriaBOs);
    }

    @Test
    public void realizarReserva_c1() {
        //preparar los datos de entrada
        login = "xxxxx";
        password = "xxxxx";
        socio = "Pepe";
        isbns = new String[]{"33333"};

        //cerar mocks -->BeforeEach

        // preparar las expectativas
        EasyMock.expect(mockReserva.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(false);
        // no se invoca a io.operacionReserva()

        // activamos los mocks
        control.replay();

        //invocamos a la SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> mockReserva.realizaReserva(login, password, socio, isbns));

        // verificamos
        control.verify();

        String mensajeEsperado = "ERROR de permisos; ";

        // informe
        assertEquals(mensajeEsperado, real.getMessage());
    }
    @Test
    public void realizarReserva_c2() {
        //preparar los datos de entrada
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222", "33333"};

        //cerar mocks -->BeforeEach

        // preparar las expectativas
        EasyMock.expect(mockReserva.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(mockFactoriaBOs.getOperacionBO()).andReturn(mockOperacionBO);
        assertDoesNotThrow(
                () -> mockOperacionBO.operacionReserva(socio, isbns[0])
        );
        assertDoesNotThrow(
                () -> mockOperacionBO.operacionReserva(socio, isbns[1])
        );

        // activamos los mocks
        control.replay();

        //invocamos a la SUT
        assertDoesNotThrow(
                () -> mockReserva.realizaReserva(login, password, socio, isbns));

        // verificamos
        control.verify();
    }

    @Test
    public void realizarReserva_c3(){
        //preparar los datos de entrada
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111", "22222", "55555"};

        //cerar mocks -->BeforeEach

        // preparar las expectativas
        EasyMock.expect(mockReserva.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(mockFactoriaBOs.getOperacionBO()).andReturn(mockOperacionBO);
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[0])
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[1])
        );
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[2])
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        // activamos los mocks
        control.replay();

        //invocamos a la SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> mockReserva.realizaReserva(login, password, socio, isbns));

        // verificamos
        control.verify();

        String mensajeEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";

        // informe
        assertEquals(mensajeEsperado, real.getMessage());
    }

    @Test
    public void realizarReserva_c4(){
        //preparar los datos de entrada
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[]{"22222"};

        //cerar mocks -->BeforeEach

        // preparar las expectativas
        EasyMock.expect(mockReserva.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(mockFactoriaBOs.getOperacionBO()).andReturn(mockOperacionBO);
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[0])
        );
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        // activamos los mocks
        control.replay();

        //invocamos a la SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> mockReserva.realizaReserva(login, password, socio, isbns));

        // verificamos
        control.verify();

        String mensajeEsperado = "SOCIO invalido; ";

        // informe
        assertEquals(mensajeEsperado, real.getMessage());
    }
    @Test
    public void realizarReserva_c5(){
        //preparar los datos de entrada
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111","22222","33333"};

        //cerar mocks -->BeforeEach

        // preparar las expectativas
        EasyMock.expect(mockReserva.compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(mockFactoriaBOs.getOperacionBO()).andReturn(mockOperacionBO);
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[0])
        );
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[1])
        );
        assertDoesNotThrow(
                ()->mockOperacionBO.operacionReserva(socio, isbns[2])
        );
        EasyMock.expectLastCall().andThrow(new JDBCException());

        // activamos los mocks
        control.replay();

        //invocamos a la SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> mockReserva.realizaReserva(login, password, socio, isbns));

        // verificamos
        control.verify();

        String mensajeEsperado = "ISBN invalido:11111; CONEXION invalida; ";

        // informe
        assertEquals(mensajeEsperado, real.getMessage());
    }
}
