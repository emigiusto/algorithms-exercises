
// Kattio.java
// https://open.kattis.com/download/Kattio.java


/* Simple yet moderately fast I/O routines.

   Example usage: see test client at end

   Some notes:
   - When done, you should always do io.close() or io.flush() on the
     Kattio instance, otherwise, you may lose output.

   - The nextInt(), nextDouble(), and nextLong() methods will throw an
     exception if there is no more data in the input, so it is generally
     a good idea to use hasNext() to check for end-of-file.

  --------------- ad's changes ---------------------------

   * changed public method names to be more scanner like:
       getInt() --> nextInt()
       getDouble()  --> nextDouble()
       getLong() --> nextLong()
       getWord() --> next()
       hasMoreTokens() --> hasNext()

*/


import java.util.*;
import java.io.*;



public class Kattio extends PrintWriter
{
  private static BufferedReader r;
  private String line, token;
  private StringTokenizer st;



  public Kattio(InputStream i)
  {  super(new BufferedOutputStream(System.out));
     r = new BufferedReader(new InputStreamReader(i));
  }


  public Kattio(InputStream i, OutputStream o)
  { super(new BufferedOutputStream(o));
    r = new BufferedReader(new InputStreamReader(i));
  }


  public boolean hasNext()
  {   return peekToken() != null; }


  public int nextInt()
  {  return Integer.parseInt(nextToken()); }


  public double nextDouble()
  {  return Double.parseDouble(nextToken()); }


  public long nextLong()
  {  return Long.parseLong(nextToken()); }


  public String next()
  {  return nextToken();  }



  private String nextToken()
  { String ans = peekToken();
    token = null;
    return ans;
  }



  private String peekToken()
  {
    if (token == null)
      try {
        while (st == null || !st.hasMoreTokens()) {
          line = r.readLine();
          if (line == null) 
            return null;
          st = new StringTokenizer(line);
        }
        token = st.nextToken();
      }
      catch (IOException e) {}
    return token;
  }  // end of peekToken()


  public static void main(String[] args) throws IOException{

    Kattio io = new Kattio(System.in);
    int vertices = io.nextInt();
    int edges = io.nextInt();
    int v = 0;
    int w = 0;
    boolean isHarmon = true;
    Gra graph = new Gra(vertices);
      for (int i = 0; i < edges; ++i) {
      v= io.nextInt();
      w= io.nextInt();
      boolean typeOfEdge = io.nextInt() == 1 ? true:false;
        isHarmon = graph.addEdge(v, w, typeOfEdge);
        if(!isHarmon){
          break;
       }
      }
      io.println((isHarmon ? 1 : 0));
    io.close();
  }
          
}
  

     
