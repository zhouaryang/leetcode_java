package leecode;

import java.awt.datatransfer.StringSelection;

import leecode.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		func1();
//		func2();
//		func3();
//		func7();
//		func8();
//		func9();
//		func5();
//		func6();
		func11();
//		func13();
//		func14();
		
		       
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
	
	public static void func5(){
		String ss = "aaaaa";
		System.out.println(solution.longestPalindrome2(ss));
		
//		StringBuilder s = new StringBuilder("babad");
//		System.out.println(s.toString().equals(s.reverse().toString()));
	}
	public static void func6(){
		String ss = "ABCDEFGHIGKLMN";
		System.out.println(solution.convert3(ss, 3));
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
	
	public static void func13(){
		String s = "LVIII";
		System.out.print(solution.romanToInt(s));
	}
	public static void func14(){
		String strs[] = {"flower","flow","flight"};
		String strs2[] = {"dog","racecar","car"};
		System.out.println(solution.longestCommonPrefix(strs2));
	}
	public static void func8(){
		String s1 = "   -234"; //-234
		String s2 = "words and 987"; // 0
		String s3 = "4193 with words1"; // 4193
		String s4 = "-91283472332";
		String s5 = "20000000000000000000";
		System.out.println(solution.myAtoi(s5));
	}
	public static void func11(){
		int a[] = {1,8,6,2,5,4,8,3,7};
		System.out.println(solution.maxArea2(a));
	}
	
}
class Node{
	   public Node(){
	       
	   }
	    public Node(int value){
	        this.val = value;
	        this.next = null;
	    }
	   public  int val;
	   public  Node next;
	   
	}