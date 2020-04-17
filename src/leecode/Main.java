package leecode;

import leecode.solution;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		func1();
//		func2();
//		func3();
//		func7();
//		func9();
		func5();
	}
	private static solution solution = new solution();
	
	public static void func1(){
//		solution solu =new solution();
		
		//No.1 两数之和
		int nums[] = {2,7,11,15};
		int target = 13;
		solution.twoSum(nums, target);
		System.out.println(solution.twoSum(nums, target)[1]);
		
	}
	public static void func2(){
		//No.2 两数相加
		/* class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; next = null;} //构造函数
		}*/
		 ListNode l11 = new ListNode(1);
		 /*ListNode l12 = new ListNode(4);l11.next =l12;
		 ListNode l13 = new ListNode(3);l12.next =l13;*/
		 
		 ListNode l21 = new ListNode(9);
		 ListNode l22 = new ListNode(9);l21.next = l22;
	/*	 ListNode l23 = new ListNode(4);l22.next = l23;
		 ListNode l24 = new ListNode(1);l23.next = l24;*/
//		 solution solu =new solution(); 
		 ListNode l6 = solution.addTwoNumbers(l11, l21);
		 while(l6 != null){
			 System.out.println("value is "+ l6.val);
			 l6 = l6.next;
		 }
	}
	public static void func3(){
		String s = "pwwkew";
//		solution solution = new solution();
		System.out.println(solution.lengthOfLongestSubstring(s));
	}
	
	public static void func7(){
		int  a = 1563847412;
//		solution solution = new solution();
		System.out.println(solution.reverse3(a));
	}
	public static void func9(){
		int x = -12321;
		System.out.println(solution.isPalindrome2(x));
	}
	public static void func5(){
		String ss = "a";
		System.out.println(solution.longestPalindrome(ss));
		
//		StringBuilder s = new StringBuilder("babad");
//		System.out.println(s.toString().equals(s.reverse().toString()));
	}

}
