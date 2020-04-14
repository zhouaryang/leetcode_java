package leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	
		
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
//		已ACE，但是效率很低，时间7%，内存93%。 写的很烂，
		//这个题关键点在于进位处理，有一个标志位记录是否进位，从低位到高位依次相加。
//		java中没有指针的概念，所以l5 =l6 就是引用同一个对象，此时再遍历谁都是ok的。但是需要用到next域，
//		不能是null对象，所以最好使用赋值初始化之后的变量遍历，遍历使用next去链接第一个节点，最后返回next域
		ListNode l3 = l1,l4 =l2,l5 = new ListNode(0);
		ListNode l6 = l5; 
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
		for(int i =0;i <= Math.max(len1, len2);i++){
			if(l1 == null){ //如果遍历到尾部
				l1 = new ListNode(0);
			}
			if(l2 == null){
				l2 = new ListNode(0);
			}
			value = l1.val + l2.val;
			
			if(value+lflag >= 10 ){
				value = value -10;
				rflag = 1;
			}else{
				rflag = 0;
			}
			if(i == Math.max(len1, len2) && value+lflag == 0){
				break; //最后如果是0，空转一圈。不创建0 node
			}
			ListNode l = new ListNode(value+lflag);
			System.out.println(value+lflag);
			l5.next =l;l5 = l5.next;
			l1 = l1.next;l2 = l2.next;lflag = rflag;
		}
		return l6.next;

    }
	public ListNode addTwoNum(ListNode l1 ,ListNode l2){
//		比我处理的优雅好多。time 99 room 94 
//		1. 最后多一个进位可以不用放在循环里面，因为只有一个。
//		2. 利用% / 来判断商和余数，比我和10比大小优雅多了
//		3. 使用while循环则不需要计算length,
		ListNode head = new ListNode(0);
		ListNode p = l1,q = l2, curr = head;
		int carry = 0;
		while(p != null || q != null){
			int x = (p != null) ? p.val:0;
			int y = (q != null) ? q.val:0;
			int sum = carry + x +y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p!= null) p = p.next;
			if (q!= null) q = q.next;
			
		}
		if (carry > 0){
			curr.next = new ListNode(carry);
		}
		return head.next;
		
	}
	/* No.3
	 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	 * example:
	 * "abcabcbb" 3 
	 * "bbbbb" 1
	 * "pwwkew" 3 
	 */
//	O(n^3)暴力法，不优雅，而且超时。
	 public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null;} //构造函数
	}
