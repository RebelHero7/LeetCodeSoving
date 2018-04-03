
/*
Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

题目的意思就是如果数组有两个元素相等，且位置相差不超过k的，那就返回true。没有就返回false；
*/


/*
第一种想法，超时。
一般一开始想都会想的简单一点，然后慢慢的改进
所以当然想到用双重循环。但是可以运行就是会超时。
遂改变想法。
*/
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for(int i = 0; i < nums.length; i++){
            for(int j = 1; j <= k; j++){
                if(i + j >= nums.length)
                {
                    break;
                }
                if(nums[i] == nums[j + i]){
                    return true;
                }
            }
        }
        return false;
    }
}

/*
第二种想法，想到了用set，毕竟set要求元素独一无二。
想着先遍历数组，添加元素进HashSet中，遇到没添加成功的就返回true。
但是对于要相隔K个之内的元素，觉得还是会用到双重循环，就放弃了。


解法：通过，其他人的想法。
后面想了好一会都没想到好的办法，
就去discuss去看，
发现了一种想法很好，
和我之前想的也有点相近只是人家简单好多o(╥﹏╥)o。
*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);//这个代表如果i都到k了，说明数组之前k范围没有相等的元素，就可以将前面的和i相隔k的元素删除了。
            if(!set.add(nums[i])) return true;
        }
        return false;
    }
}