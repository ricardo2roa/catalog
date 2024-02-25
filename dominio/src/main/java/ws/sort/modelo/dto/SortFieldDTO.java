package ws.sort.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SortFieldDTO {
    private String field;
    private int order;
}
