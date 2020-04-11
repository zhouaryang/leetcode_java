package leecode;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		solution solu =new solution();
		
		//No.1 两数之和
		int nums[] = {2,7,11,15};
		int target = 13;
		solu.twoSum(nums, target);
		System.out.println(solu.twoSum(nums, target)[1]);
	}

}
