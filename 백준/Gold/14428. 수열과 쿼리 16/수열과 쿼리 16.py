import sys
input = sys.stdin.readline

def make_tree(index,start, finish):
    if start == finish:
        tree[index] = (arr[start],start)
        return tree[index]
    mid = (start + finish)//2
    left_val, left_index = make_tree(index*2,start, mid)
    right_val, right_index = make_tree(index*2+1,mid+1, finish)
    if left_val <= right_val: tree[index] = (left_val, left_index)
    else: tree[index] = (right_val, right_index)
    return tree[index]
def update_tree(index, start,finish, idx, value):
    if idx < start or idx > finish:
        return tree[index]
    if start == finish:
        tree[index] = (value, idx)
        return tree[index]
    mid = (start + finish)//2
    left_val, left_index = update_tree(index* 2 , start, mid, idx, value)
    right_val , right_index = update_tree(index*2+ 1 , mid + 1, finish, idx, value)
    if left_val <= right_val: tree[index] = (left_val, left_index)
    else: tree[index] = (right_val, right_index)
    return tree[index]
def find_tree(index, start, finish, fr, to):
    if fr > finish or to < start:
        return (float('inf'), float('inf'))
    if fr <= start  and finish <= to:
        return tree[index]
    mid = (start + finish) // 2
    
    left_val, left_idx = find_tree(index*2, start, mid, fr, to)
    right_val ,right_idx = find_tree(index*2+ 1 , mid+ 1, finish , fr, to)
    if left_val <= right_val : return (left_val, left_idx)
    else: return (right_val, right_idx)
n = int(input())

arr = list(map(int,input().split()))

proc_count = int(input())
tree = [0] * (4*n)
make_tree(1,0, n-1)
# print(tree)
for _ in range(proc_count):
    proc, arg1, arg2 = map(int,input().split())
    # 바꾸기
    if proc == 1:
        arr[arg1-1] = arg2
        update_tree(1,0,n-1,arg1-1, arg2)
        # print(tree)
    # 작은 인덱스 고르기
    else:
        print(find_tree(1, 0, n-1, arg1-1, arg2-1)[1]+1)