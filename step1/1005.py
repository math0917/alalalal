import sys
import collections

def get_time(aim):
    if time[aim]>=0:
        return time[aim]
    if not parent[aim]:
        time[aim] = construct_time[aim-1]
        return construct_time[aim-1]
    else:
        max_time = 0
        for i in parent[aim]:
            if time[i]>=0:
                max_time = max(max_time, time[i])
            else:
                max_time = max(max_time,get_time(i))
        time[aim] = max_time + construct_time[aim-1]
        return max_time + construct_time[aim-1]
        

test_case = int(sys.stdin.readline())
real_result= []
for _ in range(test_case):
    n, k = map(int,sys.stdin.readline().split())
    construct_time = list(map(int,sys.stdin.readline().split()))
    parent = collections.defaultdict(list)
    time = [-1]*(n+1)
    for i in range(k):
        prev, cur = map(int,sys.stdin.readline().split())

        parent[cur].append(prev)
    
    aim = int(sys.stdin.readline())
    result = 0
    real_result.append(get_time(aim))

for i in real_result:
    print(i)