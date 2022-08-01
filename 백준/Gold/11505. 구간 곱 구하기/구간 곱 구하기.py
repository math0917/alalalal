import sys
input = sys.stdin.readline
MOD = 1000000007
def make_tree(idx, start,finish):
    if start == finish:
        tree[idx] = arr[start] % MOD
        return tree[idx]
    tree[idx] = (make_tree(idx*2, start, (start+finish)//2) * make_tree(idx*2+1, (start+finish)//2 +1 , finish))%MOD
    return tree[idx]
def modify_tree(idx, start, finish, modify_index, after_v):
    
    if modify_index < start or finish < modify_index:
        return
    
    if start == finish:
        tree[idx] = after_v
    else:
        modify_tree(idx*2, start, (start+finish)//2, modify_index, after_v)
        modify_tree(idx*2 + 1, (start+finish)//2 + 1, finish, modify_index, after_v)
        tree[idx] = tree[idx*2] * tree[2*idx+1]%MOD
def multiply_tree(idx, start, finish,left, right):
    if right < start or finish < left:
        return 1
    if left <= start and finish <= right:
        return tree[idx]
    return (multiply_tree(idx*2, start, (start+finish)//2, left, right) * multiply_tree(idx*2+1,(start+finish)//2 +1,finish,left,right))%MOD
n, m, k = map(int,input().split())
arr = [int(input()) for _ in range(n)]

proc = [list(map(int,input().split())) for _ in range(m+k)]
tree = [0]*(5*n)
make_tree(1, 0, n-1)
for i in range(m+k):
    #바꾸기
    if proc[i][0] == 1:
        index = proc[i][1]-1
        after_value = proc[i][2]
        before_value = arr[index]
        arr[index] = after_value
        modify_tree(1,0,n-1,index,after_value)
    else:
        fr = proc[i][1] -1
        to = proc[i][2] -1
        print(multiply_tree(1,0, n-1, fr, to))