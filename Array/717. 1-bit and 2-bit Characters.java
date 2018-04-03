/*
问题描述：

We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).

Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.
*/

/*
题目大意就是说：
数组由one bit 0和two bit 10或11组成，判断最后一位是否是由one bit组成的，是就返回true，不是返回false
*/

/*
想法一,通过。

遍历数组，先判断是否是最后一位，是的话就break；
不是的话，遇到1的就加2，遇到0就加一。

最后判断i是否大于数组长度，大于说明最后的是two bit
*/

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        int i;
        for(i = 0; i < len; ){
            if(i == len - 1){
                break;
            }
            if(bits[i] == 0){
                i++;
            } else{
                i += 2;
            }
        }
        if(i >= len){
            return false;
        }
        return true;
    }
}

/*
简化版想法一
*/

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            i += bits[i] + 1;
        }
        return i == bits.length - 1;
    }
}

/*
想法二，solution给的

two bit必定是2位，
one bit必定是1位，

那么从倒数第二个数倒序遍历数组，遇见1就自减，遇见0或者 i小于0就跳出循环。
再用数组的长度减去已经遍历过的，也就是等于还没有遍历访问的元素，判断如果%2等于0，就说明那些都是two bit或者是偶数个one bit。
*/

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = bits.length - 2;
        while (i >= 0 && bits[i] > 0) i--;
        return (bits.length - i) % 2 == 0;
    }
}