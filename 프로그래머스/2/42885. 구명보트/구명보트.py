from collections import deque


def solution(people, limit):
    people.sort()
    people = deque(people)
    answer = 0
    while people:
        person = people.pop()
        if people:
            light = people.popleft()
            if (light + person) > limit:
                people.appendleft(light)
        answer += 1
    return answer