import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    String[] input = bf.readLine().split(" ");

    int min_num = Integer.MAX_VALUE;
    int max_num = Integer.MIN_VALUE;

    for (String s: input){
      int num = Integer.parseInt(s);
      if (num > max_num){
        max_num = num;
      }
      if (num < min_num){
        min_num = num;
      }
    }

    System.out.println(Integer.toString(min_num) + " " + Integer.toString(max_num));
  }
}