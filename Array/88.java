

/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.


就是给你两个有序数组，把nums2和nums1按大小合并到nums1中，
其中m是nums1有序数组的大小，n是nums2的大小，
其中，nums1的大小足够大 大于或等于m+n。
*/


/*
第一种想法：通过。

一看见题目我就在想，这可不就是归并排序吗。
就用了归并排序。
本来只是想在nums1上操作的，但是想到nums2的元素插入到nums1中需要将nums1的元素向后移动，这样的话时间复杂度好像有点高。
于是创建一个nums3将排序的放到其中，再将它赋给nums1；
*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3 = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < m && j < n){
            if(nums1[i] > nums2[j])
            {
                nums3[k++] = nums2[j++];
                continue;
            }
               
            if(nums1[i] <= nums2[j])
            {
                nums3[k++] = nums1[i++];
                continue;
            }
        }
        while(i < m){
            nums3[k++] = nums1[i++];
        }
        while(j < n){
            nums3[k++] = nums2[j++];
        }
        for(k = 0; k < m + n; k++){
            nums1[k] = nums3[k];
        }
    }
}

/*
第二种解法，别人的，通过。

上面一种我一看通过了，又赶着吃饭就没有改进了。
后面我就去别人的解法，很简单，这个想法很好，原谅我一开始没想到，还想着每插入一个元素就将1数组向后移动。。。。

就是因为1数组位置足够大，前面的是排过序的，
后面就是空位置2的，所以根本不需要什么移动1数组。。。。
直接从后面开始遍历1和2数组，大一点的就放在1数组的后面。


*/
public void merge(int A[], int m, int B[], int n) {
    int i=m-1, j=n-1, k=m+n-1;
    while (i>-1 && j>-1) A[k--]= (A[i]>B[j]) ? A[i--] : B[j--];
    while (j>-1)         A[k--]=B[j--];
}