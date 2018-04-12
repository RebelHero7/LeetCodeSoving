/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums


问题描述：给一个非空非负整数数组，找到出现最多次数的数字，然后在这些数字中，找到一个长度最小的。返回它的长度。
*/

/*
	第一种：错误。
	总结：OK，英语不好，题目理解错误。
	以为是找出最大的重复数字的长度。
	想了半天决定用hashMap做。
	然后华丽丽的错了。
	又想了半天觉得自己没错，就去看别人的解答才发现。。。凉了。
	
	但还是记录一下，告诉自己，审题！！审题！！审题！！重要的事情说三遍。
*/
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> list = new HashMap<>();
        int max = 0;
        int end = -1;
        int start = -1;
        for(int i = 0; i < nums.length; i++){
            if(list.containsKey(nums[i])){        	
            	if(nums[i] == max){
            		end = i;
                }else if(max < nums[i]){
                    max = nums[i];
                    start = list.get(nums[i]);
                    end = i;
                }
            } else{
                list.put(nums[i], i);
            }
        }
        
        return end - start > 0 ? end - start + 1 : 0;
    }
}

/*
	第二种，别人的，通过。
	
	经过前面的伤害，直接看了别人的解答。
	
	这个解法的意思是，创建一个hashmap，key是nums[i]数。
	value是一个数组，这个数组有三个数，
	第一个数记录这个数出现的次数，第二个记录这个数字第一次出现的位置，第三个记录这个数字最后一次出现的位置。
	
	最后遍历hashmap，查询出现的次数，如果出现次数相同，找出长度最小的。
	
	
*/

 public int findShortestSubArray(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
           if (!map.containsKey(nums[i])){
               map.put(nums[i], new int[]{1, i, i});  // the first element in array is degree, second is first index of this key, third is last index of this key
           } else {
               int[] temp = map.get(nums[i]);
               temp[0]++;
               temp[2] = i;
           }
        }
        int degree = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
        for (int[] value : map.values()){
            if (value[0] > degree){
                degree = value[0];
                res = value[2] - value[1] + 1;
            } else if (value[0] == degree){
                res = Math.min( value[2] - value[1] + 1, res);
            } 
        }
        return res;
    }