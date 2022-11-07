import java.util.Scanner;
import java.util.Stack;

public class App {
    Stack<Character> stack;

    public App(){
    }

    public boolean balanced(String expression) {
        stack = new Stack<>();
        char[] chars = expression.toCharArray();
        for (char c : chars) {
            if (c=='(' || c=='[' || c=='{') {
                stack.push(c);
            } else if (c==')' || c==']' || c=='}') {
                if (!pop(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean pop(char c) {
        if (stack.isEmpty()) {
            return false;
        }
        char lastChar = stack.pop();
        if (opposite(lastChar)!=c) {
            return false;
        }
        return true; 
    }

    public char opposite(char c) {
        switch (c) {
            case '{':
                return '}';
            case '[':
                return ']';
            case '(':
                return ')';
            default:
                return '.';
        }
    }

    public static void main(String[] args) throws Exception {
        App balancer = new App();
        Scanner myReader = new Scanner(System.in);
        System.out.println(balancer.balanced(myReader.nextLine()) ? "1" : "0");
    }
}
