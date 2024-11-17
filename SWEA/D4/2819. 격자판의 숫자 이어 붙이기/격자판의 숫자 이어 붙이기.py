def valid(r, c):
    return 0 <= r < size_num and 0 <= c < size_num


def dfs(cnt, start):
    global lst
    if cnt == 7:
        result.add(''.join(lst))
        return
    for i in range(4):
        n_r, n_c = start[0] + move[i][0], start[1] + move[i][1]
        if valid(n_r, n_c):
            lst.append(board[n_r][n_c])
            dfs(cnt + 1, (n_r, n_c))
            lst.pop()


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    size_num = 4
    result = set()
    lst = []
    move = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    board = []
    for _ in range(size_num):
        board.append(input().split())
    for i in range(size_num * size_num):
        row = int(i / size_num)
        col = i % size_num
        dfs(0, (row, col))

    print(f'#{test_case} {len(result)}')
