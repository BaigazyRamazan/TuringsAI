package UndirectedGraphs;

import StacksAndQueues.MyQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths extends Paths{
    private boolean[] marked;
    private int[] edgeTo;
//    private int[] distTo;
    private int s;

    public BreadthFirstPaths(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
//        distTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G, int s) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(s);
        marked[s] = true;
//        distTo[s] = 0;
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : G.adj(v)) {
                if(!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
//                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Integer> path = new LinkedList<>();
        for (int x = v;x != s; x = edgeTo[x]){
            path.addFirst(x);
        }
        path.addFirst(s);
        return path;
    }
}
