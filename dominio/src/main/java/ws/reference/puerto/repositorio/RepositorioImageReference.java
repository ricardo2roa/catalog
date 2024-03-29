package ws.reference.puerto.repositorio;

import org.springframework.core.io.Resource;
import ws.reference.modelo.dto.ImageReferenceInfoDTO;
import ws.reference.modelo.entidad.ImageReferenceInfo;

public interface RepositorioImageReference {
    String registrarImagen(ImageReferenceInfo imageReferenceInfo);
    String obtenerNameByIdReference(String id);
    ImageReferenceInfo obtenerImagenInfoByIdReference(String id);
}
