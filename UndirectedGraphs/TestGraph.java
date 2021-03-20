package UndirectedGraphs;



import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestGraph {
    public static void main(String[] args) throws FileNotFoundException {
        Graph G = new Graph("src\\UndirectedGraphs\\tinyG.txt");
        Scanner sc = new Scanner(System.in);
        Paths paths = new BreadthFirstPaths(G,0);
        for (int v = 0; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                System.out.print("Path from 0 to " + v + ": ");
                for(int w: paths.pathTo(v)){
                    System.out.print(w + " ");
                }
                System.out.println();
            } else {
                System.out.println("Path from 0 to " + v + " doesn't exists");
            }

        }


    }
    //compute the degree of the vertice v
    public static int degree(Graph G,int v){
        int degree = 0;
        for(int w : G.adj(v)){
            degree++;
        }
        return degree;
    }
    //compute maximum degree
    public static int maxDegree(Graph G){
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (max < degree(G,v)){
                max = degree(G,v);
            }
        }
        return max;
    }
    //compute average degree
    public static double averageDegree(Graph G){
        return 2.0 * G.E() / G.V();
    }
    //count self-loops
    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for(int w : G.adj(v)){
                if (v == w) count++;
            }
        }
        return count/2;
    }
}