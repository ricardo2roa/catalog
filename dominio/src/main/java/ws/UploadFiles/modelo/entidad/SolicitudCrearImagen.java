package ws.UploadFiles.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class SolicitudCrearImagen {
    private final MultipartFile file;
    private final String name;
}
