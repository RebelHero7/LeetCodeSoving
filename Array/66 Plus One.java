
/*
第一种想法，没通过。
是将数组中的单个数变成一个真正的整数然后加一，再考虑是否
原因在于，只考虑了类似99加一，999加一，需要加一位。
没有考虑到类似89加一，只是改变前面就行。
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int n = 0;
        for(int num : digits){
            n = 10 * n + num;
        }
        n++;
        int len = digits.length;
        if(n % 10 == 0)
        {
            int[] nums = new int[len + 1];
            for(int i = nums.length - 1; i >= 0; i--){
                nums[i] = n % 10;
                 n = n / 10;
            }
            return nums;
        }
        else
        {
            digits[len - 1]++;
            return digits;
        } 
    }
}


/*
第二种想法，没通过。
将前一种改进了一下，还是没通过。
仔细思考一下发现，万一数组比较长会超过规定的范围，
一开始没想到，所以现在将这种想法摒弃了。
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int n = 0;
        for(int num : digits){
            n = 10 * n + num;
        }
        n++;
        int len = digits.length;
        if(n % (Math.pow(10, len)) == 0)
        {
            int[] nums = new int[len + 1];
            for(int i = len; i >= 0; i--){
                nums[i] = n % 10;
                 n = n / 10;
            }
            return nums;
        }
        else
        {
            for(int i = len - 1; i >= 0; i--){
                digits[i] = n % 10;
                n = n / 10;
            }
            return digits;
        } 
    }
}


/*
第三种想法，通过。
换了一个想法，
就是用i遍历数组，判断加一是否等于十，
如果是第一个元素，就跳出循环，创建一个新的数组，比原来的数组大一，再将新数组第一个元素上的值赋1返回。
如果i不是第一个元素，就将这个位置的值赋0。

不等于就将这个位置上的值加一再返回数组。
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n - 1; i >= 0; i--){
            if(digits[i] + 1 == 10)
            {
                if(i == 0) break;
                else digits[i] = 0;
            }
            else{
                digits[i]++;
                return digits;
            }
        } 
        int[] nums = new int[n + 1];
        nums[0] = 1;
        return nums;
    }
}

/*
第四种想法，通过。
只是前面一种的简化，性能并没有提升多少。
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n - 1; i >= 0; i--){
            if(digits < 9)
            {
                digits[i]++;
                return digits;
            }
			digits[i] = 0;
        } 
        int[] nums = new int[n + 1];
        nums[0] = 1;
        return nums;
    }
}
