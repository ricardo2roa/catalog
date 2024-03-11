package ws.category.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudUpdateCategory {
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;
}
