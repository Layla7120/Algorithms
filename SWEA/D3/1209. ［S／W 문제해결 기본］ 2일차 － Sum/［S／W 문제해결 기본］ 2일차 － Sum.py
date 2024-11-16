#import sys
#sys.stdin = open("input.txt", "r")

T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    n = int(input())
    board = []
    max_result = 0
    # 입력 넣을 때 행 sum 구하기
    for i in range(100):
        row = list(map(int, input().split()))
        max_result = max(max_result, sum(row))
        board.append(row)
        
	# 열 계산하기
    for i in range(100):
        total = 0
        for j in range(100):
            total += board[j][i]
            max_result = max(max_result, total)
            
    # 대각 왼오 계산하기
    diagonal = 0
    for i in range(100):
        diagonal += board[i][i]
    max_result = max(max_result, diagonal)
    # 대각 오왼 계산하기
    diagonal = 0
    for i in range(100):
        diagonal += board[i][99-i]
    max_result = max(max_result, diagonal)
    print(f'#{test_case} {max_result}')
    # ///////////////////////////////////////////////////////////////////////////////////
