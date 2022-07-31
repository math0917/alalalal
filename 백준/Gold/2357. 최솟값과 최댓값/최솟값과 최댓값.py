import sys
input = sys.stdin.readline

def make_tree(idx, start, finish):
    if start == finish:
        tree[idx] = (arr[start], arr[start])
        return tree[idx]
    left_min, left_max = make_tree(idx*2, start, (start+finish)//2)
    right_min, right_max = make_tree(idx*2+1, (start+finish)//2+1, finish)
    tree[idx] = (min(left_min, right_min), max(right_max, left_max))
    return tree[idx]

def find_min_max(idx, start, finish, left, right):
    if right < start or left > finish:
        return (float('inf'), float('-inf'))
    if left <=start and finish <= right:
        return tree[idx]
    mid = (start + finish)//2
    left_min, left_max = find_min_max(idx*2, start, mid, left, right)
    right_min, right_max = find_min_max(idx*2 + 1, mid+1, finish, left ,right)
    return (min(left_min,right_min), max(left_max, right_max))
n, m = map(int,input().split())

arr = [int(input()) for _ in range(n)]

from_to = [list(map(int,input().split())) for _ in range(m)]
tree = [0]*(5*n)
make_tree(1, 0, n-1)

for fr, to in from_to:
    print(*find_min_max(1, 0, n-1, fr-1, to-1))
    