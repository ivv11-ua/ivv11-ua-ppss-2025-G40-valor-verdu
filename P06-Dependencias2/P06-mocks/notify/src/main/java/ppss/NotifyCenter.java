package  ppss;

import java.util.List;
import java.time.LocalDate;

public class NotifyCenter {
    private String login = "root";
    private String password = "7l65a43";

    public MailServer getServer() {    //dep1
        return new MailServer(login, password);
    }

    public void sendNotify(String email) throws FailedNotifyException { //dep2
        throw new UnsupportedOperationException("Not supported yet");
    }

    public LocalDate getCurrentDate() { // Factoría local como método =>  dep 3
        return LocalDate.now();
    }

    public void notifyUsers(LocalDate fecha) throws FailedNotifyException {
        int failed = 0;
        MailServer server = getServer(); //dep1    => por aqui entramos
        List<String> emails;

        LocalDate today = getCurrentDate(); //dep3 => cambio importante
        if (today.isEqual(fecha)) {
            emails = server.findMailItemsWithDate(fecha); //dep4
            for (String email : emails) {
                try {
                    sendNotify(email); //dep2
                } catch (FailedNotifyException ex) {
                    failed++;
                }
            }
        } else {
            throw new FailedNotifyException("Date error");
        }
        if (failed > 0) {
            throw new FailedNotifyException("Failures during sending process");
        }
    }
}