import sys
input = sys.stdin.readline

n, m = map(int,input().split())

arr =[list(input().strip()) for _ in range(n)]

for gap in reversed(range(min(n,m))):
    for i in range(n-gap):
        for j in range(m-gap):
            if arr[i][j] == arr[i][j+gap] and arr[i][j+gap] == arr[i+gap][j+gap] and arr[i][j] == arr[i+gap][j]:
                print((gap+1)**2)
                sys.exit()
    