
//先是问题描述


/*Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]


翻译过来就是，比如数组应该是按顺序的0,2,3...7，8，但是少了5和6.要求输出5和6.*/




/*第一个想法(未成功)：
一开始没有想清楚题目，
以为是找出中间少了的就行，
但是后面提交提示，如果数组是1,1,那么应该输出2，
然后才惊觉这样好像确实不行*/

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = 0;
        for(int num : nums){
            while(num - n > 1){
                n++;
                list.add(n);
            }
            n = num;
        }
        return list;
}

/*
第二个想法（未实施）：

想到创建一个和nums一样大小的有序数组k，
然后双重循环，
k在外层，如果nums中没有应该有的数，就将其添加到list中。

没有做是因为，第一是题目要求不用额外的空间。
第二个是考虑到双重循环时间复杂度是平方级别的。
就作罢了。
*/


/*
第三个想法（成功，但效率很低，只击败了十二点多）：

是想到先将数组排好序，遍历数组。
创建一个n,记录前一个数。
创建一个i,记录重复的数，也就是会少几个数。

如果遍历的时候发现后一个数减去前一个数大于1，就代表中间断代了。
这时就添加中间缺少的，再将i自减。
最后再判断i如果大于0就继续添加没有的。
*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = 0;
        int i = 0;
        for(int num : nums){
            if(num == n){
                i++;
            }
            while(num - n > 1){
                n++;
                list.add(n);
                i--;
            }
            n = num;
        }
        while(i > 0){
            n++;
            list.add(n);
            i--;
        }
        return list;
}

/*
第四个（百度的，正负号标记法）：
*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int n = abs(nums[i]) - 1;//本来想用Math.abs的，但是好像不让编译通过。
            nums[n] = -abs(nums[n]);
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                list.add(i+1);
            }
        }
        return list;
    }
    
    public int abs(int i){ 
        if(i < 0) return -i;
        return i;
    }
}
