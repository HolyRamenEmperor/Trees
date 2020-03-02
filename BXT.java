// Name: J4-18
// Date: 2/6/20

import java.util.*;

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      Stack<TreeNode> stk = new Stack<TreeNode>();
     	String[] word = str.split(" ");
      for(String s : word)
      {
         if(!isOperator(s))
         {
            TreeNode node = new TreeNode(s);
            stk.push(node);
         }
         else
         {
            TreeNode op = new TreeNode(s);
            op.setRight(stk.pop());
            op.setLeft(stk.pop());
            stk.push(op);
         }
      }
      root = stk.pop();
            
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null)
      {
         return 0;
      }
      if(isOperator((String)t.getValue()))
      {
         return computeTerm((String)t.getValue(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      }
      return Double.parseDouble((String)t.getValue());
      
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
      {
         return a + b;
      }
      else if(s.equals("-"))
      {
         return a - b;
      }
      else if(s.equals("*"))
      {
         return a * b;
      }
      else if(s.equals("/"))
      {
         return a / b;
      }
      return Math.pow(a, b);
   }
   
   private boolean isOperator(String s)
   {
      String ops = "+-*/^";
      if(ops.contains(s))
      {
         return true;
      }
      return false; 
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";   
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              
      toReturn += preorderTraverse(root.getLeft());   
      toReturn += preorderTraverse(root.getRight());  
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}