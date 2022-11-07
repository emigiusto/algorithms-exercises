import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        File myObj = new File("./data/1.txt");
            try {
            Scanner sc = new Scanner(myObj);
            int m = sc.nextInt();
            UF uf = new UF(m);
            sc.nextLine();
            while (sc.hasNext()) {
                boolean same = uf.find(sc.nextInt()) != uf.find(sc.nextInt());
                if (sc.next().equals("=")) {
                    if (!same) {
                        uf.union(sc.nextInt(), sc.nextInt());
                    }
                } else {
                    if (same) {
                        System.out.println("yes");
                    } else {
                        System.out.println("no");
                    }
                }
                sc.nextLine();
            }
            sc.close();
        }catch (Exception e){

        }
    }
}