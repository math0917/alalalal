import sys
import bisect
input = sys.stdin.readline

n = int(input())

crane = list(map(int,input().split()))

m = int(input())

bag = list(map(int,input().split()))

crane.sort(key = lambda x : -x)
bag.sort(key = lambda x : -x)


count = 0
ever_since = 0

if crane[0] < bag[0]:
    print(-1)
    sys.exit()
time = 0
while bag:
    if not bag:
        break
    for i in crane:
        for j in bag:
            if i >= j:
                bag.remove(j)
                break
    time += 1
print(time)