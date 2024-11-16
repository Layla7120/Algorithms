# 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
# 아래 표준 입출력 예제 필요시 참고하세요.

# 표준 입력 예제
'''
a = int(input())                        정수형 변수 1개 입력 받는 예제
b, c = map(int, input().split())        정수형 변수 2개 입력 받는 예제 
d = float(input())                      실수형 변수 1개 입력 받는 예제
e, f, g = map(float, input().split())   실수형 변수 3개 입력 받는 예제
h = input()                             문자열 변수 1개 입력 받는 예제
'''

# 표준 출력 예제
'''
a, b = 6, 3
c, d, e = 1.0, 2.5, 3.4
f = "ABC"
print(a)                                정수형 변수 1개 출력하는 예제
print(b, end = " ")                     줄바꿈 하지 않고 정수형 변수와 공백을 출력하는 예제
print(c, d, e)                          실수형 변수 3개 출력하는 예제
print(f)                                문자열 1개 출력하는 예제
'''




'''
아래의 구문은 input.txt 를 read only 형식으로 연 후,
앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
아래 구문을 이용하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 구문을 사용하셔도 좋습니다.
아래 구문을 사용하기 위해서는 import sys가 필요합니다.
단, 채점을 위해 코드를 제출하실 때에는 반드시 아래 구문을 지우거나 주석 처리 하셔야 합니다.
'''
#import sys
#sys.stdin = open("input.txt", "r")

def valid(n_x, n_y):
    if n_x < 0 or n_y < 0:
        return False
    if n_x >= N or n_y >= N:
        return False
    return True
    

def bfs(start):
    q = [start]
    min_board = [[float('inf')] * N for _ in range(N)]
    min_board[start[0]][start[1]] = 0
    dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
    while q:
        curr = q.pop(0)
        for i in range(4):
            new_x = curr[0] + dx[i]
            new_y = curr[1] + dy[i]
            if valid(new_x, new_y):
                new_val = min_board[curr[0]][curr[1]] + board[new_x][new_y]
                if new_val < min_board[new_x][new_y]:
                    min_board[new_x][new_y] = new_val
                    q.append((new_x, new_y))
    return min_board[N-1][N-1]
            
        
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    N = int(input())
    board = []
    for i in range(N):
        board.append([int(x) for x in input()])
    answer = bfs((0, 0))
    print(f'#{test_case} {answer}')
    
    # ///////////////////////////////////////////////////////////////////////////////////
