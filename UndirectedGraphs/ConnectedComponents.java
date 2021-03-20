package UndirectedGraphs;

public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int count;
    //find connected components in G
    public ConnectedComponents(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]){
                dfs(G,v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w : G.adj(v)){
            if (!marked[w]){
                dfs(G,w);
            }
        }
    }

    // are v and w connected?
    public boolean connected(int v,int w){
        return false;
    }
    //number of connected components
    public int count(){
        return count;
    }
    //component identifier for v
    public int id(int v){
        return id[v];
    }
}
