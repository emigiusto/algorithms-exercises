import java.io.File;
import java.util.Scanner;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;

public class App {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Grade[] students = new Grade[n]; 
        int counter = 0;
        while (sc.hasNext()) {
            sc.nextLine();
            students[counter] = new Grade(sc.next(),sc.next());
            counter++;
        } 
            Insertion.sort(students);
            for (int i = students.length-1; i>=0 ; i--) {
                System.out.println(students[i].getName());
            }
            sc.close();
    }
}