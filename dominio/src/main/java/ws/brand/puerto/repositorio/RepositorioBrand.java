package ws.brand.puerto.repositorio;

import ws.brand.modelo.dto.BrandDTO;
import ws.brand.modelo.entidad.Brand;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepositorioBrand {
    Brand obtenerByCode(int code);
    Brand obtenerName(String name);
    int guardar(Brand brand);
    Map<Integer, BrandDTO> obtenerListByCodes(Set<Integer> brands);
    int calcularCode();
}
