import org.w3c.dom.*;
import javax.imageio.metadata.*;
import java.util.*;

public class Homework1 {
    static Stack stack = new Stack();
	static Node root;

	static public void inorder(Node n) {
		if (n != null){
			if(isOperate(n.getNodeName().charAt(0)) && n != root) System.out.print("(");
            inorder(n.getChildNodes().item(1)); //recursive left node
			System.out.print(n.getNodeName());   //print itself
			inorder(n.getChildNodes().item(1));    //recursive right node
            if(isOperate(n.getNodeName().charAt(0)) && n != root) System.out.print(")");
        }
	}

	static public void infix(Node n) {
		//base case
		if(!isOperate(n.getNodeName().charAt(0))) {
			n.setNodeValue(n.getNodeName());
			return;
		}
        //Constructs an right node with a given stack.pop().toString().
		Node right = new IIOMetadataNode(stack.pop().toString());
		infix(right);
        //Constructs an left node with a given stack.pop().toString().
		Node left = new IIOMetadataNode(stack.pop().toString());
		infix(left);
        //append right node in n
		n.appendChild(right);
        //append left node in n
		n.appendChild(left);
		calculate(n);
	}

	static public void calculate(Node n) {
		int left_value = Integer.parseInt(n.getChildNodes().item(1).getNodeValue());
		int right_value = Integer.parseInt(n.getChildNodes().item(0).getNodeValue());
		int result = 0;
		switch(n.getNodeName()) {
			case "+" :{
				result = left_value + right_value;
			}
			case "-" :{
				result = left_value - right_value;
			}
			case "*" :{
				result = left_value * right_value;
			}
			case "/" :{
				result = left_value / right_value;
			}
		}
//		System.out.println(left_value  + n.getNodeName() + right_value + "=" + result);
		n.setNodeValue(Integer.toString(result));
	}

	public static boolean isOperate(char string) {
		//System.out.println("string is " + string);
		return	(string == '+' || string == '-' ||
				string == '*' || string == '/');
	}

	public static void main(String[] args) {
		// Begin of arguments input sample
		if (args.length > 0) {
			String input = args[0];
			if (input.equalsIgnoreCase("251-*32*+")) {
//				System.out.println("(2*(5-1))+(3*2)=14");
			}
		}
		// End of arguments input sample

		// TODO: Implement your project here
		String data = "";
//		data = " "
		Scanner in = new Scanner(System.in);
		data = in.nextLine();
		in.close();
//		System.out.println(data.length());

		for(int i = 0; i < data.length(); i++) {
//			System.out.print(data.charAt(i));
			stack.push(data.charAt(i));
		}
//		System.out.println();
		root = new IIOMetadataNode(stack.pop().toString());
		infix(root);
		inorder(root);
		System.out.print("=" + root.getNodeValue());
	}
}