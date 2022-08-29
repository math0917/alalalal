import sys
input = sys.stdin.readline

def sum_tree(index, left, right, start, finish):
  if left > finish or right < start:
    return 0
  if start <= left and right <= finish:
    return tree[index]
  mid = (left + right)//2
  return sum_tree(index*2, left, mid, start, finish) + sum_tree(index*2+1, mid+1, right, start, finish)

def modify_tree(index, left, right, idx,value):
  
  if idx < left or right < idx:
    return
  tree[index] += value
  if left == right:
    return
  mid = (left+right)//2 
  modify_tree(index*2, left, mid, idx, value)
  modify_tree(index*2+1, mid+1, right, idx,value)

n, m = map(int,input().split())

arr = [0]*n
tree = [0]*(4*n)

for _ in range(m):
  proc, fr, to = map(int, input().split())
  #  합
  if proc == 0:
    fr, to = min(fr,to), max(fr, to)
    print(sum_tree(1, 0, n-1, fr-1, to-1))
  #  수정 
  else:
    value = to - arr[fr-1]
    arr[fr-1] = to
    modify_tree(1,0, n-1, fr-1,value)
