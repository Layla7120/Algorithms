def valid(r, c):
    condition = board[r][c] == 0 or board[r][c] == 3
    return 0 <= r < total and 0 <= c < total and (r, c) not in visited and condition

def dfs(start):
    stack = [start]
    move = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    while stack:
        curr = stack.pop()
        row, column = curr[0], curr[1]
        if board[row][column] == 3:
            return 1
        for i in range(4):
            n_r, n_c = row + move[i][0], column + move[i][1]
            if valid(n_r, n_c):
                stack.append((n_r, n_c))
                visited.add((n_r, n_c))
    return 0

T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())
    board = []
    total = 16
    visited = set()

    for i in range(total):
        board.append([int(x) for x in input()])

    start = (1, 1)
    answer = dfs(start)

    print(f'#{test_case} {answer}')