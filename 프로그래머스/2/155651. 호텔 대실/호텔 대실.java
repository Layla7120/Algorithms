import java.util.*;

class Solution {
    
    static class Reserve{
        int start;
        int end;
        
        Reserve(int start, int end){
            this.start = start;
            this.end = end + 10;
        }
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        List<Reserve> reserve_list = new ArrayList<>();
        PriorityQueue<Integer> rooms = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        
        for(String[] booking: book_time){
            reserve_list.add(new Reserve(getIntTime(booking[0]), getIntTime(booking[1])));
        }
        
        reserve_list.sort((a, b) -> Integer.compare(a.start, b.start));
        
        for(Reserve reserve: reserve_list){
            if(rooms.isEmpty()) {
                rooms.add(reserve.end);
            } else {
                if(rooms.peek() <= reserve.start){
                    rooms.poll();
                } 
                rooms.add(reserve.end);
            }
        }
        
        return rooms.size();
    }
    
    public static int getIntTime(String time){
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0])*60 + Integer.parseInt(arr[1]);
    }
}