import sys
import collections
import itertools
n, s = map(int,sys.stdin.readline().split())
result = 0
number_list = list(map(int,sys.stdin.readline().split()))
left_part = collections.defaultdict(int)
right_part = collections.defaultdict(int)
left_list = number_list[:n//2]
right_list = number_list[n//2:]

for i in range(1,n//2+1):
    cmb = itertools.combinations(left_list, i)
    for j in cmb:
        left_part[sum(j)]+=1

for i in range(1,(n+1)//2 + 1):
    cmb = itertools.combinations(right_list,i)
    for j in cmb:
        right_part[sum(j)] += 1

result = 0
for i in left_part.keys():
    result += left_part[i] * right_part[s-i]
result += left_part[s]+ right_part[s]
print(result)