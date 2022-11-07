/******************************************************************************
 *  Compilation:  javac NonrecursiveDFS.java
 *  Execution:    java NonrecursiveDFS graph.txt s
 *  Dependencies: Graph.java Queue.java Stack.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyCG.txt
 *                https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *
 *  Run nonrecurisve depth-first search on an undirected graph.
 *  Runs in O(E + V) time using O(V) extra space.
 *
 *  Explores the vertices in exactly the same order as DepthFirstSearch.java.
 *
 *  %  java Graph tinyG.txt
 *  13 vertices, 13 edges 
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  % java NonrecursiveDFS tinyG.txt 0
 *  0 1 2 3 4 5 6 
 *
 * % java NonrecursiveDFS tinyG.txt 9
 * 9 10 11 12 
 *
 ******************************************************************************/

import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;

/**
 *  The {@code NonrecursiveDFS} class represents a data type for finding
 *  the vertices connected to a source vertex <em>s</em> in the undirected
 *  graph.
 *  <p>
 *  This implementation uses a nonrecursive version of depth-first search
 *  with an explicit stack.
 *  See {@link DepthFirstSearch} for the classic recursive version.
 *  The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the worst
 *  case, where <em>V</em> is the number of vertices and <em>E</em> is the
 *  number of edges.
 *  The {@link #marked(int)} instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the graph). 
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DFS {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] color; 
    /**
     * Computes the vertices connected to the source vertex {@code s} in the graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DFS(Graphi G, int s) {
        marked = new boolean[G.V()];
        color = new int[G.V()];
        validateVertex(s);
        color[s] = 1;
        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Edge>[] adj = (Iterator<Edge>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
        boolean valid = true;
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                Edge w = adj[v].next();
                if (!marked[w.getPointer()]) {
                    // discovered vertex w for the first time
                    marked[w.getPointer()] = true;
                    stack.push(w.getPointer());
                }
                    //Colored logic
                    int colorV = color[v];
                    int colorW = color[w.getPointer()];
                    int positionW = w.getPointer();
                    
                    if (w.getConflict()) { // Vertexs should have different color
                        if (colorV != 0 && colorW != 0 && colorV == colorW) {
                            valid = false;
                        } else {
                            if (colorV==1 && colorW==0) {
                                color[positionW]=2;
                            } else if (colorV==0 && colorW == 1){
                                color[v]=2;
                            } else if (colorV==2 && colorW == 0){
                                color[positionW]=1;
                            } else if (colorV==0 && colorW == 2){
                                color[v]=1;
                            } else {
                                //Do nothing, they already have different color
                            }
                        } 
                    } else { //Vertexs should have same color
                        if (colorV != 0 && colorW != 0 && colorV != colorW) {
                            valid = false;
                        } else {
                            if (colorV==1 && colorW==0) {
                                color[positionW]=1;
                            } else if (colorV==0 && colorW == 1){
                                color[v]=1;
                            } else if (colorV==2 && colorW == 0){
                                color[positionW]=2;
                            } else if (colorV==0 && colorW == 2){
                                color[v]=2;
                            } else {
                                //Do nothing, they already have the same color
                            }
                        }
                    }
                    if (!valid) {
                        break;
                    }
                    //End of color logic


            }
            else {
                // StdOut.printf("%d done\n", v);
                stack.pop();
            }
        }
        System.out.println(valid ? "1" : "0");
    }

    /**
     * Is vertex {@code v} connected to the source vertex {@code s}?
     * @param v the vertex
     * @return {@code true} if vertex {@code v} is connected to the source vertex {@code s},
     *    and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/