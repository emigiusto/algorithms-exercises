import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 

public class App extends UF{
    

    public App(int n){
        super(n);
    }

    public static void main( String[] args) {
            File myObj = new File("./data/1.txt");
            try {
                Scanner myReader = new Scanner(myObj);
                int n = myReader.nextInt();
                int m = myReader.nextInt();
                myReader.nextLine();
                App app = new App(n);
                for(int i=0;i<m;i++) {
                    String [] line = myReader.nextLine().split(" ");
                    String q = line[0];
                    int s = Integer.parseInt(line[1]);
                    int t = Integer.parseInt(line[2]);
                    //System.out.println(q);
                    if( q.equals("1")) {
                        app.union(s,t);
                    } else if (q.equals("2")){
                        app.move(s,t);
                    } else {
                        if (app.find(s) == app.find(t)) {
                            System.out.println("1");
                        } else {
                            System.out.println("0");
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }
}