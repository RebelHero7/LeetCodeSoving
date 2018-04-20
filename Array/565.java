

/*
	A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, 
	where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

	Suppose the first element in S starts with the selection of element A[i] of index = i, 
	the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.
	
	Example 1:
	Input: A = [5,4,0,3,1,6,2]
	Output: 4
	Explanation: 
	A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

	One of the longest S[K]:
	S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
	
	
	问题描述：给你一个长度为n的数组A，里面的数都是从0到n-1的数，没有重复的，
	假设有一个数组和A一样长的数组S,S中的每个数，都是S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }的长度，
	返回最长的。
*/
/*
	第一种：超时。
	
	其实知道会超时，毕竟使用了双重循环。
	
	创一个ArrayList，遍历数组，
	
	然后使用while循环判断元素是否在list中，在就跳出循环，
	
	出while循环后，判断list的size和max哪个更大再赋给max。
*/
class Solution {
    public int arrayNesting(int[] nums) {
        List<Integer> n = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            int k = nums[i];
            while(!n.contains(k)){
                n.add(k);
                k = nums[k];
            }
            max = Math.max(max, n.size());
            n.clear();
        }
        return max;
    }
}
/*
	第二种：通过。
	
	看了一会发现，S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}，
	
	如果看成循环链表，说明S中0,5,6,2，都会是一样的长度。
	
	那么就可以创建一个和A一样长的boolean数组，遍历数组，先判断nums[i]是否访问过，
	
	没有就使用一个dowhile循环，直到等于nums[i]，同时将访问过的设为true以及一个记录长度的n。
	
*/
class Solution {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(!visited[i]){
                int s = nums[i];
                int n = 0;
                do{
                    s = nums[s];
                    n++;
                    visited[s] = true;
                }
                while (s != nums[i]);
                max = Math.max(max, n);
            }
        }
        return max;
    }
}
/*
	第三种：别人的，通过。
	
	和第二种一样，但是没有使用额外的空间，只是将访问过的设为Integer.MAX_VALUE。
	
	其实也有想过，但是觉得和第二种区别不大就没继续改进，所以就贴了别人的。
*/

public class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}