package ws.imagen.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import ws.reference.modelo.dto.ReferenceDTO;

@Getter
@AllArgsConstructor
public class ComandoSolicitudUpdateImagen {
    private ReferenceDTO referencia;
    private MultipartFile file;
}
