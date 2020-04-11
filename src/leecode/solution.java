package leecode;

import java.util.HashMap;
import java.util.Map;

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
	
	
	
}
