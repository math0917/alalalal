import sys
input = sys.stdin.readline
def find (n):
    if ever[n] == n:
        ever[n]= ever[n-1]
        return n
    else:
        ever[n] = find(ever[n])
        return ever[n]
G = int(input())
P = int(input())

ever = list(range(G+1))
count = 0
lst = [int(input()) for _ in range(P)]

for i in lst:
    if ever[i] == 0:
        print(count)
        break
    else:
        result = find(ever[i])
        if result == 0:
            print(count)
            break
        
        ever[i] = ever[result-1]
        count += 1
else:
    print(P)