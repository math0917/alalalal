import sys
import collections
sys.setrecursionlimit(100000)


def find(num):
    if root[num] == num:
        return num
    root[num] = find(root[num])
    return root[num]
    

def union(idx1,idx2):
    idx_1 = find(idx1)
    idx_2 = find(idx2)
    root[idx_1] = idx_2        


def index(num):
    start = 0
    finish = len(possible)
    while start <= finish:
        mid = (start + finish)//2
        if possible[mid] > num:
            finish = mid -1
        else:
            start = mid + 1
    return start

input = sys.stdin.readline

n,m,k = map(int,input().split())

possible = list(map(int,input().split()))

oppo = list(map(int,input().split()))

possible.sort()
root = [i for i in range(m+1)]
idx = 0
num = 1
stack = collections.deque([])

for i in oppo:
    idx = index(i)
    real_idx = find(idx)
    print(possible[real_idx])
    union(real_idx,real_idx+1)
    
    