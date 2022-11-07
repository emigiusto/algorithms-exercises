public class App {
    public static void main(String[] args){
        System.out.println(valid(8,8,4,5,5,7));
        System.out.println(valid(8,8,4,5,5,6));
        
    }

    public static boolean valid(int N, int M, int x1, int y1, int x2, int y2){
        int distX = Math.abs(x2-x1);
        int distY = Math.abs(y2-y1);

        if (((distX == 2 && distY==1 )|| (distX == 1 && distY==2))&& x2<=N && y2<=M && x2>0 && y2>0 ) {
            return true;
        }
        return false;
    } // running time: O(1)
}