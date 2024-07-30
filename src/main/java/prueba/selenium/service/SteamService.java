package prueba.selenium.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import prueba.selenium.model.Oferta;

@Service @AllArgsConstructor
public class SteamService {
    final String url = "https://store.steampowered.com/";
    private final ChromeDriver driver;
    private final ChromeDriver driver1;
    
    public void print(){
        driver.get(url);
        
        WebElement carousel = driver.findElement(By.cssSelector("div#spotlight_carousel"));
        List<WebElement> items = carousel.findElements(By.cssSelector("div.home_area_spotlight"));
        items.addAll(carousel.findElements(By.cssSelector("a.store_capsule")));
        
        items.forEach( e -> System.out.println(e.getText() + " " + e.getAttribute("href")));
    }
    
    public List<Oferta> obtenerOfertas(){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        
        driver.get(url);
        
        WebElement carousel = driver.findElement(By.cssSelector("div#spotlight_carousel"));
        List<WebElement> items = carousel.findElements(By.cssSelector("div.home_area_spotlight"));
        items.addAll(carousel.findElements(By.cssSelector("div.specials_target")));
        
        items.forEach( e -> {
            Oferta o = new Oferta();
            
            o.setEnlace(e.findElement(By.tagName("a")).getAttribute("href"));
            o.setDescuento(e.findElement(By.cssSelector("div.discount_pct")).getText());
            o.setTitulo(obtenerTitulo(o.getEnlace()));
            
            //chequear que no sea null
            if(!e.findElements(By.cssSelector("div.discount_original_price")).isEmpty()){
                o.setPrecioOriginal(e.findElement(By.cssSelector("div.discount_original_price")).getText());
            }
            if(!e.findElements(By.cssSelector("div.discount_final_price")).isEmpty()){
                o.setPrecioOferta(e.findElement(By.cssSelector("div.discount_final_price")).getText());
            }
            
            ofertas.add(o);
        });
            
        return ofertas;
    }
    
    private String obtenerTitulo(String url){
        driver1.get(url);
        if(!driver1.findElements(By.cssSelector("div#appHubAppName")).isEmpty()){
            return driver1.findElement(By.cssSelector("div#appHubAppName")).getText();
        }
        return "";
    }
}
