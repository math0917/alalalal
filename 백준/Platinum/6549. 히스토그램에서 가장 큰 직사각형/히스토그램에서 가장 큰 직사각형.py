import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)
def make_tree(idx, start,finish):
    if start == finish:
        tree[idx] = (histo[start],start)
        return tree[idx]
    mid = (start+finish)//2
    left_val, left_idx = make_tree(idx*2, start, mid)
    right_val, right_idx = make_tree(idx*2 + 1, mid+1, finish)
    if left_val <= right_val:
        tree[idx] = (left_val, left_idx)
    else:
        tree[idx] = (right_val, right_idx)
    return tree[idx]
def find_max_area(idx, start,finish, left, right):
    if left > finish or right < start:
        return (float('inf'), -1)
    if left <= start and finish <= right:
        return tree[idx]
    left_val, left_idx = find_max_area(idx*2, start, (start + finish)//2 , left, right)
    right_val, right_idx = find_max_area(idx*2 +1, (start + finish)//2+1, finish, left, right)
    if left_val <= right_val:
        return (left_val, left_idx)
    return(right_val, right_idx)
def conquer(lo, hi):
    if lo == hi:
        return histo[lo]
    if lo > hi:
        return -1
    minimum, idx = find_max_area(1, 0, histo_length-1, lo, hi)
    a = conquer(lo, idx-1)
    b = conquer(idx+1, hi)
    c = minimum*(hi-lo +1)
    return max(a,b,c)



    
while True:
    string = input()
    if string[0] == '0':
        break
    histo = list(map(int,string.split()))
    histo_length = histo[0]
    histo = histo[1:]
    tree = [0] * (5*histo_length)
    make_tree(1, 0, histo_length -1)
    max_area = 0
    print(conquer(0, histo_length-1))