
import java.util.Stack;

public class Medhod  {
    Node root;
    Node current;
    int N=0;

    public Medhod() {

    }

    public boolean isOperator(char a) {
        if (a == '+' || a == '-' || a == '*' || a == '/') {
            return true;
        } else {
            return false;
        }
    }

    public void makeTree(Node node) {

        if (root == null) {
            root = node;
            current = root;
        } else if (isOperator(current.key)) {
            if(!isOperator(node.key)){
                String a = node.key + "";
                node.value = Integer.parseInt(a);
            }
            if (current.right == null) {
                current.right = node;
                node.parent = current;
                if (isOperator(current.right.key)) {
                    current = current.right;
                }

            } else if (current.left == null) {
                current.left = node;
                node.parent = current.left;
                if (isOperator(current.left.key)) {
                    current = current.left;
                }
            } else {
                current = current.parent;
                makeTree(node);
            }
        }
    }

    public void makeTree(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            makeTree(new Node(s.charAt(i)));
        }
    }

    

    public void inorder() {
        inorder(root);
    }

    public void inorder(Node n) {
        if (n == null) {//if have no node
            return;
        } else {//by algorithm
            inorder(n.left);
//            if(N == 0){
//                System.out.print(n.key);
//            }
            if(n.left != null && n.right != null){
                Calculate(n);
            }
            inorder(n.right);
        }
    }

    public String Infix(String postfix) {
        Stack<String> s = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (isOperator(c)) {
                String b = s.pop();
                String a = s.pop();
                if (i == postfix.length() - 1) {
                    s.push(a + c + b);
                } else {
                    s.push("(" + a + c + b + ")");
                }

            } else {
                s.push("" + c);
            }
        }

        return s.pop();
    }

    public String Calculate(String input) {
        Stack<String> s = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            if (!isOperator(x)) {
                s.push("" + x);
            } else {
                int b = Integer.parseInt(s.pop());
                int a = Integer.parseInt(s.pop());
                if (x == '+') {
                    s.push(Integer.toString(a + b));
                } else if (x == '-') {
                    s.push(Integer.toString(a - b));
                } else if (x == '*') {
                    s.push(Integer.toString(a * b));
                } else if (x == '/') {
                    s.push(Integer.toString(a / b));
                }
            }
        }
        return s.pop();
    }

    public void Calculate() {
        N = 1;
        inorder();
        System.out.print(" = "+root.value);
    }

    public void Calculate(Node n) {
        if (n.key == '+') {
            n.value = n.left.value + n.right.value;
        } else if (n.key == '-') {
            n.value = n.left.value - n.right.value;
        } else if (n.key == '*') {
            n.value = n.left.value * n.right.value;
        } else if (n.key == '/') {
            n.value = n.left.value / n.right.value;
        }
        }

    }




