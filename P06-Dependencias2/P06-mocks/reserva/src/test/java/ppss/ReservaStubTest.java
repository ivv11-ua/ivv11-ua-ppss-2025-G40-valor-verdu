package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaStubTest {
    private Reserva reservaStub;
    private FactoriaBOs factoriaStub;
    private IOperacionBO operacionStub;

    String login, password, socio;
    String[] isbns;

    @BeforeEach
    public void setUp() {
        reservaStub = EasyMock.partialMockBuilder(Reserva.class)
                .addMockedMethod("compruebaPermisos").niceMock();
        factoriaStub = EasyMock.niceMock(FactoriaBOs.class);
        reservaStub.setFd(factoriaStub);
        operacionStub = EasyMock.niceMock(IOperacionBO.class);
    }

    @Test
    void realizaReservaC1() {
        //PREPARAR DATOS ENTRADA
        login = "xxxx";
        password = "xxxx";
        socio = "Pepe";
        isbns = new String[]{"33333"};

        // crear dobles --> @BeforeEach

        //PROGRAMAR LAS EXPECTATIVAS
        EasyMock.expect(reservaStub.compruebaPermisos(anyString(), anyString(),anyObject())).andStubReturn(false);
        // no se invoca a io.operacionReserva()

        //activamos el stub
        EasyMock.replay(reservaStub);

        // invocamos al SUT
        ReservaException exception = assertThrows(ReservaException.class,
                () -> reservaStub.realizaReserva(login, password, socio, isbns));

        // resultado esperado
        String mensajeEsperado = "ERROR de permisos; ";

        // informe
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    void realizaReservaC2() {
        //PREPARAR DATOS ENTRADA
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222","33333"};

        // crear dobles --> @BeforeEach

        //PROGRAMAR LAS EXPECTATIVAS
        EasyMock.expect(reservaStub.compruebaPermisos(anyString(), anyString(),anyObject())).andStubReturn(true);
        EasyMock.expect(factoriaStub.getOperacionBO()).andStubReturn(operacionStub);
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[0])
        );

        //activamos el stub
        EasyMock.replay(reservaStub, factoriaStub, operacionStub);

        // invocamos al SUT
        assertDoesNotThrow(() -> reservaStub.realizaReserva(login, password, socio, isbns));

        // resultado esperadoç

        // informe
    }

    @Test
    void realizaReservaC3() {
        //PREPARAR DATOS ENTRADA
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111","22222","55555"};

        // crear dobles --> @BeforeEach

        //PROGRAMAR LAS EXPECTATIVAS
        EasyMock.expect(reservaStub.compruebaPermisos(anyString(), anyString(),anyObject())).andStubReturn(true);
        EasyMock.expect(factoriaStub.getOperacionBO()).andStubReturn(operacionStub);
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[0])
        );
        EasyMock.expectLastCall().andStubThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[1])
        );
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[2])
        );
        EasyMock.expectLastCall().andStubThrow(new IsbnInvalidoException());

        //activamos el stub
        EasyMock.replay(reservaStub, factoriaStub, operacionStub);

        // invocamos al SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> reservaStub.realizaReserva(login, password, socio, isbns));

        // resultado esperado
        String mensajeEsperado = "ISBN invalido:11111; ISBN invalido:55555; ";


        // informe
        assertEquals(mensajeEsperado, real.getMessage());

    }


    @Test
    void realizaReservaC4() {
        //PREPARAR DATOS ENTRADA
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[]{"22222"};

        // crear dobles --> @BeforeEach

        //PROGRAMAR LAS EXPECTATIVAS
        EasyMock.expect(reservaStub.compruebaPermisos(anyString(), anyString(),anyObject())).andStubReturn(true);
        EasyMock.expect(factoriaStub.getOperacionBO()).andStubReturn(operacionStub);
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[0])
        );
        EasyMock.expectLastCall().andStubThrow(new SocioInvalidoException());


        //activamos el stub
        EasyMock.replay(reservaStub, factoriaStub, operacionStub);

        // invocamos al SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> reservaStub.realizaReserva(login, password, socio, isbns));

        // resultado esperado
        String mensajeEsperado = "SOCIO invalido; ";


        // informe
        assertEquals(mensajeEsperado, real.getMessage());

    }

    @Test
    void realizaReservaC5() {
        //PREPARAR DATOS ENTRADA
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111","22222","333333"};

        // crear dobles --> @BeforeEach

        //PROGRAMAR LAS EXPECTATIVAS
        EasyMock.expect(reservaStub.compruebaPermisos(anyString(), anyString(),anyObject())).andStubReturn(true);
        EasyMock.expect(factoriaStub.getOperacionBO()).andStubReturn(operacionStub);
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[0])
        );
        EasyMock.expectLastCall().andStubThrow(new IsbnInvalidoException());
        assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[1])
        ); assertDoesNotThrow(
                ()->operacionStub.operacionReserva(socio,isbns[2])
        );
        EasyMock.expectLastCall().andStubThrow(new JDBCException());


        //activamos el stub
        EasyMock.replay(reservaStub, factoriaStub, operacionStub);

        // invocamos al SUT
        ReservaException real = assertThrows(ReservaException.class,
                () -> reservaStub.realizaReserva(login, password, socio, isbns));

        // resultado esperado
        String mensajeEsperado = "ISBN invalido:" + isbns[0] + "; CONEXION invalida; ";


        // informe
        assertEquals(mensajeEsperado, real.getMessage());

    }

}
