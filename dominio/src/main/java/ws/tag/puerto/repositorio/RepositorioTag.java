package ws.tag.puerto.repositorio;

import ws.tag.modelo.entidad.Tag;

public interface RepositorioTag {
    int guardar(Tag tag);

    int calcularCode();
}
