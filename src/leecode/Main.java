package leecode;

import leecode.solution;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		solution solu =new solution();
		
		//No.1 两数之和
		/*int nums[] = {2,7,11,15};
		int target = 13;
		solu.twoSum(nums, target);
		System.out.println(solu.twoSum(nums, target)[1]);
		*/
		
		func2();
	}
	
	public static void func2(){
		//No.2 两数相加
		/* class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; next = null;} //构造函数
		}*/
		 ListNode l11 = new ListNode(2);
		 ListNode l12 = new ListNode(4);l11.next =l12;
		 ListNode l13 = new ListNode(3);l12.next =l13;
		 
		 ListNode l21 = new ListNode(5);
		 ListNode l22 = new ListNode(6);l21.next = l22;
		 ListNode l23 = new ListNode(4);l22.next = l23;
		 ListNode l24 = new ListNode(1);l23.next = l24;
		 solution solu =new solution(); 
		 ListNode l6 = solu.addTwoNumbers(l11, l21);
		 while(l6 != null){
			 System.out.println("value is "+ l6.val);
		 }
	}

}
