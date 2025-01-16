import heapq
import sys
from collections import deque


N, M = list(map(int, input().split()))
dists = [list(map(int, input().split())) for _ in range(M)]


# 정보 미리 Graph 에 넣어두기
graph = [[] for _ in range(N + 1)]
for a, b, c in dists:
    graph[a].append((b, c))
    graph[b].append((a, c))

def dijkstra(start):
    distance = [sys.maxsize] * (N + 1)
    distance[start] = 0

    # 해당 노드가 무엇이랑 연결되어 있는지 추적하기 위해
    prev = [-1] * (N + 1)
    pq = []
    heapq.heappush(pq, (start, 0)) # (node, distance)

    while pq:
        node, dist = heapq.heappop(pq)

        if dist > distance[node]:
            continue

        for next, value in graph[node]:
            new_dist = dist + value
            if new_dist < distance[next]:
                distance[next] = new_dist
                prev[next] = node
                heapq.heappush(pq, (next, new_dist))
    return distance, prev

distance, prev = dijkstra(1)

result = []
for i in range(2, N + 1):
    if prev[i] != -1:
        result.append((prev[i], i))

print(len(result))
for a, b in result:
    print(a, b)
