import java.util.Scanner;
import java.util.Stack;

public class Balance {

    public static void main(String[] args) {

        Scanner line = new Scanner(System.in);
        String w = line.next();

        Stack<Character> balance = new Stack<>();

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(' || w.charAt(i) == '[' || w.charAt(i) == '{' ) {
                balance.push(w.charAt(i));
            } else if ((w.charAt(i) == ')') && balance.peek() == '(' ||
                    (w.charAt(i) == ']' && balance.peek() == '[') ||  (w.charAt(i) == '{' && balance.peek() == '}')) {
                balance.pop();
            } else {
                balance.push(w.charAt(i));
            }
        }
        if (balance.isEmpty()) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
        line.close();
    }
}