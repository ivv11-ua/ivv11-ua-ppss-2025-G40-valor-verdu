package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ppss.matriculacion.to.AlumnoTO;

import java.time.LocalDate;
import java.time.Month;

/**
 * Bottonm-up, los componenentes con menor abstracción (menos dep. externas) y luego
 * los que mayor tengan.
 * Reactor Summary for matriculacion 1.0-SNAPSHOT:
 * [INFO]
 * [INFO] matriculacion ...................................... SUCCESS [  0.300 s]
 * [INFO] matriculacion-comun ................................ SUCCESS [  0.542 s]
 * [INFO] matriculacion-dao .................................. SUCCESS [  1.885 s]
 * [INFO] matriculacion-proxy ................................ SUCCESS [  1.111 s]
 * [INFO] matriculacion-bo ................................... SUCCESS [  0.944 s]
 */
@Tag("Integracion-Fase1")
public class AlumnoDAOIT {
    private MiJdbcDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @BeforeEach
    public void setUp() throws Exception {
        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new MiJdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB, "ppss_user", "ppss-2025");
        //obtenemos la conexión con la BD
        connection = databaseTester.getConnection();
    }

    @Test
    public void testA1() throws Exception {

        // datos de entrada
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        alumno.setFechaNacimiento(LocalDate.of(1985, Month.FEBRUARY, 22));

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla alumnos
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    //Intento meter un usuario que ya existe
    @Test
    public void testA2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        alumno.setFechaNacimiento(LocalDate.of(1982, Month.APRIL, 22));

        //cargamos los datos de inicio
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException exception = Assertions.assertThrows(DAOException.class,
                ()-> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        //Comparamos resultado
        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    public void testA3() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setNombre(null);
        alumno.setFechaNacimiento(LocalDate.of(1982, Month.FEBRUARY, 22));

        //cargamos los datos iniciales
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        //Comparamos resultados
        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());
    }


    @Test
    public void testA4() throws Exception {

        // datos de entrada
        AlumnoTO alumno = null;

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        // informe
        Assertions.assertEquals("Alumno nulo", exception.getMessage());
    }
    @Test
    public void testA5() throws Exception {

        // datos de entrada
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        alumno.setFechaNacimiento(LocalDate.of(1982, Month.FEBRUARY, 22));

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        // informe
        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    public void testB1() throws Exception {

        // datos de entrada
        String nifBorrar = "11111111A";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().delAlumno(nifBorrar));

        //cargamos la tabla despues de invocar la sut
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //cargamos la tabla con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        // informe
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception {

        // Preaparar datos de entrada
        String nifBorrar = "33333333C";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a la sut
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().delAlumno(nifBorrar));

        //Comparamos resultado
        Assertions.assertEquals("No se ha borrado ningun alumno", exception.getMessage());
    }
}
