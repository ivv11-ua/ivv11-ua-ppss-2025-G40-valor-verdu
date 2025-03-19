package ppss.P05;

public class GestorLLamadasTestable extends GestorLlamadas{
    Calendario cal;

    public void setCalendario(Calendario cal) {
        this.cal = cal;
    }

    @Override
    public Calendario getCalendario() {
        return cal;
    }
}
