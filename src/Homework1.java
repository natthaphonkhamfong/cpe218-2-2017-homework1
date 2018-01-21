import org.w3c.dom.*;
import javax.imageio.metadata.*;
import java.util.*;

public class Homework1 {
    static private Stack stack = new Stack();
    static private Node root; //root node

    private static void infix(Node n) {
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

    private static void inorder(Node n) {
        if (n != null){
            if(isOperate(n.getNodeName().charAt(0)) && n != root) System.out.print("(");
            NodeList list = n.getChildNodes(); //list get child nodes
            inorder(list.item(1)); //recursive left node
            System.out.print(n.getNodeName());   //print itself
            inorder(list.item(0));    //recursive right node
            if(isOperate(n.getNodeName().charAt(0)) && n != root) System.out.print(")");
        }
    }

    private static void calculate(Node n) {
        NodeList list = n.getChildNodes(); //list get child nodes
        int left_value = Integer.parseInt(list.item(1).getNodeValue()); //value of left node (0-9)
        int right_value = Integer.parseInt(list.item(0).getNodeValue()); //value of right node (0-9)
        int result = 0;
        switch(n.getNodeName()) {
            case "+" :{
                result = left_value + right_value;
                break;
            }
            case "-" :{
                result = left_value - right_value;
                break;
            }
            case "*" :{
                result = left_value * right_value;
                break;
            }
            case "/" :{
                result = left_value / right_value;
                break;
            }
        }
        n.setNodeValue(Integer.toString(result));
    }

    private static boolean isOperate(char string) {
        //System.out.println("string is " + string);
        return	(string == '+' || string == '-' ||
                string == '*' || string == '/');
    }

    public static void main(String[] args) {
        // Begin of arguments input sample
        if (args.length > 0) {
            String input = args[0];
            if (input.equalsIgnoreCase("251-*32*+")) {
				System.out.println("(2*(5-1))+(3*2)=14");
            }
        }
        // End of arguments input sample

        // TODO: Implement your project here
        String data;
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