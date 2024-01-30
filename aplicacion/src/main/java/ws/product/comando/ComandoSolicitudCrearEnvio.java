package ws.product.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Getter
@AllArgsConstructor
public class ComandoSolicitudCrearEnvio {
    private ComandoProducto producto;
    private List<MultipartFile> multipartFiles;
}
