import sys

n = int(sys.stdin.readline())
arr = []

for i in range(n):
    m = int(sys.stdin.readline())
    ptr = [sys.stdin.readline()[:-1] for _ in range(m)]
    arr.append(ptr)
for i in arr:
    fashion = dict()
    for j in i:
        j = j.split()
        if j[1] in fashion.keys():
            fashion[j[1]].append(j[0])
        else:
            fashion[j[1]] = [j[0]]
    num = 1
    for k in fashion.keys():
        num*=len(fashion[k])+1
    print(num-1)
