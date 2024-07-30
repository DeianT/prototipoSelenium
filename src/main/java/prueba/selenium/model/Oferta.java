package prueba.selenium.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Oferta {
    private String titulo;
    private String enlace;
    private String precioOriginal;
    private String precioOferta;
    private String descuento;
}
