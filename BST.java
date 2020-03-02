// Name: J4-18
// Date: 2/20/20

import java.util.*;
interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the addBalanced() method.
**********************/
public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /******
   @param s -- one string to be inserted
   ********/
   public void add(String s) 
   {
      add(root, s);
   }
   private TreeNode add(TreeNode t, String s) 
   { 
      if(t == null)
      {
         root = new TreeNode(s);
         size++;
         return root;
      }  
      if(t.getLeft() == null && s.compareTo((String)t.getValue()) <= 0)
      {   
         t.setLeft(new TreeNode(s));
         size++;
         return t.getLeft();
      }
      if(t.getRight() == null && s.compareTo((String)t.getValue()) > 0)
      {
         t.setRight(new TreeNode(s));
         size++;
         return t.getRight();
      }
      if(s.compareTo((String)t.getValue()) <= 0)
      {
         return add(t.getLeft(), s);
      }
      if(s.compareTo((String)t.getValue()) > 0)
      {
         return add(t.getRight(), s);
      }
      return t;   
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
   
   public boolean contains(String obj)
   {
      return contains(root, obj);
   }
   public boolean contains(TreeNode t, String x)
   {
      if(t == null)
         return false;
      if(x.compareTo((String)t.getValue()) < 0)
         return contains(t.getLeft(), x);
      else if(x.compareTo((String)t.getValue()) > 0)
         return contains(t.getRight(), x); 
      return true;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return null;
      while(t.getLeft() != null)
         t = t.getLeft();
      return (String)t.getValue();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //use recursion
   {
      if(t == null)
         return null;
      String rootval = (String)t.getValue();
      String leftval = max(t.getLeft());
      String rightval = max(t.getRight());
      
      if(leftval != null && rootval != null && leftval.compareTo(rootval) > 0)
      {
         rootval = leftval;
      }
      if(rightval != null && rootval != null && rightval.compareTo(rootval) > 0)
      {
         rootval = rightval;
      }
      return rootval;
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";   
      toReturn += toString(t.getRight());
      return toReturn;
   }  
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      //case 1b), 2c), 2d)
      if(current.getLeft() == null && current.getRight() == null)
      {
         current = null;
         return current;
      }
      else if(current.getLeft() != null && current.getRight() == null && current.getValue().equals(target))
      {
         current = current.getLeft();
         return current;
      }
      else if(current.getRight() != null && current.getLeft() == null && current.getValue().equals(target))
      {
         current = current.getRight();
         return current;
      }
      TreeNode parent = current;
      TreeNode node = current; 
      boolean found = false;
      while(node != null && found == false)
      {
         if(target.compareTo((String)node.getValue()) < 0)
         {
            if(parent == node)
            {   
               node = node.getLeft();
            }
            else
            {
               if(parent.getRight() == node)
               {
                  parent = parent.getRight();
               }
               else if(parent.getLeft() == node)
               {
                  parent = parent.getLeft();
               }
               node = node.getLeft(); 
            }
         }
         else if(target.compareTo((String)node.getValue()) > 0)
         {
            if(parent == node)
            {   
               node = node.getRight();
            }
            else
            {
               if(parent.getRight() == node)
               {
                  parent = parent.getRight();
               }
               else if(parent.getLeft() == node)
               {
                  parent = parent.getLeft();
               }
               node = node.getRight(); 
            }
         }
         else if(target.compareTo((String)node.getValue()) == 0)
         {
            found = true;
         }
      }
      //case 1a)
      if(node.getLeft() == null && node.getRight() == null && parent.getLeft() == node)
      {
         parent.setLeft(null);
         return current;
      }
      else if(node.getLeft() == null && node.getRight() == null && parent.getRight() == node)
      {
         parent.setRight(null);
         return current;
      }
      //case 2a), 2b)
      if(node.getLeft() == null)
      {
         if(parent.getLeft() == node)
         {
            parent.setLeft(node.getRight());
            return current;
         }
         else if(parent.getRight() == node)
         {
            parent.setRight(node.getRight());
            return current;
         }
      }
      else if(node.getRight() == null)
      {
         if(parent.getLeft() == node)
         {
            parent.setLeft(node.getLeft());
            return current;
         }
         else if(parent.getRight() == node)
         {
            parent.setRight(node.getLeft());
            return current;
         }
      }
      //case 3a)/3b)
      String maxval = max(node.getLeft());
      TreeNode tparent = node;
      TreeNode tnode = node.getLeft(); 
      boolean found2 = false;
      while(tnode != null && found2 == false)
      {
         if(maxval.compareTo((String)tnode.getValue()) < 0)
         {
            if(tparent == tnode)
            {   
               tnode = tnode.getLeft();
            }
            else
            {
               if(tparent.getRight() == tnode)
               {
                  tparent = tparent.getRight();
               }
               else if(tparent.getLeft() == tnode)
               {
                  tparent = tparent.getLeft();
               }
               tnode = tnode.getLeft(); 
            }
         }
         else if(maxval.compareTo((String)tnode.getValue()) > 0)
         {
            if(tparent == tnode)
            {   
               tnode = tnode.getRight();
            }
            else
            {
               if(tparent.getRight() == tnode)
               {
                  tparent = tparent.getRight();
               }
               else if(tparent.getLeft() == tnode)
               {
                  tparent = tparent.getLeft();
               }
               tnode = tnode.getRight(); 
            }
         }
         else if(maxval.compareTo((String)tnode.getValue()) == 0)
         {
            found2 = true;
         }
      }
      //case 3a)
      if(tnode.getLeft() == null && tnode.getRight() == null && tparent.getRight() == tnode)
      {
         node.setValue(maxval);
         tparent.setRight(null);
         return current;
      }
      else if(tnode.getLeft() == null && tnode.getRight() == null && tparent.getLeft() == tnode)
      {
         node.setValue(maxval);
         tparent.setLeft(null);
         return current;
      }
      //case 3b)
      if(tnode.getLeft() != null && tnode.getRight() == null && tparent.getLeft() == tnode)
      {
         node.setValue(maxval);
         tparent.setLeft(tnode.getLeft());
         return current;
      }
      else if(tnode.getLeft() != null && tnode.getRight() == null && tparent.getRight() == tnode)
      {
         node.setValue(maxval);
         tparent.setRight(tnode.getLeft());
         return current;
      }
      return current;
   }
   public void addBalanced(String value)  //new method
   {
      size++;
      root = balanceTree(root, value);   // AVL tree.  You may change it.
   }
   public int getHeight(TreeNode tn)
   {
      if(tn != null)
      {
         return tn.getHeight();
      }
      return 0;
   }
   public TreeNode balanceTree(TreeNode t, String s)
   {
      if(t == null) 
      {
         TreeNode n = new TreeNode(s);
         n.setHeight(1);
         return (n);
      }
      if(s.compareTo((String)t.getValue()) < 0) 
      {
         t.setLeft(balanceTree(t.getLeft(), s));
      } 
      else if(s.compareTo((String)t.getValue()) > 0)
      {
         t.setRight(balanceTree(t.getRight(), s));
      }
      
      t.setHeight(Math.max(getHeight(t.getLeft()), getHeight(t.getRight())) + 1);
      int balance = calcBalance(t);
      
      if(balance > 1 && s.compareTo((String)t.getLeft().getValue()) < 0)
      {
         return LLrot(t);
      }
      if(balance < -1 && s.compareTo((String)t.getRight().getValue()) > 0)
      {
         return RRrot(t);
      }
      if(balance > 1 && s.compareTo((String)t.getLeft().getValue()) > 0)
      {
         return LRrot(t);
      }
      if(balance < -1 && s.compareTo((String)t.getRight().getValue()) < 0)
      {
         return RLrot(t);
      }
      return t;
      
   }
   public int calcBalance(TreeNode t)
   {
       if(t != null)
       {
           return getHeight(t.getLeft()) - getHeight(t.getRight());
       }
       return 0;  
   }
  public TreeNode LLrot(TreeNode r)
  {
      TreeNode one = r.getLeft();
      TreeNode two = r.getLeft().getRight();
      one.setRight(r);
      r.setLeft(two);
      r.setHeight(Math.max(getHeight(r.getLeft()), getHeight(r.getRight())) + 1);
      one.setHeight(Math.max(getHeight(one.getLeft()), getHeight(one.getRight())) + 1);
      return one;
  }
  public TreeNode RRrot(TreeNode r)
  { 
      TreeNode one = r.getRight();
      TreeNode two = r.getRight().getLeft();
      one.setLeft(r);
      r.setRight(two);
      r.setHeight(Math.max(getHeight(r.getLeft()), getHeight(r.getRight())) + 1);
      one.setHeight(Math.max(getHeight(one.getLeft()), getHeight(one.getRight())) + 1);
      return one;
  }
  public TreeNode LRrot(TreeNode r)
  {
      r.setLeft(RRrot(r.getLeft()));
      return LLrot(r);
  }
  public TreeNode RLrot(TreeNode r)
  {
      r.setRight(LLrot(r.getRight()));
      return RRrot(r);
  }
}