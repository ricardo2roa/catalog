package ws.product.comando;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ws.product.modelo.entidad.SolicitudReferencia;

import java.util.List;

@Getter
@NoArgsConstructor
public class ComandoReferencia {
    private long peso;
    private long precio;
    private int stock;
    private String codeImg;

    public static List<SolicitudReferencia> convertir(List<ComandoReferencia> referencias){
        return referencias.stream().map(referencia->new SolicitudReferencia(referencia.getPeso(),
                referencia.getPrecio(),referencia.getStock(),referencia.getCodeImg())).toList();
    }
}
