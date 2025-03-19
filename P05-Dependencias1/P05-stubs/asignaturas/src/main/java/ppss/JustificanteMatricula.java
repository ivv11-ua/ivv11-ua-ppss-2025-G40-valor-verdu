package ppss;

import java.util.ArrayList;

public class JustificanteMatricula {
    private String dni;
    private ArrayList<String> asignaturas;
    private ArrayList<String> errores;
    //getters y setters

    public void setDni(String dni){
        this.dni = dni;
    }
    public void setAsignaturas(ArrayList<String> asig){
        asignaturas = asig;
    }
    public void setErrores(ArrayList<String> errores){
        this.errores = errores;
    }
    public String getDni() {
        return dni;
    }
    public ArrayList<String> getAsignaturas() {
        return asignaturas;
    }
    public ArrayList<String> getErrores() {
        return errores;
    }
}
