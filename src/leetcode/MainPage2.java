package leetcode;

import java.util.Iterator;
import java.util.List;

public class MainPage2 {

	public static  solutionPage2 solution2 = new solutionPage2();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		func94();
	}
	
	public static void func94(){
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);root.right = node2;
		TreeNode node3 = new TreeNode(3); node2.left = node3;
		List<Integer> list = solution2.inorderTraversal(root);
		for(Iterator<Integer> iterator = list.iterator();iterator.hasNext();){
			System.out.println(iterator.next());
		}
	}
}
