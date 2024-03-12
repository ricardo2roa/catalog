package ws.brand.puerto.repositorio;

import ws.brand.modelo.dto.BrandRead;
import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.DateFilter;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepositorioBrand {
    Brand obtenerByCode(int code);
    Brand obtenerName(String name);
    int guardar(Brand brand);
    //Map<Integer, BrandRead> obtenerListByCodes(Set<Integer> brands);
    Map<Integer, Brand> obtenerListValrep();
    List<Brand> obtenerTodasLasMarcas(int page, String searchText,List<String> nameFilters, List<String> lockedFilters,
                                      List<String> disabledFilters, List<DateFilter> dateFilters);
    int calcularCode();
    void updateBrand(SolicitudUpdateBrand brand, String id);
}
