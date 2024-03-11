package ws.product.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudCrearProducto {
    private final SolicitudProducto producto;
    private final List<MultipartFile> imageReference;
}
