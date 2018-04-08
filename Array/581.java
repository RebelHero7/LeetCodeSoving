

/*
问题描述：Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

给你一个数组，找出使数组无序的最小数组长度。

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
*/

/*
	思路：无序的最小数组，在开始和结束的地方肯定是nums[i] < nums[i++]
	
*/

/*
	第一种：未通过。
	min表示无序数组开始的地方，max表示无序数组结束的地方
	就是从头遍历数组，如果下一个元素与现在的元素相等，且min不是-1，那么就使max = i + 1。
	如果nums[i] > nums[i+1]，且min等于-1，就说明是无序数组开始的地方。
	如果max <= i 说明是无序数组中，或者是无序数组结束的地方。
	
	但是这样就会错在，如[1,3,2,3,3]会输出4，而实际应该是2.
	想了半天发现实在是改进不了，放弃这种想法。
*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int min = -1;
        int max = -1;
        for(int i = 0; i < len - 1; i++){
            if(nums[i] == nums[i + 1] && min != -1){
                max = i + 1;
                continue;
            }
            if(nums[i] > nums[i+1]){
                if(min == -1){
                    min = i;
                }
                if(max <= i)
                {
                    max = i + 1;
                }
                
            }
        }
        if(max == -1 && min == -1){
            return 0;
        }
        return max - min + 1;
    }
}


/*
	第二种：通过。
	
	思路是，新建一个和原来一样的数组，然后通过将原数组排序，遍历这两个数组，
	遇见第一个不相等的元素就把i赋给min。
	遇见max < i的就将i赋给max。
	最后判断是否相减大于0，大于就返回相减的数加一的值，等于或小于就返回0.
*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int[] n = new int[len];
        for(int i = 0; i < len; i++){
            n[i] = nums[i];
        }
        Arrays.sort(nums);
        int min = -1;
        int max = -1;
        for(int i = 0; i < len; i++){
            if(n[i] != nums[i]){
                if(min == -1)
                    min = i;
                if(max < i)
                    max = i;
            }
        }
        
        return (max - min >= 0 ? max - min + 1 : 0);
    }
}
/*
	第三种：别人的想法，通过。
	其实就是我的第二种的简化版本，使用clone。
*/

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }
}

/*
	第四种：通过。
	使用双重循环，第二种循环通过从i的后面的元素开始遍历，遇见小于nums[i]的元素，它的位置是j
	就判断这个j是否比之前记录的r（即无序数组的结束）大，以及判断i是否比之前记录的l（即无序数组的开始）小。
	最后判断是否r - l < 0，是的话就说明这个数组是一直有序的，不是就返回r - l + 1。
*/
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}
/*
	第五种：别人的想法，通过。
	很精妙的想法，就是将nums这个数组使用坐标画出来，如果一直是有序的数组那么应该总体呈上升趋势，
	那么无序数组的开始是，前面一段有序的最高点，通过这个最高点画一条平行于x轴的虚线，虚线与这个实线相交的第二个点，就是无序数组结束的地方。
	
*/
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}
