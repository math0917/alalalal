def solution(a):
    answer = 0
    minContinuous = []
    minReverse = [0] * len(a)
    for i in range(len(a)):
        if minContinuous:
            minContinuous.append(min(a[i], minContinuous[i-1]))
        else:
            minContinuous.append(a[i])
    for i in reversed(range(len(a))):
        if i == len(a) - 1:
            minReverse[i] = a[i]
        else:
            minReverse[i] = min(minReverse[i+1], a[i])
    answer = min(len(a), 2)
    for i in range(1, len(a) - 1):
        count = 0
        if minContinuous[i-1] < a[i]:
            count += 1
        if minReverse[i+1] < a[i]:
            count += 1
        if count <= 1:
            answer += 1
            
    return answer

    