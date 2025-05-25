package ejercicio3.conPOyPFact;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;


public class Cookies {
    static String urlInicial= "http://demo.magento.recolize.com/";

    //guarda las cookies en un fichero en el directorio target
    public static void storeCookiesToFile(String login, String password, String nombre_fichero) {
        WebDriver driver;
		
        ChromeOptions co = new ChromeOptions();
        co.setBrowserVersion("131");
        co.setExperimentalOption("excludeSwitches",
                Arrays.asList("enable-automation"));

        driver=new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(urlInicial);

        //accedemos a Account->Log In
        driver.findElement(By.linkText("ACCOUNT")).click();
        driver.findElement(By.linkText("Log In")).click();

        //introducimos login y password
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).submit();

        //creamos el fichero donde guardaremos las cookies generadas
        File file = new File("./target/"+nombre_fichero);
        try {
            // borramos el fichero si ya existe
            file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter bwrite = new BufferedWriter(fileWrite);

            // bucle para obtener las cookies
            for(Cookie ck : driver.manage().getCookies()) {
                bwrite.write((ck.getName()+";"+ck.getValue()+";"
                        +ck.getDomain()+";"+ck.getPath()+";"
                        +ck.getExpiry()+";"+ck.isSecure()));
                bwrite.newLine();
            }
            bwrite.flush();
            bwrite.close();
            fileWrite.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        driver.close();
      }

    //lee el fichero de cookies generado y guarda las cookies en el navegador
    public static void loadCookiesFromFile(WebDriver driver){

        //necesitamos acceder a la página principal para fijar el dominio
        driver.get(urlInicial);
        //borramos todas ls cookies de este dominio
        driver.manage().deleteAllCookies();

        //leemos los datos del fichero y los "guardamos" en el navegador
        try{
            File file = new File("./target/cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String strline;
            while((strline=Buffreader.readLine())!=null){
                StringTokenizer token = new StringTokenizer(strline,";");
                while(token.hasMoreTokens()){
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    String val;
                    if(!(val=token.nextToken()).equals("null")) {
                        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                        expiry = formatter.parse(val);
                    }
                    Boolean isSecure = Boolean.valueOf(token.nextToken());
                    //Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
                    //Cremos la cookie con los datos leidos del fichero
                    Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);
                    driver.manage().addCookie(ck); // This will add the stored cookie to your current session
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //muestra los valores de las cookies almacenadas en el navegador
    public static void printCookies(WebDriver driver){
        System.out.println("**************** ");
        System.out.println("Datos de las cookies:");
        int i= 0;
        for(Cookie ck : driver.manage().getCookies()) {
            i++;
            System.out.println("Cookie  número "+i);
            System.out.println("   nombre: "+ ck.getName());
            System.out.println("   valor: "+ ck.getValue());
            System.out.println("   dominio: "+ ck.getDomain());
            System.out.println("   path: "+ ck.getPath());
            System.out.println("   expira: "+ ck.getExpiry());
            System.out.println("   segura: "+ ck.isSecure());
        }
        System.out.println("****************");
    }
}
