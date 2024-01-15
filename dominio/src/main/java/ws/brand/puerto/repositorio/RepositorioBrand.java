package ws.brand.puerto.repositorio;

import ws.brand.modelo.entidad.Brand;

public interface RepositorioBrand {
    Brand obtenerByCode(int code);
    Brand obtenerName(String name);
    int guardar(Brand brand);

    int calcularCode();
}
