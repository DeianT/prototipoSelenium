package prueba.selenium.service;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockService {
    final String URL = "https://web.archive.org/web/20190104110157/http://shares.telegraph.co.uk/indices/?index=MCX";
    private final ChromeDriver driver;
    
    public void scrapeStocks(){
        driver.get(URL);
        
        for(WebElement row : driver.findElements(By.cssSelector("table.tablesorter.full tbody tr"))){

            if(row.findElement(By.tagName("td")) != null){
                final String ticker = row.findElement(By.tagName("td")).getText();
                
                final String name = row.findElement(By.tagName("td")).findElement(By.xpath("following-sibling::*[1]")).getText();
                
                String tempPrice = row.findElement(By.cssSelector("td.right")).getText();
                tempPrice = tempPrice.replace(",", "");
                final double price = Double.parseDouble(tempPrice);
                    
                System.out.println(ticker + " " + name + " " + price);
            }
        }
    }
}
