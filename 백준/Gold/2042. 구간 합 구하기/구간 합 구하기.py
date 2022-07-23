import sys
input = sys.stdin.readline

def MakeTree(idx, start, finish):
    if start == finish:
        tree[idx] = arr[start]
        return arr[start]
    tree[idx] = MakeTree(idx*2, start, (start+finish)//2) + MakeTree(idx*2+1, (start+finish)//2 +1, finish)
    return tree[idx]

def UpdateTree(idx, start,finish, aimIdx, num):
    if start > aimIdx or finish < aimIdx:
        return
    tree[idx] += num
  
    if start != finish:
        UpdateTree(idx*2, start, (start+finish)//2, aimIdx, num)
        UpdateTree(idx*2+1, (start+finish)//2 + 1, finish, aimIdx, num) 
def SumTree(idx, start, finish, left, right):
    
    if left > finish or right < start : return 0
    elif left <= start and finish <= right : return tree[idx]
    else: return SumTree(idx*2,start, (start+finish)//2, left, right) + SumTree(idx*2+1, (start+finish)//2+1, finish,left,right ) 

n, m, k = map(int,input().split())

arr = [int(input()) for _ in range(n)]

proc = [list(map(int,input().split())) for _ in range(m+k)]



tree = [0]*(4*n)

MakeTree(1,0,n-1)

for a, b, c in proc:
    if a == 1:
        idx = b - 1
        addNum = c - arr[idx] 
        arr[idx] = c
        UpdateTree(1,0,n-1, idx, addNum)  
    else:
        print(SumTree(1, 0, n-1, b-1, c-1))
        
        