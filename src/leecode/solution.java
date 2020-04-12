package leecode;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.PrinterLocation;

public class solution {
	
	/* No.1: 
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
		你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
		示例:
		给定 nums = [2, 7, 11, 15], target = 9
		因为 nums[0] + nums[1] = 2 + 7 = 9
		所以返回 [0, 1]
	 */
	public int[] twoSum(int[] nums,int target){
		
		//暴力解法：
		/*int result[] = new int [2];
		if(nums.length <=1){
			return null;
		}
		for(int i=0 ; i < nums.length-1 ;i++){
			for(int j = i ; j<nums.length ;j++){
				if(nums[i] + nums[j] == target){
					result[0]=i;result[1] =j;
					return result;
				}
			}
		}*/
		
		//显然暴力O（n^2）,
		//使用hashmap.
		Map<Integer, Integer> map = new HashMap<>();
		int result[] = new int [2];
		for(int i =0 ; i< nums.length ; i++){
			if(map.containsKey(target - nums[i])){ //现在判断target-a[i]是否存在于map中
				//存在则返回
				result[0]=map.get(target - nums[i]);
				result[1]=i;
				return result;
			}
			map.put(nums[i],i);//每次找不到就塞到map，值和下标
		}
		return null;
	}
	
	
	/* No.2:
	 * 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
		如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
		您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
		示例：
		输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
		输出：7 -> 0 -> 8
		原因：342 + 465 = 807
	 */
	
	//内部类 表示链接电费
		
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		//这个题关键点在于进位处理，有一个标志位记录是否进位，从低位到高位依次相加。
		ListNode l3 = l1,l4 =l2,l5 = null,l6 =l5;
		int len1 = 0 ,len2 = 0;
		int lflag = 0,rflag=0,value=0;//进位表示1,value代表两数之和
		
		while(l3 != null){ //
			len1++;
			l3 = l3.next;
		}
		while(l4 != null){
			len2++;
			l4 = l4.next;
		}
		for(int i =0;i < Math.max(len1, len2);i++){
			if(l1 == null){ //如果遍历到尾部
				l1 = new ListNode(0);
			}
			if(l2 == null){
				l2 = new ListNode(0);
			}
			value = l1.val + l2.val;
			
			if(value >= 10 ){
				value = value -10;
				rflag = 1;
			}else{
				rflag = 0;
			}
			ListNode l = new ListNode(value+lflag);
//			System.out.println(value+lflag);
			l5 =l;l5 = l5.next;
			l1 = l1.next;l2 = l2.next;lflag = rflag;
		}
		return l6;

    }
	
	
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null;} //构造函数
	}