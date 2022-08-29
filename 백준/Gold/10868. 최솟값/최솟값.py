import sys
input = sys.stdin.readline

def make_tree(index, left, right):
  if left == right:
    tree[index] = arr[left]
    return arr[left]
  mid = (left+right)//2
  left_val = make_tree(index*2, left, mid)
  right_val = make_tree(index*2 + 1, mid+1, right)
  tree[index] = min(left_val, right_val)
  return tree[index]
def find_min(index, left, right, start, finish):
  if left > finish or right < start:
    return float('inf')
  if start <= left and right <= finish:
    return tree[index]
  mid = (left+right)//2
  left_val = find_min(index*2, left, mid, start,finish)
  right_val = find_min(index*2+1, mid+1, right, start, finish)
  return min(left_val, right_val)
n, m = map(int,input().split())

arr = [int(input()) for _ in range(n)]

tree = [0]*(4*n)

make_tree(1,0,n-1)

for _ in range(m):
  fr, to = map(int,input().split())
  print(find_min(1,0,n-1, fr-1, to-1))
