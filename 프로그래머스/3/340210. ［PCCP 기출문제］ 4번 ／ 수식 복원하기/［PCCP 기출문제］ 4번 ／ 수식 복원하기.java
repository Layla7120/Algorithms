import java.util.*;
import java.util.stream.*;

class Solution {
    
    public ArrayList<String> solution(String[] expressions) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<Integer> candidate = new ArrayList<>();
        
        System.out.println("Start");
        
        //2~9 진법을 순환하기
        for(int i=2; i<10; i++){
            boolean flag = true;
            
            for(int j=0; j<expressions.length; j++) {
                String[] arr = expressions[j].split(" ");

                String res = getResult(expressions[j], i);
                
                if (res.isEmpty()) {
                    flag = false;
                    break; 
                }
                
                if (!arr[4].equals("X")) {
                    try {
                        Long.parseLong(arr[4], i); 
                    } catch (NumberFormatException e) {
                        flag = false; 
                        break;
                    }

                    if (!arr[4].equals(res)) {
                        flag = false; 
                        break;
                    }
                }
            }
            
            if(flag == true){
                candidate.add(i);
            }
        }
        
        System.out.println(candidate.toString());
        
        
        for(String exp: expressions) {
            String[] arr = exp.split(" ");
            if(arr[4].equals("X")){
                int N = candidate.get(0);
                String start = getResult(exp, N);
                boolean flag = true;
                for(int i=1; i<candidate.size(); i++) {
                    N = candidate.get(i);
                    String res = getResult(exp, N);
                    if(!start.equals(res)){
                        flag = false;
                        break;
                    }
                }
                
                String ans = "";
                if(flag){ 
                    ans = arr[0] + " " + arr[1] + " " + arr[2] + " " 
                            + arr[3] + " " + start + "";
                } else {
                    ans = arr[0] + " " + arr[1] + " " + arr[2] + " " 
                            + arr[3] + " ?";
                }
                answer.add(ans);
            }
        }
        
        System.out.println("end2");
        return answer;
    }
    
    
    private static String getResult(String exp, int N){
        try{
            String[] arr = exp.split(" ");
            long a = Long.parseLong(arr[0], N);
            String op = arr[1];
            long b = Long.parseLong(arr[2], N);
            String c = arr[4];

            long result = calculate(a, b, op);
            String result2 = Long.toString(result, N);
            
            return result2;
            
        } catch (NumberFormatException e) {
            return ""; 
        }
    }
   
    
    private static long calculate(long a, long b, String op){
        switch (op){
            case "+":
                return a + b;
            case "-":
                return a - b;
        }
        return 0;
    }
}