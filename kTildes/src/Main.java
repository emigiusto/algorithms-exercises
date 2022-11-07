import java.util.Scanner;


public class Main extends UF{
    

    public Main(int n){
        super(n);
    }    
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int m = sc.nextInt();
            int n = sc.nextInt();
            UF main = new UF(m);
            sc.nextLine();

            for (int i = 0; i < n; i++) {
                String fullInstruct = sc.nextLine();
                String instruction = fullInstruct.substring(0, 1);
                if (instruction.equals("s")) {
                    System.out.println(main.size(Integer.parseInt(fullInstruct.substring(2, fullInstruct.length()))-1));
                } else if (instruction.equals("t")){ 
                    String[] inputT = fullInstruct.split(" ");
                    main.union(Integer.parseInt(inputT[1])-1, Integer.parseInt(inputT[2])-1);
                }
            }
            sc.close();
    }
}