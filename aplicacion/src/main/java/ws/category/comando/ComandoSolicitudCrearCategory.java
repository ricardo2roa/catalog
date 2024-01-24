package ws.category.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudCrearCategory {
    private String name;
    private Boolean locked;
    private Boolean disabled;
}
