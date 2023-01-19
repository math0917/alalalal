import sys
input = sys.stdin.readline
import collections

arr = list(map(int,input().split()))
arr.sort(reverse = True)
dic = collections.defaultdict(int)
mul = 1
while True:
    count = 0
    for i in arr:
        if mul % i == 0:
            count += 1
            
    if count >= 3:
        print(mul)
        sys.exit()
    else:
        mul += 1
