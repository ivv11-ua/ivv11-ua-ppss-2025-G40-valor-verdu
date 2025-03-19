package ppss;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OperacionStub extends Operacion{
    private ArrayList<String> cursadas;
    private ArrayList<String> no_existen;

    @Override
    public void compruebaMatricula(String dni, String asignatura) throws AsignaturaIncorrectaException, AsignaturaCursadaException {
        if(no_existen.contains(asignatura)){
            throw new AsignaturaIncorrectaException();
        } else if (cursadas.contains(asignatura)) {
            throw new AsignaturaCursadaException();
        }
    }

    public void setAsignatura(ArrayList<String> cursadas, ArrayList<String> no_existen) {
        this.cursadas = cursadas;
        this.no_existen = no_existen;
    }
}
