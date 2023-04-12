import collections
import heapq
def solution(a):
    answer = 0
    map = collections.defaultdict(int)
    for i in a:
        map[i] += 1
    arr = []
    for i in map.keys():
        arr.append((-map[i], i))
    heapq.heapify(arr)
    
    while arr:
        list = heapq.heappop(arr)
        print(list)
        if -1 * list[0] * 2 <= answer:
            break
        count = 0
        idx = 0
        
        while idx < len(a):
            if a[idx] == list[1]:
                if idx + 1 < len(a):
                    if a[idx + 1] != a[idx]:
                        count += 2
                        idx += 1
            else:
                if idx + 1 < len(a):
                    if a[idx + 1] == list[1]:
                        idx += 1
                        count += 2
            idx += 1
        
        answer = max(answer, count)
    

    
    return answer