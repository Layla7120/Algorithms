def valid(x, y, visited):
    return 0 <= x < total and 0 <= y < total and (x, y) not in visited


def next_curr(curr, visited):
    move = [(0, 1), (0, -1)]
    for m in move:
        n_x, n_y = curr[0] + m[0], curr[1] + m[1]
        if valid(n_x, n_y, visited) and board[n_x][n_y]:
            return m
    return -1, 0


def dfs(start):
    q = [start]
    visited = []
    while q:
        curr = q.pop(0)
        if curr[0] == 0:
            return curr[1]
        m_x, m_y = next_curr(curr, visited)
        n_x, n_y = curr[0] + m_x, curr[1] + m_y
        if valid(n_x, n_y, visited):
            q.append((n_x, n_y))
            visited.append((n_x, n_y))


T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    num = int(input())
    total = 100
    board = []
    for i in range(total):
        board.append([int(x) for x in input().split()])

    idx = board[total - 1].index(2)
    start = (total - 1, idx)
    answer = dfs(start)

    print(f'#{test_case} {answer}')