package ws.productos.adaptador.resolver.connection;

import graphql.relay.Edge;
import com.google.common.collect.ImmutableList;
import graphql.relay.PageInfo;

import java.util.List;
import java.util.Objects;

import static graphql.Assert.assertNotNull;

public class CustomConnection<T> implements Connection<T>{
    private final ImmutableList<Edge<T>> edges;
    private final PageInfo pageInfo;
    private final int totalCount;

    public CustomConnection(List<Edge<T>> edges, PageInfo pageInfo, int totalCount) {
        this.edges = ImmutableList.copyOf(assertNotNull(edges, () -> "edges cannot be null"));
        this.pageInfo = assertNotNull(pageInfo, () -> "page info cannot be null");
        this.totalCount = assertNotNull(totalCount,()-> "total count cannot be null");
    }
    @Override
    public List<Edge<T>> getEdges() {
        return edges;
    }
    @Override
    public PageInfo getPageInfo() {
        return pageInfo;
    }
    @Override
    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomConnection that = (CustomConnection) o;
        return Objects.equals(edges, that.edges) && Objects.equals(pageInfo, that.pageInfo) && Objects.equals(totalCount, that.totalCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edges, pageInfo, totalCount);
    }

    @Override
    public String toString() {
        return "CustomConnection{" +
                "edges=" + edges +
                ", pageInfo=" + pageInfo +
                ", totalCount=" + totalCount +
                '}';
    }
}
