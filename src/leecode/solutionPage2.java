package leecode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class solutionPage2 {
	
	/* No.94 二叉树的中序遍历
	 * 给定一个二叉树，返回它的中序 遍历。
	 * ep:输入: [1,null,2,3] out: 1,3,2
	 */
	public List<Integer> inorderTraversal(TreeNode root){
		
		List<Integer> list = new LinkedList<Integer>();
//		中序遍历：
//		使用一个队列存储元素,使用递归
		if(root == null){
			return list;
		}
//		递归入队列
		enQue(root,list);
		
		return list;
	}
	public void enQue(TreeNode node,List<Integer> queue){
		
		if(node.left != null){
			enQue(node.left, queue);
		}
		queue.add(node.val);
		if(node.right != null){
			enQue(node.right, queue);
		}
	}
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int val){
		this.val = val;
	}
}

