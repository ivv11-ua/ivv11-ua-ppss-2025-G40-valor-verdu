 package ppss;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

 /* IMPORTANTE:
     Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
     vamos a usar "throws Esception" en los métodos, para que el código quede más
     legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit
     con un assertDoesNotThrow()
     Es decir, que vamos a primar la legibilidad de los tests.
     Si la SUT puede lanza una excepción, SIEMPRE usaremos assertDoesNotThrow para
     invocar a la sut cuando no esperemos que se lance dicha excepción (independientemente de que hayamos propagado las excepciones provocadas por dbunit).
 */
public class ClienteDAO_IT {
  
  private ClienteDAO clienteDAO; //SUT
  private IDatabaseTester databaseTester;
  private IDatabaseConnection connection;

  @BeforeEach
  public void setUp() throws Exception {

    String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false";
    databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            cadena_conexionDB, "ppss_user", "ppss-2025");
    connection = databaseTester.getConnection();

    clienteDAO = new ClienteDAO();
  }
  @Test
  public void D1_insert_should_add_John_to_cliente_when_John_does_not_exist() throws Exception {
    Cliente cliente = new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
    
     //invocamos a la sut
    Assertions.assertDoesNotThrow(()->clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);

   }

  @Test
  public void D2_delete_should_remove_John_from_cliente_when_John_is_in_table() throws Exception {
    Cliente cliente =  new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    Assertions.assertDoesNotThrow(()->clienteDAO.delete(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");
    
    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }


  //Los que añado yo
  //Apartado C)
  @Test
  public void D3_insert_should_return_exception_SQLException_when_add_one_that_exist() throws Exception {
    Cliente cliente = new Cliente(2,"Will", "Smith");
    cliente.setDireccion("Mi casa");
    cliente.setCiudad("Madrid");

    //Inicializamos el dataSet con los datos iniciales de la tabla cliente
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init_ej1c.xml");
    //Inyectamos el dataset en el objeto databaseTester
    databaseTester.setDataSet(dataSet);
    //inicializamos la base de datos con los contenidos del dataset
    databaseTester.onSetup();

    //invocamos a la sut
    SQLException thrown = Assertions.assertThrows(SQLException.class, () -> {
      clienteDAO.insert(cliente);
    });

    Assertions.assertTrue(thrown.getMessage().contains("Duplicate entry"));

  }

   @Test
   public void D4_delete_should_throw_SQLException_when_client_does_not_exist() throws Exception {
     Cliente cliente = new Cliente(99,"Will", "Smith");
     cliente.setDireccion("Mi casa");
     cliente.setCiudad("Madrid");

     // Carga inicial con dos clientes distintos
     IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init_ej1c.xml");
     databaseTester.setDataSet(dataSet);
     databaseTester.onSetup();

     // Intentamos eliminar un cliente que NO existe (por ejemplo, id=99)
     SQLException thrown = Assertions.assertThrows(SQLException.class, () ->
       clienteDAO.delete(cliente)// ID inexistente
     );

     Assertions.assertTrue(thrown.getMessage().contains("Delete failed"));
   }

   @Test
   public void D5_update() throws Exception {
     Cliente cliente = new Cliente(1,"John", "Smith");
     cliente.setDireccion("Madrid");
     cliente.setCiudad("Madrid");

     // Carga inicial con un cliente
     IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init_ej1d.xml");
     databaseTester.setDataSet(dataSet);
     databaseTester.onSetup();

     // Invocamos a la sut
     Assertions.assertDoesNotThrow(()->clienteDAO.update(cliente));

     //recuperamos los datos de la BD después de invocar a la sut
     IDataSet databaseDataSet = connection.createDataSet();
     ITable actualTable = databaseDataSet.getTable("cliente");

     //creamos el dataset con el resultado esperado
     IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado_ej1d.xml");
     ITable expectedTable = expectedDataSet.getTable("cliente");
     Assertion.assertEquals(expectedTable, actualTable);
     //CUIDADO CON PONER ASSERTIONS QUE DA ERROR GARRAFAL BRO!!!
   }

   @Test
   public void D6_retrievew() throws Exception {

     Cliente cliente = new Cliente(1, "John", "Smith");
     cliente.setDireccion("1 Main Street");
     cliente.setCiudad("Anycity");

     // Inicializamos la BD
     // Inicializamos el dataSet con los datos iniciales de la tabla cliente
     IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init_ej1d.xml");
     // Inyectamos el dataset en el objeto databaseTester
     databaseTester.setDataSet(dataSet);
     // Inicializamos la base de datos con los contenidos del dataset
     databaseTester.onSetup();

     //INVOCAR A LA SUT
     Cliente clienteReal = Assertions.assertDoesNotThrow(
                          () -> clienteDAO.retrieve(1));
     // comprobar cliente
     Assertions.assertAll(
             () -> Assertions.assertEquals(cliente.getId(), clienteReal.getId()),
             () -> Assertions.assertEquals(cliente.getNombre(), clienteReal.getNombre()),
             () -> Assertions.assertEquals(cliente.getApellido(), clienteReal.getApellido()),
             () -> Assertions.assertEquals(cliente.getDireccion(), clienteReal.getDireccion()),
             () -> Assertions.assertEquals(cliente.getCiudad(), clienteReal.getCiudad())
     );

     // recuperamos los datos de la BD después de invocar al SUT
     IDataSet databaseDataSet = connection.createDataSet();
     // recuperamos los datos de la tabla cliente
     ITable actualTable = databaseDataSet.getTable("cliente");

     // creamos el dataset con el resultado esperado
     IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-init_ej1d.xml");
     ITable expectedTable = expectedDataSet.getTable("cliente");

     // comparamos tablas y generamos informe --> Assertion = dbunit
     Assertion.assertEquals(expectedTable, actualTable);
   }
 }

