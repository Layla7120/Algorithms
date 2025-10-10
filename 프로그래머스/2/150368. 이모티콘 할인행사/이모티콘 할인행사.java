import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
	static int N, M;
	
    public int[] solution(int[][] users, int[] emoticons) {
    	N = 0;
    	M = 0;
    	choose(0, new int[emoticons.length], users, emoticons);
    	
        int[] answer = {N, M};
        return answer;
    }
    
    
    public void choose(int count, int[] arr, int[][] users, int[] emoticons) {
    	if(count == emoticons.length) {
    		// 계산
    		int n = 0;
    		int sum_total = 0;
    		
    		for(int i=0; i<users.length; i++) {
    			int[] arr2 = users[i];
    			int wanted_discount = arr2[0];
    			int threshold = arr2[1];
    			
    			int total = 0;
    			for(int j=0; j<arr.length; j++) {
    				if(arr[j] >= wanted_discount) {
    					total += emoticons[j] / 100 * (100 - (arr[j]));
    				}
    			}
    			
    			if(total >= threshold) {
    				n += 1;
    			} else {
    				sum_total += total;
    			}
    		}
    		
    		if(n > N) {
    			N = n;
    			M = sum_total;
    		} else if(n == N && sum_total > M) {
    			M = sum_total;
    		}
    		return;
    	}
    	
    	for(int i=10; i<=40; i+=10) {
    		arr[count] = i;
    		choose(count+1, arr, users, emoticons);
    	}
    }
}