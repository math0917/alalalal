import sys

N = int(sys.stdin.readline())

data = [sys.stdin.readline().strip() for i in range(N)]

arr = sorted(data,key = lambda x : int(x.split(' ')[0]))

for i in arr:
    print(i)