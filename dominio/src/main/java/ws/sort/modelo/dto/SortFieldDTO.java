package ws.sort.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SortFieldDTO {
    private String field;
    private int order;
}
