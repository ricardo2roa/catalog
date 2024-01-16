package ws.productos.adaptador.resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import ws.product.consulta.ManejadorBuscarProductos;
import ws.product.consulta.ManejadorCountProduct;
import ws.product.modelo.dto.ProductDTO;
import ws.productos.adaptador.resolver.connection.Connection;
import ws.productos.adaptador.resolver.connection.CursorUtil;
import ws.productos.adaptador.resolver.connection.CustomConnection;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductResolver implements GraphQLQueryResolver {
    private final ManejadorBuscarProductos manejadorBuscarProductos;
    private final ManejadorCountProduct manejadorCountProduct;
    private final CursorUtil cursorUtil;

    public ProductResolver(ManejadorBuscarProductos manejadorBuscarProductos, ManejadorCountProduct manejadorCountProduct, CursorUtil cursorUtil) {
        this.manejadorBuscarProductos = manejadorBuscarProductos;
        this.manejadorCountProduct = manejadorCountProduct;
        this.cursorUtil = cursorUtil;
    }
    /*public Value findByFullName(String fullName, DataFetchingEnvironment enviroment){
        enviroment.getSelectionSet();
        return new Value("Hola");
    }*/

    public Connection<ProductDTO> allProducts(int first, @Nullable String cursor){
        /*Set<String> requestFields = enviroment.getSelectionSet().getFields().stream()
                .map(SelectedField::getName).collect(Collectors.toUnmodifiableSet());*/
        List<Edge<ProductDTO>> edges = this.getProduct(cursor)
                .stream()
                .map(product -> new DefaultEdge<>(product, cursorUtil.from(product.getId())))
                .limit(first)
                .collect(Collectors.toUnmodifiableList());

        double npage = ((double)this.manejadorCountProduct.ejecutar() / (double)first);
        int totalCount = esEntero(npage) ? (int)npage:((int)npage + 1);
        ConnectionCursor firstCursor = cursorUtil.getFirstCursorFrom(edges);
        ConnectionCursor lastCursor = cursorUtil.getLastCursorFrom(edges);
        DefaultPageInfo pageInfo = new DefaultPageInfo(firstCursor,lastCursor,
                cursor != null, edges.size() >= first);

        return new CustomConnection<>(edges,pageInfo,totalCount);
    }

    private List<ProductDTO> getProduct(String cursor){
        if(cursor == null){
            return this.manejadorBuscarProductos.ejecutar();
        }
        return this.manejadorBuscarProductos.ejecutar(cursorUtil.decodeCursorWith(cursor));
    }

    private static boolean esEntero(double numero) {
        return numero == Math.floor(numero) && !Double.isInfinite(numero);
    }
}
