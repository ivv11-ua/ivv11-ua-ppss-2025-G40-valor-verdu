package ppss.P01;

public class Ejemplo {

    boolean fechaValida(int dia, int mes, int anyo) {
        boolean es_valida = false;
        int valor_maximo_dia=0;

        if (mes >= 1  &&  mes <= 12) {
            switch (mes) {
                case 1: case 3: case 5: case 7: case 10: case 12:
                    valor_maximo_dia = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    valor_maximo_dia = 30;
                    break;
                case 2:
                    if ( (anyo % 4 == 0  &&  anyo % 100 != 0) ||
                            (anyo % 400 == 0)) {  // año bisiesto
                        valor_maximo_dia = 29;
                    }
                    else {
                        valor_maximo_dia = 28;
                    }
                case 8: //para agosto el dia maximo es 31 y faltaba por implementar
                    valor_maximo_dia = 31;
            }
            if (dia >= 1  &&  dia <= valor_maximo_dia) {
                es_valida = true;
            }
        }
        return es_valida;
    }
}
