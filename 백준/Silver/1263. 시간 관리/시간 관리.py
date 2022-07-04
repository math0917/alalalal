import sys
input= sys.stdin.readline

n = int(input())

affair = [list(map(int,input().split())) for _ in range(n)]

affair.sort(key = lambda x : (-x[1], -x[0]))

min_time = 0
min_term = float('inf')
while affair:
    duration, until = affair.pop()    
    
    if min_time + duration > until:
        print(-1)
        sys.exit()
    else:
        
        min_time = min_time + duration
        min_term = min(min_term, until-min_time)
print(min_term)