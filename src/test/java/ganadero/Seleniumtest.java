package ganadero;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Seleniumtest {
    WebDriver driver;
    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mercadolibre.com/");
    }
    @AfterEach
    public void cleanup(){
        driver.quit();
    }
    @Test
    public void verifyCRUDProject() throws InterruptedException {
        // SELECCION DE PAIS BOLIVIA
        driver.findElement(By.id("BO")).click();
        // ACEPTAR COOKIES
        driver.findElement(By.xpath("//button[@class=\"cookie-consent-banner-opt-out__action cookie-consent-banner-opt-out__action--primary cookie-consent-banner-opt-out__action--key-accept\"]")).click();
        //LOCALIZA CAJA DE TEXTO E INGRESA PRODUCTO
        driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("CPU");

        //VERIFICACION DE ASSERTION CON PRODUCTO USADO MENOR A 3
        //driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("tablet");

        //CLICK EN EL BOTON DE BUSQUEDA
        driver.findElement(By.xpath("//div[@role=\"img\"]")).click();

        // TIEMPO DE RESPUESTA PARA LA BUSQUEDA
        Thread.sleep(3000);

        //FLTRA EL PRODUCTO POR "USADO"
        driver.findElement(By.xpath("//a/span[text()=\"Usado\"]")).click();

        // CONTADOR DE LOS RESULTADOS
        int elements=driver.findElements(By.xpath("//div[@role=\"presentation\"]")).size();

        //VERIFICACION QUE LOS RESULTADOS SEAN >=3 PARA PASAR LA PRUEBA
        Assertions.assertTrue(elements >= 3
                ,"ERROR Los productos encontrados son menores a 3");


    }

}
