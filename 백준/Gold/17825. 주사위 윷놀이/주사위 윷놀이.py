
adj = [[1],[2],[3],[4],[5],[6,21],[7],[8],[9],[10],[11,27],[12],[13],[14],[15],[16,29],[17],[18],[19],[20],[32],[22],[23],[24],[25],[26],[20],[28],[24],[30],[31],[24],[32],[32],[32],[32],[32]]
score =[0,  2,  4,  6,  8,  10,    12, 14, 16, 18,  20,     22,  24,  26,  28,  30,     32,  34,  36,  38,  40,  13,  16,  19,  25,  30,  35,  22,  24,  28,  27,  26,  0]


def dfs(count, sum_score):
    global answer
    if count == 10:
        answer = max(answer, sum_score)
        return

    for i in range(4):
        start = current_loc[i]
        piece_loc = adj[start][-1] # 갈림길인 경우 먼저 한칸 이동
        for _ in range(1, dices[count]):
            piece_loc = adj[piece_loc][0]

        # 도착지점이거나 말이 겹치지 않은 경우 이동 - 이게 어려웠음
        if piece_loc == 32 or piece_loc not in current_loc:
            current_loc[i] = piece_loc
            dfs(count + 1, sum_score + score[piece_loc])
            current_loc[i] = start


dices = list(map(int, input().split()))
current_loc = [0, 0, 0, 0]
answer = 0
dfs(0, 0)
print(answer)