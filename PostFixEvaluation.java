public class PostFixEvaluation {

    static class MyStack {
        private int[] arr;
        private int top;

        public MyStack(int size) {
            arr = new int[size];
            top = -1;
        }

        public void push(int value) {
            arr[++top] = value;
            System.out.println("Pushed " + value + " -> Stack: " + this);
        }

        public int pop() {
            int value = arr[top--];
            System.out.println("Popped " + value + " -> Stack: " + this);
            return value;
        }

        public int peek() {
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i <= top; i++) {
                sb.append(arr[i]);
                if (i < top) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static int evaluate(String expression) {
        MyStack stack = new MyStack(50);
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int result = 0;

                switch (token) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": result = a / b; break;
                }
                stack.push(result);
                System.out.println("Applied " + token + " -> Stack: " + stack);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String expr = "5 3 + 2 *";
        System.out.println("Postfix Expression: " + expr);
        int result = evaluate(expr);
        System.out.println("Final Result = " + result);
    }
}