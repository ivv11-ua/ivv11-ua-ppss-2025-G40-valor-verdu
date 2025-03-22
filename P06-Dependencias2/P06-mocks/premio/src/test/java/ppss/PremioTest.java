package  ppss;


import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.partialMockBuilder;
import static org.junit.jupiter.api.Assertions.*;

public class PremioTest {
    private Premio mockPremio;
    private CienteWebService mockCiente;
    IMocksControl ctrl;
    String resultadoEsperado, resultadoReal, premio;

    @BeforeEach
    public void setup() {
        ctrl = EasyMock.createStrictControl();
        mockPremio = partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero").mock(ctrl);
        mockCiente = ctrl.mock(CienteWebService.class);

        //inyectamos el doble
        mockPremio.cliente =  mockCiente;
    }

    @Test
    void compruebaPremio_A(){
        //preparar datos de entrada
        premio = "entrada final Champions";
        resultadoEsperado = "Premiado con entrada final Champions";
        float rand = 0.07f;

        //creamos mocks => en BeforeEach

        //Preparamos las expectativas
        EasyMock.expect(mockPremio.generaNumero()).andReturn(rand);
        assertDoesNotThrow(() ->EasyMock.expect(mockCiente.obtenerPremio()).andReturn(premio));

        //activamos el mock
        ctrl.replay();

        //invocamos a la SUT
        resultadoReal = mockPremio.compruebaPremio();

        //verfimicamos que nuestra SUT ha invocado a los mocks
        ctrl.verify();

        //informe
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void compruebaPremio_B(){
        //preparar datos de entrada
        resultadoEsperado = "No se ha podido obtener el premio";
        float rand = 0.05f;

        //creamos mocks => en BeforeEach

        //Preparamos las expectativas
        EasyMock.expect(mockPremio.generaNumero()).andReturn(rand);
        assertDoesNotThrow(() ->EasyMock.expect(mockCiente.obtenerPremio())
                .andThrow(new ClienteWebServiceException()), "Excepción laznada");

        //activamos el mock
        ctrl.replay();

        //invocamos a la SUT
        resultadoReal = mockPremio.compruebaPremio();

        //verfimicamos que nuestra SUT ha invocado a los mocks
        ctrl.verify();

        //informe
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void compruebaPremio_C(){
        //preparar datos de entrada
        resultadoEsperado = "Sin premio";
        float rand = 0.48f;

        //creamos mocks => en BeforeEach

        //Preparamos las expectativas
        EasyMock.expect(mockPremio.generaNumero()).andReturn(rand);

        //activamos el mock
        ctrl.replay();

        //invocamos a la SUT
        resultadoReal = mockPremio.compruebaPremio();

        //verfimicamos que nuestra SUT ha invocado a los mocks
        ctrl.verify();

        //informe
        assertEquals(resultadoEsperado, resultadoReal);
    }

}
