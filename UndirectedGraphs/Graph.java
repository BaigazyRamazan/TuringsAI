package UndirectedGraphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    private int V; // number of vertices
    private int E = 0; //number of edges
    private LinkedList<Integer>[] adj;
    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public Graph(String arg) throws FileNotFoundException {
        File file = new File(arg);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            V = sc.nextInt();
            adj = new LinkedList[V];

            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
            int e = sc.nextInt();
            for (int i = 0; i < e; i++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                addEdge(v, w);
            }
        }
    }

    public Graph() {
    }

    public void addEdge(int v, int w){
        adj[v].addFirst(w);
        adj[w].addFirst(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < V; i++) {
            for(int j: adj(i)){
                str += i + " " + j + "\n";
            }
        }
        return "";
    }
}



