import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    N = int(input())
    height = list(map(int,input().split()))
    result = [0]*N
    height.sort()
    front = True
    idx = 0
    while height:
        if front:
            result[idx] = height.pop()
            front = False
        else:
            front = True
            result[N-1-idx] = height.pop()
            idx += 1
    max_height = 0
    
    for i in range(N):
        max_height = max(abs(result[i] - result[(i+1)%N]), max_height)
    print(max_height)