import heapq
from heapq import heappush
import sys

N, E = map(int, input().split())
graph = [[] for _ in range(N + 1)]
for n in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

v1, v2 = map(int, input().split())

def dijkstra(start):
    distance = [sys.maxsize for _ in range(N + 1)]
    distance[start] = 0
    hq = []
    heappush(hq, (start, 0))

    while hq:
        node, dist = heapq.heappop(hq)
        if dist > distance[node]:
            continue

        for next_n, value in graph[node]:
            new_dist = dist + value
            if new_dist < distance[next_n]:
                distance[next_n] = new_dist
                heapq.heappush(hq, (next_n, new_dist))

    return distance

dist_from_1 = dijkstra(1)
dist_from_v1 = dijkstra(v1)
dist_from_v2 = dijkstra(v2)

# 1 -> v1 -> v2 -> N
path1 = dist_from_1[v1] + dist_from_v1[v2] + dist_from_v2[N]
# 1 -> v2 -> v1 -> N
path2 = dist_from_1[v2] + dist_from_v2[v1] + dist_from_v1[N]

result = min(path1, path2)
if result >= sys.maxsize:
    print(-1)
else:
    print(result)