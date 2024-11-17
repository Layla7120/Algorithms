from collections import deque


def test():
    open_cases = ['{', '[', '(', '<']
    close_cases = ['}', ']', ')', '>']

    open_p = deque()
    _ = input()
    # open case 일때는 stack 에 넣고
    # close case 일때는 stack 에서 pop 해 index 값 비교
    for x in input():
        if x in open_cases:
            open_p.append(x)
        elif x in close_cases:
            if len(open_p) == 0:
                return 0
            p = open_p.pop()
            if open_cases.index(p) != close_cases.index(x):
                return 0
        else:
            return 0
    if open_p:
        return 0
    return 1


T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print(f'#{test_case} {test()}')
