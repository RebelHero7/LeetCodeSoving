
/*先问题描述：

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.


就是给你一个数组，返回数组中大于数组长度一半的数。*/



//1.一开始使用双重foreach循环，但是运行超时了。

class Solution {
    public int majorityElement(int[] nums) {
        
        for(int num : nums){
            int n = 0;
            for(int elem : nums){
                if(elem == num){
                    n++;
                }
            }
            if(n > nums.length/2){
                return num;
            }
        }
        return -1;
    }
}

/*2.于是换了将数组进行先排序，
先把数组第一个数赋给n，
然后再使用foreach遍历有序数组，假设这个数不等于n，就将n的值换成这个数，再把那个计数的max置零。
然后出判断的时候将max++，最后判断一下max是否大于数组的一半。记住这些判断的顺序很重要。*/
class Solution {
    public int majorityElement(int[] nums) {
       Arrays.sort(nums);
        int max = 0;
        int n = nums[0];
        for(int num : nums){ 
            if(num != n){  
                n = num;
                max = 0;
            }
            max++;
            if(max > nums.length/2){
                return num;
            }
        }
        return nums[0];
    }
}

/*3.思考了一下是否可以用set。
应该可以，但是好像有点麻烦，就没有弄。*/

/*4.对于第二个方法，
有一个参考答案简直了，只有两步，也是先排序，
再直接返回数组长度的一半的那个对应的那个数。
这想法，绝了。我的简直就是画蛇添足。*/

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

/*5.还有一个参考方法也很简单，那就是先有一个中间值candidate记录出现次数为length/2的值，再在循环中判断计数的是否是等于0，如果等于0，那就将它赋给candidate。
再判断被遍历的这个值是否等于candidate，如果等于就将计数的加一，不等于就减一。最后返回candidate。

思路：一开始刚看见的时候不理解，认为数组是无序的，你每次遍历对应的数不一样，如何记录出现次数最多的。
但是仔细思考一下发现，微妙的点就在于count += (num == candidate) ? 1 : -1;
当你遇见一个和candidate不一样的数的时候，它是占了数组中的一个位置的，那么数组长度的一半就会小一点。
当count减到0的时候，说明这个数的个数已经不是数组的一半长，比如一个数组，7,7,5,5,7，count的数是1,2,1,0,1的。

所以可以理解了。*/
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}

//还有其余的精妙的想法如hashmap之类的，我就不写不分析了。