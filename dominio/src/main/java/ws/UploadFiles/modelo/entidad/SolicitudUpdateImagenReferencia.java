package ws.UploadFiles.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import ws.reference.modelo.dto.ReferenceDTO;

@Getter
@AllArgsConstructor
public class SolicitudUpdateImagenReferencia {
    private final ReferenceDTO referencia;
    private final MultipartFile file;
}
