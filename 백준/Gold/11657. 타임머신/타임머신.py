import sys


def bf(start):
    dist[start] = 0
    for i in range(1, N + 1):
        # 모든 간선 확인
        for j in range(M):
            current = edges[j][0]
            next = edges[j][1]
            cost = edges[j][2]

            if dist[current] != sys.maxsize and dist[next] > dist[current] + cost:
                dist[next] = dist[current] + cost
                # N번째 에서도 값이 갱신되면 음수 순환 존재
                if i == N:
                    return True
    return False


N, M = list(map(int, input().split()))
edges = [list(map(int, input().split())) for _ in range(M)]

dist = [sys.maxsize] * (N + 1)

infinite_loop = bf(1)

if infinite_loop:
    print("-1")
else:
    for i in range(2, N + 1):
        if dist[i] == sys.maxsize:
            print(-1)
        else:
            print(dist[i])