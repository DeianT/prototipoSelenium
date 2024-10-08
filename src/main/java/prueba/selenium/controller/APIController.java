package prueba.selenium.controller;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import prueba.selenium.model.Oferta;
import prueba.selenium.model.Pregunta;
import prueba.selenium.service.StackService;
import prueba.selenium.service.ScraperService;
import prueba.selenium.service.SteamService;
import prueba.selenium.service.StockService;

@CrossOrigin(origins = {"http://127.0.0.1:5500/"})
@RestController
public class APIController {
    @Autowired
    private ScraperService service;
    
    @Autowired
    private StackService stackService;
    
    @Autowired
    private StockService stockService;
    
    @Autowired
    private SteamService steamService;
    
    @GetMapping("/lista/{palabra}")
    public List<String> obtenerListaPalabras(@PathVariable String palabra){
        List<WebElement> list = service.scrapeLista(palabra);
        List<String> words = new ArrayList<>();
        for(WebElement word: list){
            words.add(word.getText());
        }
        return words;
    }
    
    @GetMapping("/palabras/{palabra}")
    @ResponseBody
    public String obtenerPalabras(@PathVariable String palabra){
        return service.scrapeString(palabra);
    }
    
    @GetMapping("/stack")
    @ResponseBody
    public String stack(){
        return stackService.scrapeTitulo();
    }
    
    @GetMapping("/search/{palabra}")
    @ResponseBody
    public List<String> search(@PathVariable String palabra){
        List<WebElement> list = stackService.search(palabra);
        List<String> titles = new ArrayList<>();
        for(WebElement word: list){
            titles.add(word.getText());
            titles.add(word.getAttribute("href"));
        }
        return titles;
    }
    
    @GetMapping("/preguntas/{palabra}")
    public List<Pregunta> obtenerPreguntas(@PathVariable String palabra){
        return stackService.preguntas(palabra);
    }
    
    @GetMapping("/stocks")
    public void printStocks(){
        stockService.scrapeStocks();
    }
    
    @GetMapping("/steam")
    public List<Oferta> obtenerOfertas(){
        return steamService.obtenerOfertas();
    }
}
