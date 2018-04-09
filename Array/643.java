/*
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. 
And you need to output the maximum average value.

问题描述：给你一个数组，以及一个K，找出这个数组中连续的K个值之和的平均数最大。

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
*/


/*
	第一种：通过。
	先创建一个最大数为Integer.MIN_VALUE，以及记录数组中k个连续值之和的s，
	遍历数组，从0到nums.length - k + 1，
	然后再在遍历数组的循环中创建一个大小为k的循环，
	然后相加数组中的k个数，最后判断max是否小于s,小于将s赋给max
	最后再将s设为0.
	最后返回平均数。
	
	这个思路是这样的，但是很明显使用了双重循环，时间复杂度为N的平方。
	所以不是不是很好。
*/

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int s = 0;
        for(int i = 0; i < nums.length - k + 1; i++){
            for(int j = 0; j < k; j++){
                s += nums[j + i];
            }
            if(max < s){
                max = s;
            }
            s = 0;
        }
        return (double)max / k;
    }
}

/*
	第二种，别人的，通过。
	
	这个是将数组最前面的k个数相加为sum，然后让max等于sum，
	再从k开始遍历数组，这时sum将减去i-k位置上的元素，再加上i位置上的元素，
	最后比较max和sum哪个更大，再赋给max。
	也就是说，sum始终等于k个数之和，但是sum在不停的移动。
	
*/
public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        long max = sum;
        
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        
        return max / 1.0 / k;
    }
}