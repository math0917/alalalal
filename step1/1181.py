import sys

N = int(sys.stdin.readline())

data = [sys.stdin.readline().strip() for i in range(N)]

arr = sorted(data,key = lambda x : (len(x), x))
dup=[]
for i in arr:
    if i not in dup:
        dup.append(i)
        print(i)