package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotifyCenterTest {
    private NotifyCenter mockNotifyCenter;
    private MailServer mockMailServer;
    private IMocksControl control;

    @BeforeEach
    void setUp() {
        control = EasyMock.createStrictControl();

        //Mock parcial para NotifyCenter
        mockNotifyCenter= EasyMock.partialMockBuilder(NotifyCenter.class)
                .addMockedMethod("getServer")
                .addMockedMethod("sendNotify")
                .addMockedMethod("getCurrentDate")
                .mock(control);
        // Mock para MailServer
        mockMailServer = control.mock(MailServer.class);
    }
    //public void C1_when_4_mensajes_should_fail_2_and_return_failures_during_sending_process()
    @Test
    public void testCasoA(){
        /// Prepara datos
        LocalDate fechaEnvio= LocalDate.of(2025, 3, 11);
        LocalDate fechaActual = LocalDate.of(2025, 3, 11);
        List<String> emails = Arrays.asList("email1", "email2", "email3", "email4");
        String esperado = "Failures during sending process";

        //Hacemos las expectativas
        // Configurar getServer() para devolver el mock del servidor
        EasyMock.expect(mockNotifyCenter.getServer()).andReturn(mockMailServer);
        EasyMock.expect(mockNotifyCenter.getCurrentDate()).andReturn(fechaActual);
        // El servidor tiene 4 emails pendientes
        EasyMock.expect(mockMailServer.findMailItemsWithDate(fechaEnvio)).andReturn(emails);

        // Configurar sendNotify() dentro de assertDoesNotThrow()
        assertDoesNotThrow(() -> {
                    mockNotifyCenter.sendNotify("email1");
                    EasyMock.expectLastCall();
                    mockNotifyCenter.sendNotify("email2");
                    EasyMock.expectLastCall().andThrow(new FailedNotifyException("error")); // Falla
                    mockNotifyCenter.sendNotify("email3");
                    EasyMock.expectLastCall().andThrow(new FailedNotifyException("error")); // Falla
                    mockNotifyCenter.sendNotify("email4");
                    EasyMock.expectLastCall();
        });

        // Activamos los mocks
        control.replay();

        //Ejecutamos la SUT Y verificamos que lanza la excepción esperada
        FailedNotifyException exception = assertThrows(
                FailedNotifyException.class,
                () -> mockNotifyCenter.notifyUsers(fechaEnvio)
        );
        assertEquals(esperado, exception.getMessage());

        // Verificamos que los mocks fueron llamados
        control.verify();
    }

    //public void C2_when_fechas_diferentes_should_return_date_error(){
    @Test
    public void testCasoB(){
        /// Prepara datos
        LocalDate fechaEnvio= LocalDate.of(2025, 3, 12); //Fecha en la que queremos enviar las notificaciones
        LocalDate fechaActual = LocalDate.of(2025, 2, 12);
        String esperado = "Date error";

        //Mockeamos las expectativas => Hacemos las expectativas
        EasyMock.expect(mockNotifyCenter.getServer()).andReturn(mockMailServer);
        EasyMock.expect(mockNotifyCenter.getCurrentDate()).andReturn(fechaActual);

        // Activamos los mocks
        control.replay();

        //Ejecutamos la SUT Y verificamos que lanza la excepción esperada sea lanzada
        FailedNotifyException real = assertThrows(
                FailedNotifyException.class,
                () -> mockNotifyCenter.notifyUsers(fechaEnvio)
        );
        assertEquals(esperado, real.getMessage());
        // Verificamos interacciones que los mocks fueron llamados
        control.verify();
    }

    //    public void C3_when_no_mensajes_pendientes_should_not_send_no_message(){
    @Test
    public void testCasoC(){
        /// Prepara datos
        LocalDate fechaEnvio= LocalDate.of(2025, 3, 23); //Fecha en la que queremos enviar las notificaciones
        LocalDate fechaActual = LocalDate.of(2025, 3, 23);
        List<String> emails = Arrays.asList();

        //Mockeamos las expectativas => Hacemos las expectativas
        EasyMock.expect(mockNotifyCenter.getServer()).andReturn(mockMailServer);
        EasyMock.expect(mockNotifyCenter.getCurrentDate()).andReturn(fechaActual);
        EasyMock.expect(mockMailServer.findMailItemsWithDate(fechaEnvio)).andReturn(emails);
        // Activamos los mocks
        control.replay();

        //Ejecutamos la SUT Y NO DEBERIA HACER NADA PORQUE NO HAY CORREOS
        assertDoesNotThrow(
                () -> mockNotifyCenter.notifyUsers(fechaActual)
        );
        // Verificamos interacciones que los mocks fueron llamados
        control.verify();
    }

}
