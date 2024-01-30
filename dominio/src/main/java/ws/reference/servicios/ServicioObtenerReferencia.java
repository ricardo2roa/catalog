package ws.reference.servicios;

import ws.category.modelo.dto.CategoryDTO;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;
import java.util.Map;
import java.util.Set;

public class ServicioObtenerReferencia {
    private final RepositorioReference repositorioReference;

    public ServicioObtenerReferencia(RepositorioReference repositorioReference){
        this.repositorioReference = repositorioReference;
    }

    public Reference ejecutar(String id){
        return this.repositorioReference.buscarPorId(id);
    }
}
