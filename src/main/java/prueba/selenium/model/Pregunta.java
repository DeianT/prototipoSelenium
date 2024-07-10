package prueba.selenium.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pregunta {
    private String titulo;
    private String enlace;
    private List<String> contenido;
}
