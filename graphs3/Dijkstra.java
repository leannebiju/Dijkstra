package graphs3;

import java.util.Arrays;

public class Dijkstra 
{
    int[][] adjMatrix;
    final String[] vertexData;
    final int size;

    public Dijkstra(int size) 
    {
        this.size = size;
        adjMatrix = new int[size][size];
        vertexData = new String[size];
        Arrays.fill(vertexData, "");
    }

    public void addEdge(int u, int v, int weight) 
    {
        if (u >= 0 && u < size && v >= 0 && v < size) 
        {
            adjMatrix[u][v] = weight;
            adjMatrix[v][u] = weight; 
        }
    }

    public void addVertexData(int vertex, String data) 
    {
        if (vertex >= 0 && vertex < size) 
        {
            vertexData[vertex] = data;
        }
    }

    public Result dijkstra(String startVertexData) 
    {
        int startVertex = -1;
        for (int i = 0; i < size; i++) 
        {
            if (vertexData[i].equals(startVertexData)) 
            {
                startVertex = i;
                break;
            }
        }

        if (startVertex == -1) 
        {
            throw new IllegalArgumentException("Start vertex '" + startVertexData + "' not found");
        }

        int[] distances = new int[size];
        int[] predecessors = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[startVertex] = 0;

        for (int i = 0; i < size; i++) 
        {
            int u = -1;
            for (int j = 0; j < size; j++) 
            {
                if (!visited[j] && (u == -1 || distances[j] < distances[u])) 
                {
                    u = j;
                }
            }

            if (distances[u] == Integer.MAX_VALUE) 
            {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < size; v++) 
            {
                if (adjMatrix[u][v] != 0 && !visited[v]) 
                {
                    int alt = distances[u] + adjMatrix[u][v];
                    if (alt < distances[v]) 
                    {
                        distances[v] = alt;
                        predecessors[v] = u;
                    }
                }
            }
        }

        return new Result(distances, predecessors);
    }

    public LinkedList getPath(int[] predecessors, int startVertex, int endVertex) 
    {
        LinkedList path = new LinkedList();
        for (int at = endVertex; at != -1; at = predecessors[at]) 
        {
            path.addFirst(at);
        }

        if (path.getFirst() != startVertex) 
        {
            return null;
        }

        return path;
    }
}
