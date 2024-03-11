package ws.brand.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class DateFilter {
    private Date value;
    private String option;
}
