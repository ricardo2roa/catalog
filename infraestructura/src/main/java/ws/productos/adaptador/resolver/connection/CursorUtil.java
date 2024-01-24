package ws.productos.adaptador.resolver.connection;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import org.springframework.stereotype.Component;
import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.entidad.Product;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
public class CursorUtil {
    public ConnectionCursor from(String id){
        return new DefaultConnectionCursor(Base64.getEncoder().encodeToString(id.getBytes(StandardCharsets.UTF_8)));
    }

    public String decodeCursorWith(String id){
        return new String(Base64.getDecoder().decode(id));
    }
    public <T> ConnectionCursor getFirstCursorFrom(List<Edge<Product>> edges){
        return (edges.isEmpty() ? null : edges.get(0).getCursor());
    }

    public <T> ConnectionCursor getLastCursorFrom(List<Edge<Product>> edges){
        return (edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor());
    }
}
