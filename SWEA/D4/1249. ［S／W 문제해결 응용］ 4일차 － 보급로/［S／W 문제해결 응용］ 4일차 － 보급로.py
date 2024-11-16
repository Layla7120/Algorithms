T = int(input())

def valid(curr, move):
    new_x = curr[0] + move[0]
    new_y = curr[1] + move[1]
    if new_x < 0 or new_y < 0:
        return False
    if new_x >= n or new_y >= n:
        return False
    return True

def bfs(curr):
    min_vals = [[float('inf')] * n for x in range(n)]
    q = [curr]
    min_vals[curr[0]][curr[1]] = 0
    while q:
        curr = q.pop(0)
        for j in range(4):
            move = (dx[j], dy[j])
            if valid(curr, move):
                new_curr = curr[0] + move[0], curr[1] + move[1]
                new_val = board[new_curr[0]][new_curr[1]] + min_vals[curr[0]][curr[1]]
                if new_val < min_vals[new_curr[0]][new_curr[1]]:
                    min_vals[new_curr[0]][new_curr[1]] = new_val
                    q.append(new_curr)
    return min_vals[n - 1][n - 1]


# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    n = int(input())
    board = []
    for i in range(n):
        board.append([int(x) for x in input()])
    dx = [1, 0, -1, 0]
    dy = [0, -1, 0, 1]
    answer = bfs((0, 0))

    print(f'#{test_case} {answer}')
    # ///////////////////////////////////////////////////////////////////////////////////
