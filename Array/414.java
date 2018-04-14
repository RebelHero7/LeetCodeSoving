
/*
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

问题描述：题目很简单，给你一个非空数组，返回第三大的数字，如果没有那么返回最大的数字。
*/

/*
	第一种，未通过。
	
	一开始看见这个题目想着，很简单吗，
	
	不就是假设有三个数，然后遍历数组，找到了第三个最大的数字就返回，没找到就返回最大的。
	
	于是有了下面这种没通过的方法，很显然这个方法没有考虑到如果第三个数是Integer.MIN_VALUE的情况。
	
	想了一会决定放弃这种想法。
*/
class Solution {
    public int thirdMax(int[] nums) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE + 1;
        int max3 = Integer.MIN_VALUE + 2;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= max1){
                max1 = nums[i];
            }else if(nums[i] >= max2){
                max2 = nums[i];
            }else if(nums[i] > max3){
                max3 = nums[i];
            }
        }
        
        return max3 > Integer.MIN_VALUE + 2 ? max3 : max1;
    }
}
/*
	第二种：未通过。
	
	于是想到用Arrays.sort方法先将数组排序，然后从后向前遍历。
	
	然后大体思绪和前面一种差不多，但是和前面一个样，第三个数是Integer.MIN_VALUE的时候一样凉，￣へ￣。
	
	又放弃了。
	
*/
class Solution {
    public int thirdMax(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        Arrays.sort(nums);
        int i = nums.length - 1;
        int max1 = nums[i];
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] < max1 && nums[i] > max2){
                max2 = nums[i];
            } else if(nums[i] < max2 && nums[i] > max3){
                max3 = nums[i];
            }
        }
        return max3 > Integer.MIN_VALUE + 1 ? max3 : max1;
    }
}
/*
	第三种：通过。
	
	将第二种改进了一下。
	
	不设有第三个数了，就将排好序的数组的最后一个数字赋给max1。
	
	从后遍历数组，如果小于max1再判断是否大于等于max2，是的话就赋值给max2，继续遍历。
	
	找到小于max2的数就肯定是第三大的数了，就直接返回。
	
	如果循环结束还没有返回说明没有第三大的数，那么就返回max1。
*/
class Solution {
    public int thirdMax(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        Arrays.sort(nums);
        int i = nums.length - 1;
        int max1 = nums[i];
        int max2 = Integer.MIN_VALUE;
        while(i > 0){
            i--;
            if(nums[i] < max1){
                if(nums[i] >= max2){
                    max2 = nums[i];
                } else {
                    return nums[i];
                }      
            }
            
        }
        return max1;
	}
}

/*
	第四种，别人的，通过。
	
	这种办法很好，没有使用int的min和max，使用了integer对象，就可以让它们指向null，也就没有我前两种想法的悲惨结局，(⊙︿⊙)。
	
	使用foreach遍历数组，如果这个数字等于三个最大数中的任何一个就continue。
	
	如果max1指向null，或者这个数字大于max1就将max3指向max2，max2指向max1，max1再指向这个新的数字。
	
	max2和max3一样。
	
	最后判断max3是否指向null，是的话就返回max1，不是就返回max3.
	
*/
public int thirdMax(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }
}
