package ppss.P05;

public class GestorLlamadas {
    private static final double TARIFA_NOCTURNA=12.2;
    private static final double TARIFA_DIURNA=20.7;
    public Calendario getCalendario() {
        Calendario c = new Calendario();
        return c;
    }

    public double calculaConsumo(int minutos) {
        Calendario c = getCalendario();
        int hora = c.getHoraActual();
        if(hora < 8 || hora > 20) {
            return minutos * TARIFA_NOCTURNA;
        } else {
            return minutos * TARIFA_DIURNA;
        }
    }
}

