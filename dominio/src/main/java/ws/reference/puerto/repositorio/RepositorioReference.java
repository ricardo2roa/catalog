package ws.reference.puerto.repositorio;

import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;

import java.util.List;

public interface RepositorioReference {
    Reference buscarPorId(String id);

    String guardar(ReferenceDTO reference);

    String obtenerInfoParaSKUByIdReferencia(Reference reference);
}
