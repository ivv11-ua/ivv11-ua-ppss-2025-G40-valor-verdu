package ppss;

public class MatriculaAlumnoTestable extends MatriculaAlumno {
    private Operacion op;

    @Override
    public Operacion getOperacion() {
        return op;
    }

    public void setOp(Operacion op){
        this.op = op;
    }
}
