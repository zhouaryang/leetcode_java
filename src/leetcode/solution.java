package leetcode;

import java.io.PushbackInputStream;
import java.sql.Time;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.stream.IIOByteBuffer;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.html.MinimalHTMLWriter;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.portable.ValueInputStream;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.Set;
import java.util.Stack;

public class solution {

	/*
	 * No.1: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	 * func3是高阶这个题，很难，头条面2 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 示例: 给定 nums =
	 * [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
	 */
	public int[] twoSum(int[] nums, int target) {

		// 暴力解法：
		/*
		 * int result[] = new int [2]; if(nums.length <=1){ return null; }
		 * for(int i=0 ; i < nums.length-1 ;i++){ for(int j = i ; j<nums.length
		 * ;j++){ if(nums[i] + nums[j] == target){ result[0]=i;result[1] =j;
		 * return result; } } }
		 */

		// 显然暴力O（n^2）,
		// 使用hashmap.
		Map<Integer, Integer> map = new HashMap<>();
		int result[] = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) { // 现在判断target-a[i]是否存在于map中
				// 存在则返回
				result[0] = map.get(target - nums[i]);
				result[1] = i;
				return result; // return new int[]{map.get(target-nums[i]),i};
			}
			map.put(nums[i], i);// 每次找不到就塞到map，值和下标
		}
		return null;
	}

	/*
	 * No.2: 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
	 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 示例： 输入：(2
	 * -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
	 */

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		// 已ACE，但是效率很低，时间7%，内存93%。 写的很烂，
		// 这个题关键点在于进位处理，有一个标志位记录是否进位，从低位到高位依次相加。
		// java中没有指针的概念，所以l5 =l6 就是引用同一个对象，此时再遍历谁都是ok的。但是需要用到next域，
		// 不能是null对象，所以最好使用赋值初始化之后的变量遍历，遍历使用next去链接第一个节点，最后返回next域
		ListNode l3 = l1, l4 = l2, l5 = new ListNode(0);
		ListNode l6 = l5;
		int len1 = 0, len2 = 0;
		int lflag = 0, rflag = 0, value = 0;// 进位表示1,value代表两数之和

		while (l3 != null) { //
			len1++;
			l3 = l3.next;
		}
		while (l4 != null) {
			len2++;
			l4 = l4.next;
		}
		for (int i = 0; i <= Math.max(len1, len2); i++) {
			if (l1 == null) { // 如果遍历到尾部
				l1 = new ListNode(0);
			}
			if (l2 == null) {
				l2 = new ListNode(0);
			}
			value = l1.val + l2.val;

			if (value + lflag >= 10) {
				value = value - 10;
				rflag = 1;
			} else {
				rflag = 0;
			}
			if (i == Math.max(len1, len2) && value + lflag == 0) {
				break; // 最后如果是0，空转一圈。不创建0 node
			}
			ListNode l = new ListNode(value + lflag);
			System.out.println(value + lflag);
			l5.next = l;
			l5 = l5.next;
			l1 = l1.next;
			l2 = l2.next;
			lflag = rflag;
		}
		return l6.next;

	}

	public ListNode addTwoNum(ListNode l1, ListNode l2) {
		// 比我处理的优雅好多。time 99 room 94
		// 1. 最后多一个进位可以不用放在循环里面，因为只有一个。
		// 2. 利用% / 来判断商和余数，比我和10比大小优雅多了
		// 3. 使用while循环则不需要计算length,
		ListNode head = new ListNode(0);
		ListNode p = l1, q = l2, curr = head;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;

		}
		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return head.next;

	}

	/*
	 * No.3 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 example: "abcabcbb" 3 "bbbbb" 1
	 * "pwwkew" 3
	 */
	// O(n^3)暴力法，不优雅，而且超时。
	public int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)
				if (allUnique(s, i, j))
					ans = Math.max(ans, j - i);
		return ans;
	}

	public boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if (set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}

	// 滑动窗口法：滑动窗口正好是数组/字符串问题中常用的抽象概念，[i,j),
	// 我们使用hashset做滑动窗口，查找快，期初[i,j)为0，然后j后移，判断s[j]是否在hashset中，如果不在继续移动j
	// 如果存在则此时[i,j)正好是当前最大子串，此时可以移动i,hashset只需剔除s[i]元素,j保持不动，一次遍历即可
	// time 29 root 5 感觉数据不很好啊 最后把 set.size()改成j-i 后变成了 time 76 room 5
	public int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<>();
		int ans = 0;
		int len = s.length();
		int i = 0, j = 0;
		while (i < len && j < len) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j));
				j++;
			} else {
				set.remove(s.charAt(i));
				i++;
			}
			ans = Math.max(ans, j - i);
		}
		return ans;
	}

	// bytedance 1
	public int findLoaction(char[] ch, String s) {

		Map<Character, Integer> map = new HashMap<>();
		for (char c : ch) { // 先全部放到map中
			map.put(c, 0);
		}
		int i = 0, j = 0;
		boolean flag = true;
		while (i < s.length() && j < s.length()) { //
			char key = s.charAt(j);

			if (map.containsKey(key) && map.get(key) == 0) { // 如果存在且为0，则移动窗口
				j++;
				map.put(key, 1);// 添加记录。
			} else {
				i++;
				for (Entry<Character, Integer> entry : map.entrySet()) {
					if (entry.getValue() == 0) {
						flag = false;
					}
					if (entry.getValue() == 1) {
						map.put(entry.getKey(), 0);
					}
				}
			}
			if (flag) {
				return i;
			}
		}

		return 0;
	}
	/* No.4 寻找两个正序数组的中位数
	 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
		请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
		你可以假设 nums1 和 nums2 不会同时为空。
	 */
	
	public double findMedianSortedArrays(int[] nums1,int[] nums2){
		
		//首先用粗暴法试试
		double result = 0.0;
		int nums3[] = new int[nums1.length+nums2.length];
		//合并两个数组
		/*for(int i = 0,j=0,k=0;i< nums1.length+nums2.length;i++){
			
			nums3[i] = nums1[j] < nums2[k] ? nums1[j++]:nums2[k++];
		}*/
		int i=0,j=0,k=0;
		while(i<nums1.length && j<nums2.length){
			if(nums1[i] < nums2[j]){
				nums3[k++] = nums1[i++];
			}else{
				nums3[k++] = nums2[j++];
			}
		}
		while(i < nums1.length){
			nums3[k++] = nums1[i++];
		}
		while(j < nums2.length){
			nums3[k++] = nums2[j++];
		}
		//合并完之后 找中位数
		k = nums3.length ;
		if(k%2 == 1 ){ //基数个
			result = nums3[k/2];
		}else{ //偶数个
			result = (nums3[k/2] + nums3[k/2 -1])/2.0;//注意此处是2.0不是2
		}		
		
		return result;
	}

	/*
	 * No.5 回文子串 给定一个字符串 s，找到 s中最长的回文子串。你可以假设 s 的最大长度为 1000。 输入: "babad" 输出:
	 * "bab" 注意: "aba" 也是一个有效答案。 示例 2： 输入: "cbbd" 输出: "bb"
	 */
	public String longestPalindrome(String s) {
		// 判断是否回文可以用StringBuilder 的 reverse计算。
		// time 超时
		int len = s.length();
		int i = 0;
		StringBuilder sb;
		String result = "";
		while (i < s.length()) {
			int j = i + 1;
			while (j <= s.length()) {
				sb = new StringBuilder(s.substring(i, j));
				if (sb.toString().equals(sb.reverse().toString())) { // 回文了
					result = result.length() > sb.toString().length() ? result : sb.toString();
				}
				j++;
			}
			i++;
		}
		return result;
	}

	public String longestPalindrome2(String s) {
		// 动态规划 保存已求解子问题的结果
		// dp[i][j]表示第i到第j的子串是否为回文子串
		// 初始化dp[i][j] =True;
		// 递推公式 dp[i][j]=( dp[i+1][j-1]&&arr[i]==arr[j]) (i-j != +-1)
		// 在构建dp数组的时候，要先遍历j，再遍历i，否则会出错。且构建dp数组的时候，顺便统计最长的回文长度
		// Time 33 room 15
		if (("").equals(s) || s.length() == 1) {
			return s;
		}
		int length = s.length();
		int start = 0;
		int max_len = 1;
		boolean[][] dp = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			dp[i][i] = true;
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < i; j++) { // 反正只用一半的数组
				// 根据递推公式可知，相邻1个元素不满足公式，因为&&后面是恒等，所以单独考虑。
				if (j + 1 == i && s.charAt(j) == s.charAt(i)) {
					dp[j][i] = true;
				} else {
					// 当这里i j 相差为2时
					dp[j][i] = dp[j + 1][i - 1] && s.charAt(i) == s.charAt(j);
				}
				if (dp[j][i] && i - j + 1 > max_len) {
					max_len = i - j + 1;// +1是为了截取字符串
					start = j;
				}

			}
		}
		String res = new String(s.toCharArray(), start, max_len);
		return res;

	}

	public String longestPalindrome3(String s) {
		// 这个有问题
		if ("".equals(s) || s.length() == 1) {
			return s;
		}
		int len = s.length();
		int maxLen = 1;
		int start = 0; // 记录最大子串起始位置
		boolean dp[][] = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			dp[i][i] = true;
		}

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				/*
				 * if(i == j){//分以下三种情况讨论，但是这里没有dp[i+1][i+1]的信息，下面第三个分支要使用，
				 * 可以在这里预分配或者在外面统一赋值,这个时候有个坑，中间子串没保存到值 dp[i][j] = true; } else
				 */if (j == i + 1) {
					dp[i][j] = s.charAt(i) == s.charAt(j);
				} else {
					dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
				}
				if (dp[i][j] && maxLen < j - i + 1) { // 在这里记录最大长度,如果i,j回文则记录
														// ,这里应该要用j-i+1,使用j-i
					maxLen = j - i + 1;
					start = i;
				}
			}

		}
		String res = s.substring(start, start + maxLen);
		System.out.println(start + " " + maxLen);
		return res;
	}

	/*
	 * No.6 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。 比如输入字符串为 "LEETCODEISHIRING" 行数为
	 * 3 时，排列如下： L C I R E T O E S I I G E D H N
	 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
	 * 请你实现这个将字符串进行指定行数变换的函数： string convert(string s, int numRows); 示例 1: 输入: s
	 * = "LEETCODEISHIRING", numRows = 3 输出: "LCIRETOESIIGEDHN" 示例 2: 输入: s =
	 * "LEETCODEISHIRING", numRows = 4 输出: "LDREOEIIECIHNTSG" 解释: L D R E O E I
	 * I E C I H N T S G
	 */
	// 关键点如何存 遍历一次找到元素最终位置。
	// 方法1.按行排序，这个+-1用的真精髓，确定了位置，还有使用字符串拼接的方式，非常方便。
	// time 76 room 8
	public String convert(String s, int numRows) {
		if (numRows == 1)
			return s;

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			rows.add(new StringBuilder());

		int curRow = 0;
		boolean goingDown = false;

		for (char c : s.toCharArray()) {
			rows.get(curRow).append(c);
			if (curRow == 0 || curRow == numRows - 1)
				goingDown = !goingDown;
			curRow += goingDown ? 1 : -1;
		}

		StringBuilder ret = new StringBuilder();
		for (StringBuilder row : rows)
			ret.append(row);
		return ret.toString();
	}

	// time 22 room 8,
	public String convert2(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		int length = s.length();
		List<String> list = new ArrayList<>();
		// list的长度取决与numRows,但是存在s短的情况
		int line = Math.min(length, numRows);
		for (int i = 0; i < line; i++) {
			list.add("");
		}
		boolean flag = false;
		// char[] ch = s.toCharArray(); //在这里也会多加耗时
		int currIndex = 0;// 这个表示指向list的位置，Z字行到两头之后转向
		for (char c : s.toCharArray()) {
			list.set(currIndex, list.get(currIndex) + c);// 这里耗时，对list的操作
			if (currIndex == 0 || currIndex == numRows - 1) {// 首尾换向,注意第一次会转向，巧妙使用初始值
				flag = !flag;
			}
			currIndex += flag ? 1 : -1;
		}
		String reString = "";
		for (String ss : list) {
			reString += ss;
		}
		return reString;
	}

	// 按行访问
	public String convert3(String s, int numRows) {

		if (numRows == 1)
			return s;
		StringBuilder ret = new StringBuilder();
		int n = s.length();
		int cycleLen = 2 * numRows - 2;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j + i < n; j += cycleLen) {
				ret.append(s.charAt(j + i));
				if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
					ret.append(s.charAt(j + cycleLen - i));
			}
		}

		return null;
	}

	/*
	 * No.7 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 In:132 Out:321 In:-123 Out:-321
	 * In:120 Out:21
	 */
	public int reverse(int x) {
		// 实现比较烂 time 23 room 5,使用了stringBuffer做反转，应用了其append方法。
		// 位数，符号，末尾0是三个关键点
		int len = 1, val = x;
		while (x / 10 != 0) {
			x = x / 10;
			len++;
		}
		boolean flag = val > 0 ? true : false;
		if (!flag)
			val = -val;
		String s = Integer.toString(val);
		StringBuffer reString = new StringBuffer();
		for (int i = s.length() - 1; i >= 0; i--)
			reString.append(s.charAt(i));
		try {
			int result = Integer.parseInt(reString.toString());
			result = flag ? result : -1 * result;
			return result;
		} catch (NumberFormatException e) { // 这里int溢出之后返回0
			return 0;
		}

	}

	public int reverse2(int x) {
		// 用hashmap试试，K-V存 位数，值，快速查找
		// time 15 room 5 感觉还不如上个
		Map<Integer, Integer> map = new HashMap<>();
		int i = 1; // 从1开始，去除最末尾
		int result = 0;// 取最末尾
		while (x / 10 != 0) {
			map.put(i, x % 10);
			i++;
			x = x / 10;
		}
		map.put(i, x % 10); // 最高位加进来

		for (Integer key : map.keySet()) {

			if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE) {
				return 0;
			}
			result += map.get(key) * Math.pow(10, i - (key));
			/*
			 * try{ result += map.get(key)*Math.pow(10, i-(key));
			 * }catch(NumberFormatException e){ return 0; }
			 */
		}
		// 恶心，测试边界点
		System.out.println("int max :" + Integer.MAX_VALUE + " int min:" + Integer.MIN_VALUE);
		return result;

	}

	public int reverse3(int x) {
		// time 100 room 5 因为用了long,所以空间大了
		long ans = 0; // 判断int溢出用，如果是int，判断不了是否溢出

		while (x / 10 != 0 || x % 10 != 0) {
			ans = ans * 10 + (x % 10);
			x /= 10;
		}
		if (ans >= Integer.MAX_VALUE || ans <= Integer.MIN_VALUE) {
			return 0;
		}
		return (int) ans;
	}

	/*
	 * No.8 字符串转整数 atoi 状态机：
	 */// tiem : room:
	public int myAtoi(String str) {
		// 遍历一遍先把数字和负号挑出来
		if (str == null || ("").equals(str) || str.equals("-") || str.equals("+")) {
			return 0;
		}
		StringBuilder sb = new StringBuilder();
		char c;
		int num = 0;
		boolean firstChar = true;
		boolean lastChar = true;

		for (int i = 0; i < str.length() && lastChar; i++) {
			c = str.charAt(i);
			num = (int) c - 48;
			if (firstChar) {
				if (c == ' ')// 这种丢弃
					continue;
				if (!(c == '+' || c == '-' || (num >= 0 && num < 10))) // 这些是不允许在前面出现的，
					return 0;
			}
			if (c == '+' || c == '-' || (num >= 0 && num < 10)) {
				sb.append(c);
				lastChar = true;
				firstChar = false;
			} else {
				lastChar = false;
			}

		}
		try {
			if (Long.parseLong(sb.toString()) > Integer.MAX_VALUE) { // 这里加入string转long异常时逻辑错误，应该输出maxint,而不是0.
				return Integer.MAX_VALUE;
			} else if (Long.parseLong(sb.toString()) < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
			return Integer.parseInt(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		/*
		 * if(sb.toString().trim().isEmpty()) return 0;
		 * if(Long.parseLong(sb.toString())>Integer.MAX_VALUE ){ return
		 * Integer.MAX_VALUE; }else if(Long.parseLong(sb.toString())<
		 * Integer.MIN_VALUE){ return Integer.MIN_VALUE; } return
		 * Integer.parseInt(sb.toString());
		 */
	}

	/*
	 * No.9 判断一个整数是否是回文数，回文数是指正序（从左到右）和倒序读都是一样的数 例如：121 true ; -121 false 10
	 * false 不要转为String来处理
	 */
	public Boolean isPalindrome(int x) {
		// time 26 room 5 感觉还是挺
		if (x < 0) {
			return false;
		}
		int val = x, y = 0;
		while (val / 10 != 0 || val % 10 != 0) {
			y = y * 10 + val % 10;
			val /= 10;
		}
		/*
		 * if(x != y){ return false; } return true;
		 */
		return x == y;
	}

	// 转String 试试
	// 其中StringBuilder 提供了多种操作方法，append reverse等，最后通过toString转string
	// time 9 room 5
	public Boolean isPalindrome2(int x) {
		StringBuilder s1 = new StringBuilder(x + "");
		return s1.reverse().toString().equals(x + "");
	}

	/*
	 * No 10 正则表达式匹配
	 * 
	 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 '.' 匹配任意单个字符 '*'
	 * 匹配零个或多个前面的那一个元素 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 s 可能为空，且只包含从 a-z 的小写字母。 p
	 * 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
	 */
	public boolean isMatch(String s, String p) {

		return true;
	}

	/*
	 * No.11
	 * 
	 * @desc:给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线
	 * i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 */
	public int maxArea(int[] height) {
		if (height.length < 2)// 0 1 非法
			return 0;
		// 暴力解法先来探探路 time 13 room 40 一次ace
		int maxArea = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				maxArea = Math.max(Math.min(height[i], height[j]) * (j - i), maxArea);
			}
		}
		return maxArea;
	}

	// 双指针法：time O(n) room O(1) time:73 room:57 (4ms 39.7MB)
	public int maxArea2(int[] height) {
		int l = 0, r = height.length - 1;
		int ans = 0;
		while (l < r) {
			int area = Math.min(height[l], height[r]) * (r - l);
			ans = Math.max(ans, area);
			if (height[l] <= height[r]) { // 你要找最大值，当然每次保留大的，移动小的那个指针，
				++l;
			} else {
				--r;
			}
		}
		return ans;
	}

	/*
	 * No .12 整数转roma数字 I 1;V 5 ;X 10; L 50; C 100; D 500; M 1000
	 */
	public String intToRoman(int num) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "I");
		map.put(5, "V");
		map.put(10, "X");
		map.put(50, "L");
		map.put(100, "C");
		map.put(500, "D");
		map.put(1000, "M");
		return "";
	}

	/*
	 * No.13 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。 字符 数值 I 1;V 5 ;X 10; L 50; C 100;
	 * D 500; M 1000 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27
	 * 写做  XXVII, 即为 XX + V + II 。 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4
	 * 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9
	 * 表示为 IX。这个特殊的规则只适用于以下六种情况： I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
	 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。  C 可以放在 D (500) 和 M (1000)
	 * 的左边，来表示 400 和 900。 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
	 */
	public int romanToInt(String s) {
		// so easy time 99.97 room 5.56
		int sum = 0, value = 0;
		char last = 'a';
		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);
			switch (c) {
			case 'I':
				value = 1;
				break;
			case 'V':
				if (last == 'I')
					value = 3;
				else
					value = 5;
				break;
			case 'X':
				if (last == 'I')
					value = 8;
				else
					value = 10;
				break;
			case 'L':
				if (last == 'X')
					value = 30;
				else
					value = 50;
				break;
			case 'C':
				if (last == 'X')
					value = 80;
				else
					value = 100;
				break;
			case 'D':
				if (last == 'C')
					value = 300;
				else
					value = 500;
				break;
			case 'M':
				if (last == 'C')
					value = 800;
				else
					value = 1000;
				break;
			default:
				break;
			}
			sum += value;
			last = c;
		}
		return sum;
	}

	/*
	 * No.14 编写一个函数来查找字符串数组中的最长公共前缀。 如果不存在公共前缀，返回空字符串 ""。 示例 1: 输入:
	 * ["flower","flow","flight"] 输出: "fl" 示例 2: 输入: ["dog","racecar","car"] 输出:
	 * "" 解释: 输入不存在公共前缀。 例如 ["dog","racecar","car"]
	 */
	// time 5 room 5 ,so low
	public String longestCommonPrefix(String[] strs) {
		// 不要一个字符一个字符去考虑，应该整体考虑
		// 令子串初始值为第一个字符串，然后依次找公共子串，找不到则返回空。
		if (strs.length == 0)
			return "";
		String subStr = strs[0];
		// 现在挨个找公共子串，类似一趟冒泡，最终找到的则为公共子串
		for (int i = 1; i < strs.length; i++) {
			subStr = pubString(subStr, strs[i]);
		}
		return subStr;
	}

	public String pubString(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return null;
		}
		String ans = "";
		for (int i = 0; i < s1.length() && i < s2.length(); i++) {
			if (s1.charAt(i) == s2.charAt(i))
				ans += s1.charAt(i);
			else {
				break;
			}
		}
		if (ans.equals("")) {
			return null;
		}
		return ans;
	}

	//
	public String longestCommonPrefix2(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";
			}
		return prefix;
	}
	

	/*
	 * No 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
		注意：答案中不可以包含重复的三元组。
	 * 
	 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
	 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
		满足要求的三元组集合为：
		[
		  [-1, 0, 1],
		  [-1, -1, 2]
		]
	 */ //感觉像是使用深度遍历算法。
	public List<List<Integer>> threeSum(int []nums){
		List<List<Integer>> list = new ArrayList<>();
		int length = nums.length;
		int sum  = 0 ;
		for(int i =0 ; i < length-2 ; i++){
			for(int j = i+1 ; j< length-1 ; j++){
				for(int k = j+1 ; k< length ; k++){
					if(nums[i] + nums[j] + nums[k] == 0){
						List<Integer> li = new ArrayList<>();
						li.add(nums[i]);li.add(nums[j]);li.add(nums[k]);
						list.add(li);
					}
				}
			}
		}
		return list;
	}
	public List<List<Integer>> threeSum2(int []nums){
		HashMap<Integer, Integer> map = new HashMap<>();
		//把数据先放到hashmap中
		for(int i  =0;i < nums.length; i++){
			map.put(nums[i], i);
		}
		
		return null;
	}
	/*
	 * No.20 括号匹配 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * 
	 * 有效字符串需满足：
	 * 
	 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
	 * 
	 */ // Time 84 room 5 额外使用了一个栈
	public boolean isValid2(String s) {
		// 解法，左括号入栈，右括号判断匹配则出战，不匹配失败，最后看栈中是否有元素。
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
				stack.push(s.charAt(i));
			} else if (stack.size() == 0) {
				return false;
			} else if (stack.peek() == '(') {
				if (')' != s.charAt(i))
					return false;
				stack.pop();
			} else if (stack.peek() == '{') {
				if ('}' != s.charAt(i))
					return false;
				stack.pop();
			} else if (stack.peek() == '[') {
				if (']' != s.charAt(i))
					return false;
				stack.pop();
			}
		}
		if (stack.size() == 0) {
			return true;
		}
		return false;
	}

	// time 5 room 5 ,递归，很耗时的
	public boolean isValid(String s) {
		if (s.contains("()") || s.contains("{}") || s.contains("[]")) {
			return isValid(s.replace("()", "").replace("{}", "").replace("[]", ""));
		} else {
			return "".equals(s);
		}
	}

	/*
	 * No.21 合并链表 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。  示例：
	 * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
	 */
	// time 62 room 48 还行吧，注意使用curr指向head，然后用作遍历链表
	// 法一：迭代
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		// 申请一个新链表头结点：使用指针curr指向head
		ListNode head = new ListNode();
		ListNode curr = head;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				curr.next = l2;
				curr = curr.next;
				l2 = l2.next;
				continue;
			} else if (l2 == null) {
				curr.next = l1;
				curr = curr.next;
				l1 = l1.next;
				continue;
			}
			if (l1.val < l2.val) {
				curr.next = l1;
				curr = curr.next;
				l1 = l1.next;
			} else {
				curr.next = l2;
				curr = curr.next;
				l2 = l2.next;
			}
		}
		return head.next;
	}

	// 法二：递归
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists2(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists2(l1, l2.next);
			return l2;
		}
	}

	// 给定一个数组，给定一个target，给定结果数组的个数k，求这个数组的子数组个数为k且子数组所有元素和为target所有子数组
	// 头条面试题2 很难，使用dfs；
	int target = 8;

	public void getSubArray() {
		int a[] = { 10, 1, 2, 7, 6, 1, 5 };
		Arrays.sort(a);
		// boolean flag[] = new boolean[9];
		// func1(a,flag,0);
		// List<Stack<Integer>> list = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		func2(a, 0, 8, stack);
	}

	public boolean func2(int a[], int curr, int target, Stack<Integer> stack) {

		if (target < 0) { // 这种情况可以停止遍历
			return false;
		}
		if (target == 0) {
			// stack.add(a[curr]);
			// list.add(stack);
			printStack(stack);
			return true;
		}
		for (int i = curr; i < a.length; i++) {
			if (i > curr && a[i] == a[i - 1])
				continue;
			stack.add(a[i]);
			func2(a, i + 1, target - a[i], stack);
			stack.pop();
		}
		return false;
	}

	public void func1(int a[], boolean[] flag, int j) {
		if (j == a.length) {
			Stack<Integer> tmp = new Stack<>();
			for (int i = 0; i < a.length; i++) {
				if (flag[i])
					tmp.add(a[i]);
			}
			int sum = 0;
			if (tmp.size() == 3) {
				for (int val : tmp) {
					sum += val;
				}
				if (sum == target) {
					printStack(tmp);
				}
			}
		} else {
			flag[j] = true;
			func1(a, flag, j + 1);
			flag[j] = false;
			func1(a, flag, j + 1);
		}
	}

	public void printStack(Stack<Integer> tmp) {
		for (int i : tmp) {
			System.out.println(i);
		}
		System.out.println("--------------");
	}

	public void func3() {
		List<List<Integer>> list = combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8);

		for (List<Integer> li : list) {
			for (int i : li) {
				System.out.println(i);
			}
			System.out.println("-------------");
		}

	}

	public List<List<Integer>> combinationSum2(int[] cand, int target) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		Arrays.sort(cand);
		dfs(cand, 0, target, res, list);
		return res;
	}

	public void dfs(int[] cand, int start, int target, List<List<Integer>> res, List<Integer> list) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		if (target < 0)
			return;

		for (int i = start; i < cand.length; i++) {
			if (i > start && cand[i] == cand[i - 1])
				continue;
			list.add(list.size(), cand[i]);
			dfs(cand, i + 1, target - cand[i], res, list);
			list.remove(list.size() - 1);
		}
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int x) {
		val = x;
		next = null;
	} // 构造函数
}
