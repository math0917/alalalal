import sys
input = sys.stdin.readline
import collections

n, k = map(int,input().split())

arr = [input().strip() for _ in range(n)]
for i in range(len(arr)):
    arr[i] = len(arr[i])
start = 0
finish = k
friends = collections.defaultdict(int)
for i in range(start, finish+1):
    friends[arr[i]] += 1
count = 0

for i in friends.keys():
    count += (friends[i]*(friends[i]-1))//2

for i in range(n-k-1):
    
    friends[arr[i]]-=1
    count += friends[arr[i+k+1]]
    friends[arr[i+k+1]] +=1
print(count)