package ppss;

import java.util.ArrayList;

public class MatriculaAlumno {
    protected Operacion getOperacion() {
        return new Operacion();
    }

    public JustificanteMatricula validaAsignaturas(String dni, String[] asignaturas) {
        JustificanteMatricula justificante = new JustificanteMatricula();
        ArrayList<String> validas = new ArrayList<>();
        ArrayList<String> listaErrores = new ArrayList<>();
        Operacion op = getOperacion();
        for (String asignatura : asignaturas) {
            try {
                op.compruebaMatricula(dni, asignatura);
                validas.add(asignatura);
            } catch (AsignaturaIncorrectaException ex) {
                listaErrores.add("Asignatura " + asignatura + " no existe");
            } catch (AsignaturaCursadaException ex) {
                listaErrores.add("Asignatura " + asignatura + " ya cursada");
            }
        }
        justificante.setDni(dni);
        justificante.setAsignaturas(validas);
        justificante.setErrores(listaErrores);

        return justificante;
    }
}
