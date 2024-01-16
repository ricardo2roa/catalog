package ws.productos.adaptador.resolver.connection;

import graphql.relay.Edge;
import graphql.relay.PageInfo;

import java.util.List;

public interface Connection<T> {
    List<Edge<T>> getEdges();
    PageInfo getPageInfo();
    int getTotalCount();
}
