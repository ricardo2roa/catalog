package ws.productos.adaptador.resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import ws.product.consulta.ManejadorBuscarProducto;
import ws.product.consulta.ManejadorBuscarProductos;
import ws.product.consulta.ManejadorCountProduct;
import ws.product.modelo.entidad.Product;
import ws.productos.adaptador.resolver.connection.Connection;
import ws.productos.adaptador.resolver.connection.CursorUtil;
import ws.productos.adaptador.resolver.connection.CustomConnection;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductResolver implements GraphQLQueryResolver {
    private final ManejadorBuscarProductos manejadorBuscarProductos;

    private final ManejadorBuscarProducto manejadorBuscarProducto;
    private final ManejadorCountProduct manejadorCountProduct;
    private final CursorUtil cursorUtil;

    public ProductResolver(ManejadorBuscarProductos manejadorBuscarProductos, ManejadorBuscarProducto manejadorBuscarProducto, ManejadorCountProduct manejadorCountProduct, CursorUtil cursorUtil) {
        this.manejadorBuscarProductos = manejadorBuscarProductos;
        this.manejadorBuscarProducto = manejadorBuscarProducto;
        this.manejadorCountProduct = manejadorCountProduct;
        this.cursorUtil = cursorUtil;
    }
    /*public Value findByFullName(String fullName, DataFetchingEnvironment enviroment){
        enviroment.getSelectionSet();
        return new Value("Hola");
    }*/

    public Connection<Product> allProducts(int first, @Nullable String cursor, DataFetchingEnvironment enviroment){
        //GraphQLContext context =  enviroment.getGraphQlContext();
        /*Set<String> requestFields = enviroment.getSelectionSet().getFields().stream()
                .map(SelectedField::getName).collect(Collectors.toUnmodifiableSet());*/
        List<Edge<Product>> edges = this.getProduct(cursor)
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

    public Product getProductById(String id){
        return this.manejadorBuscarProducto.ejecutar(id);
    }

    private List<Product> getProduct(String cursor){
        if(cursor == null){
            return this.manejadorBuscarProductos.ejecutar();
        }
        return this.manejadorBuscarProductos.ejecutar(cursorUtil.decodeCursorWith(cursor));
    }

    private static boolean esEntero(double numero) {
        return numero == Math.floor(numero) && !Double.isInfinite(numero);
    }
}
