/*
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

題目解釋：就是将数组中的元素向后移动k步。

但是要注意一點，要把它當成是一個就是循環數組。
*/

/*
第一種想法：通過

先判斷這個k的值是否是0或者和數組大小一樣大，如果是就return ;

不是創建一個新的數組保存後面幾個數，
再將數組前面的向后移動，
最後再將保存再另外一個數組的數放回去。
*/
class Solution {
    public void rotate(int[] nums, int k) {
        if(k == 0 || k % nums.length == 0){
            return ;
        }
        int len = nums.length;
        int j = k % len;
        int[] n = new int[j];
        int m = 0;
        for(int i = len - j; i < len; i++){
            n[m++] = nums[i];
        }
        for(int i = len - j - 1; i >= 0; i--){
            nums[i + j] = nums[i];
        }
        for(int i = 0; i < j; i++){
            nums[i] = n[i];
        }
    }
}
/*
第二种解法:通過，不是我想的，但是覺得挺精妙的。

就是先將數組全部翻轉，再翻轉前面k個，最後翻轉後面的。
之所以覺得精妙是因爲使用了递归，分治了這個問題。
*/
public class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}