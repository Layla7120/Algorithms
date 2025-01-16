N = map(int, input())
nums = list(map(int, input().split()))

nums.sort()
answer = 0

def find_good(idx, new_nums):
    first = 0
    second = len(new_nums) - 1
    while first < second:
        sum_num = new_nums[first] + new_nums[second]
        if sum_num == nums[idx]:
            return 1
        elif sum_num < nums[idx]:
            first += 1
        else:
            second -= 1
    return 0

for i in range(len(nums)):
    new_nums = nums[:i] + nums[i + 1:]
    answer += find_good(i, new_nums)

print(answer)