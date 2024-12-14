def solution(numbers, target):
    def choose(idx, num_sum):
        if idx == len(numbers):
            if num_sum == target:
                return 1
            return 0
        return choose(idx + 1, num_sum + numbers[idx]) + choose(idx + 1, num_sum - numbers[idx])

    answer = choose(0, 0)
    return answer