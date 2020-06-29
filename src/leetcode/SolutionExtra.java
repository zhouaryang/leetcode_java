package leetcode;

public class SolutionExtra {
	
	public int getMax(int []value){
		
		if(value.length == 0){
			return 0;
		}
		int sum = 0;
		for(int i = 0; i< value.length ; i++){
			sum = Math.max(sum, value[i]);
		}
		return sum;
	}
}
