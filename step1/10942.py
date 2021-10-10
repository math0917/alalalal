import sys

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

m = int(sys.stdin.readline())

question = [tuple(map(int,sys.stdin.readline().split())) for _ in range(m)]

ans_set = set()

pelindrom = [[0]*(n+1) for _ in range(n+1)]

for length in range(1,n+1): #길이를 나타내는 값으로 생각
    for i in range(n): #index기준
        j = i+length-1
        if j>=n:
            break
        if i == j:
            pelindrom[i+1][j+1] = 1
        else:
            if arr[i] == arr[j]:
                if i+1 > j-1:
                    pelindrom[i+1][j+1] = 1
                else:
                    pelindrom[i+1][j+1] = pelindrom[i+2][j]
            else:
                pelindrom[i+1][j+1] = 0
for i in question:
    print(pelindrom[i[0]][i[1]])
        