import java.util.Stack;

public class InfixConversion {

    static int precedence(char ch) {
        switch (ch) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() &&
                       precedence(stack.peek()) >= precedence(ch))
                    result.append(stack.pop());
                stack.push(ch);
            }
        }

        while (!stack.isEmpty())
            result.append(stack.pop());

        return result.toString();
    }

    static String infixToPrefix(String exp) {
        StringBuilder sb = new StringBuilder(exp).reverse();

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') sb.setCharAt(i, ')');
            else if (sb.charAt(i) == ')') sb.setCharAt(i, '(');
        }

        String postfix = infixToPostfix(sb.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        String exp = "(A+B)*C";
        System.out.println("Postfix: " + infixToPostfix(exp));
        System.out.println("Prefix: " + infixToPrefix(exp));
    }
}
