
public class Homework1 {

	public static int length;
	public static String input;
	public static char arr[];



	public static void main(String[] args) {

		// Begin of arguments input sample

		if (args.length > 0) {

			input = args[0];
			length = input.length() - 1;
            arr = input.toCharArray();
            Node BTC = new Node(arr[length]);
            inorder(BTC);
            infix(BTC);
            System.out.print("=" + calculate(BTC));
		}else {

            System.out.print("else");
            }
		}
		// End of arguments input sample

		// TODO: Implement your project here
    static Node root;

	public static class Node {
		Node left;
		Node right;

		char key;
		public Node(char data) {
		    this.key = data;

		}
        public Node() {
        }

	}

    public static void inorder(Node n) {
        if (root == null) {
            if (IsOp(n)) {
                root = n;
                length--;
                //   System.out.print("1root"+n.key);

            }
            /*else{
                n.left = new Node(arr[length]);
                // System.out.print("left"+arr[length]);
                length--;}}*/
        }
        if (IsOp(n)) {
            n.right = new Node(arr[length]);
            length--;
            inorder(n.right);
            n.left = new Node(arr[length]);
            length--;
            inorder(n.left);
        }
    }

	public static boolean IsOp(Node n) {
		return n.key == '+' || n.key == '-' || n.key == '*' || n.key == '/';
	}


	public static void infix(Node n) {
        if (n == root) {
            infix(n.left);
            System.out.print(n.key);
            infix(n.right);

        } else if (IsOp(n)) {
            System.out.print('(');
            infix(n.left);
            System.out.print(n.key);
            infix(n.right);
            System.out.print(')');

        } else {
            System.out.print(n.key);
        }
           // System.out.print("R"+n.right);

           // System.out.print("L"+n.left);

	}


	public static int calculate(Node n) {
		if (IsOp(n)) {
			switch (n.key) {
				case '+':
					return calculate(n.left) + calculate(n.right);
				case '-':
					return calculate(n.left) - calculate(n.right);
				case '/':
					return calculate(n.left) / calculate(n.right);
				case '*':
					return calculate(n.left) * calculate(n.right);
			}
		} else {
			return Character.getNumericValue(n.key);
		}
		return 0;
	}

}
