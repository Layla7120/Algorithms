import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 결과를 담을 리스트
        ArrayList<Integer> expiredList = new ArrayList<>();
        
        // 약관 종류와 유효기간(일 수)을 저장할 맵
        Map<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            String[] parts = term.split(" ");
            String type = parts[0];
            int durationInMonths = Integer.parseInt(parts[1]);
            // 유효기간을 월 -> 일 단위로 변환하여 저장
            termsMap.put(type, durationInMonths * 28);
        }

        // 오늘 날짜를 총 일 수로 변환
        int todayTotalDays = getTotalDays(today);

        // 각 개인정보를 순회하며 파기 여부 확인
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] parts = privacy.split(" ");
            String collectionDate = parts[0];
            String termType = parts[1];

            // 개인정보 수집일자를 총 일 수로 변환
            int collectionTotalDays = getTotalDays(collectionDate);
            
            // 해당 약관의 유효기간(일 수)을 가져옴
            int durationInDays = termsMap.get(termType);

            // 파기해야 할 날짜 = 수집일 + 유효기간
            // 오늘 날짜가 파기해야 할 날짜보다 크거나 같으면 파기 대상
            if (collectionTotalDays + durationInDays <= todayTotalDays) {
                expiredList.add(i + 1);
            }
        }

        // 리스트를 배열로 변환하여 반환
        return expiredList.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * "YYYY.MM.DD" 형식의 날짜 문자열을 총 일 수로 변환하는 헬퍼 함수
     */
    private int getTotalDays(String date) {
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        // 모든 달은 28일이므로 계산이 간단해짐
        return (year * 12 * 28) + (month * 28) + day;
    }
}