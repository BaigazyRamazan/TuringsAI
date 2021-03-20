import javafx.scene.layout.StackPane;

import java.util.*;

class GFG
{
    private int ROW ;
    private int COL ;

    public GFG(int r, int c) {
        this.ROW = r;
        this.COL = c;
    }

    // To store matrix cell cordinates
    public class Point
    {
        int x;
        int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    };

    // A Data Structure for queue used in BFS
    public class queueNode
    {
        Point pt; // The cordinates of a cell
        int dist; // cell's distance of from the source

        public queueNode(Point pt, int dist)
        {
            this.pt = pt;
            this.dist = dist;
        }
    };

    // check whether given cell (row, col)
    // is a valid cell or not.
    public boolean isValid(int row, int col)
    {
        // return true if row number and
        // column number is in range
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL);
    }

    // These arrays are used to get row and column
// numbers of 4 neighbours of a given cell
    static int rowNum[] = {-1, 0, 0, 1};
    static int colNum[] = {0, -1, 1, 0};

    // function to find the shortest path between
// a given source cell to a destination cell.
    public Queue<queueNode> BFS(int mat[][], Point src,
                   Point dest)
    {
        // check source and destination cell
        // of the matrix have value 1
        if (mat[src.x][src.y] == 1 ||
                mat[dest.x][dest.y] == 1)
            return null;

        boolean [][]visited = new boolean[ROW][COL];

        // Mark the source cell as visited
        visited[src.x][src.y] = true;

        // Create a queue for BFS
        Queue<queueNode> q = new LinkedList<>();
        Queue<queueNode> q1 = new LinkedList<>();

        // Distance of source cell is 0
        queueNode s = new queueNode(src, 0);
        q.add(s); // Enqueue source cell
        q1.add(s); // Enqueue source cell

        // Do a BFS starting from source cell
        while (!q.isEmpty())
        {
            queueNode curr = q.peek();
            Point pt = curr.pt;

            // If we have reached the destination cell,
            // we are done
            if (pt.x == dest.x && pt.y == dest.y)
                return q1;

            // Otherwise dequeue the front cell
            // in the queue and enqueue
            // its adjacent cells
            q.remove();

            for (int i = 0; i < 4; i++)
            {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                // if adjacent cell is valid, has path
                // and not visited yet, enqueue it.
                if (isValid(row, col) &&
                        mat[row][col] != 1 &&
                        !visited[row][col])
                {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode(new Point(row, col),
                            curr.dist + 1 );
                    q.add(Adjcell);
                    q1.add(Adjcell);
                    if(Adjcell.pt.x == dest.x && Adjcell.pt.y == dest.y) return q1;
                }
            }
        }


        // Return -1 if destination cannot be reached
        return null;
    }

    // Driver Code
    public Stack<Position> getRoad(Map m , Position position, Position fp) {
        int mat[][] =  new int[m.getSize()][m.getSize()];
        for (int i = 0; i <m.getSize() ; i++) {
            for (int j = 0; j <m.getSize() ; j++) {
                mat[i][j] = m.getMap()[j][i];
            }
        }
        Point source = new Point(position.getX(), position.getY());
        Point dest = new Point(fp.getX(), fp.getY());
        Queue<queueNode> q = BFS(mat, source, dest);

        for (queueNode queueNode1: q) {
            System.out.println(queueNode1.pt.x + " " + queueNode1.pt.y + " " + queueNode1.dist);
        }

        Collections.reverse((List<queueNode>)q);
        Queue<queueNode> q1 = new LinkedList<>();
        q1.offer(q.peek());

        queueNode que = q.poll();
        Point pt = que.pt;
        boolean is = false;
        while(!q.isEmpty()){
            for (int i = 0; i <4 ; i++) {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                if(q.peek().pt.x == row && q.peek().pt.y == col) {
                    q1.offer(q.peek());
                    que = q.poll();
                    pt = que.pt;
                    is = true;
                    break;
                }
            }
            if(!is) q.poll();
            is = false;
        }


        Stack<Position> stack1 = new Stack<>();
        while(!q1.isEmpty()){
            stack1.push(new Position(q1.peek().pt.x, q1.poll().pt.y));
        }


        return stack1;
    }
}