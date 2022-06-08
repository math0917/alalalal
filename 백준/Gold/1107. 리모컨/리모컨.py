import sys
input = sys.stdin.readline

num = input().strip()

n = int(input())

ten_set = set(range(10))
if n:
    del_set = set(map(int,input().split()))
else:
    del_set = set()

list = []
ten_set -= del_set
min_val = float('inf')
for this_num in range(1000000):
    str_num = str(this_num)
    if this_num - int(num) > min_val:
        continue
    for i in str_num:
        if int(i) not in ten_set:
            break
    else:
        
        min_val = min(abs(this_num - int(num)) + len(str_num), min_val)
        continue
    
min_val = min(abs(int(num)-100),min_val)
print(min_val)


