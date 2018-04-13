/*
In LOL world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition. Now, given the Teemo's attacking ascending time series towards Ashe and the poisoning time duration per Teemo's attacking, you need to output the total time that Ashe is in poisoned condition.

You may assume that Teemo attacks at the very beginning of a specific time point, and makes Ashe be in poisoned condition immediately.

Example 1:
Input: [1,4], 2
Output: 4
Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately. 
This poisoned status will last 2 seconds until the end of time point 2. 
And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds. 
So you finally need to output 4.


Example 2:
Input: [1,2], 2
Output: 3
Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned. 
This poisoned status will last 2 seconds until the end of time point 2. 
However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status. 
Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2, it will stop at the end of time point 3. 
So you finally need to output 3.

题目描述：就是说在LOL的世界里有个叫Teemo可以毒Ashe，给你一个增长的数组也就是攻击Ashe的时间点，和一个中毒的时间长度，如果中毒的时候再中毒，时间不能算重复的。
*/

/*
	第一种：通过。
	一看见这个题目就想到用坐标轴，end表示中毒结束的时间。
	posisoned表示中毒的时间。
*/
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int posisoned = 0;
        int end = -1;
        for(int i = 0; i < timeSeries.length; i++){
            if(end < timeSeries[i]){
                posisoned += duration;
            } else{      
                posisoned = posisoned - (end - timeSeries[i]) + duration - 1;
            }
            end = timeSeries[i] + duration - 1;
        }
        return posisoned;
    }
}
/*
	第二种：别人的，通过。
	这种和我的想法差不多，不过更加简化了。
	也可以通过坐标轴去理解。
*/
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        
        if(timeSeries==null || timeSeries.length<1) return 0;
        int result=duration;
        
        for(int i=1;i<timeSeries.length;i++){
            
            int diff = timeSeries[i]-timeSeries[i-1];
            result+= (diff>=duration) ? duration : diff;
            
        }
        
        return result;
    }
