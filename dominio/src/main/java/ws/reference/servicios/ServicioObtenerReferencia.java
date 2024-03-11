package ws.reference.servicios;

import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;

public class ServicioObtenerReferencia {
    private final RepositorioReference repositorioReference;

    public ServicioObtenerReferencia(RepositorioReference repositorioReference){
        this.repositorioReference = repositorioReference;
    }

    public Reference ejecutar(String id){
        return this.repositorioReference.buscarPorId(id);
    }
}
