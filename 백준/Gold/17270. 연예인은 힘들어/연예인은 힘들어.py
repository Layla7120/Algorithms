import sys

V, M = map(int, input().split())
dists = [list(map(int, input().split())) for _ in range(M)]
J, S = map(int, input().split())

min_dist = [[sys.maxsize for _ in range(V + 1)] for _ in range(V + 1)]

# 최초 거리 저장
for dist in dists:
    a, b, c = dist
    if a == b:
        continue
    if min_dist[a][b] > c:
        min_dist[a][b] = c
        min_dist[b][a] = c

# 특정 정점을 지났을 때 최소 거리 업데이트
for i in range(1, V + 1):
    for j in range(1, V + 1):
        for k in range(j + 1, V + 1):
            if min_dist[j][i] + min_dist[k][i] < min_dist[j][k]:
                min_dist[j][k] = min_dist[j][i] + min_dist[k][i]
                min_dist[k][j] = min_dist[j][i] + min_dist[k][i]


dist_J = min_dist[J]
dist_S = min_dist[S]

# J - S 는 약속 장소가 될 수 없으므로
dist_J[S] = sys.maxsize
dist_S[J] = sys.maxsize

sum_dist = []

for i in range(len(dist_J)):
    sum_dist.append(dist_J[i] + dist_S[i])

if min(sum_dist) >= sys.maxsize:
    print(-1)
else:
    min_idx = -1
    min_j_dist = sys.maxsize
    for i in range(1, len(sum_dist)):
        if sum_dist[i] == min(sum_dist) and dist_J[i] <= dist_S[i]:
            if min_j_dist > dist_J[i]:
                min_j_dist = dist_J[i]
                min_idx = i
    print(min_idx)
