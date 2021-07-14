import sys


n = int(sys.stdin.readline())

count = 0
arr = []
str = []
for i in range(n):
    str.append(i)
arr.append(str)
idx = 0
ptr=[]
while(len(arr[0]!=0)):
    val = arr[idx].pop(-1)
    ptr.append(val)
    idx += 1
    bool = [True for _ in range(n)]
    for i in range(len(ptr)):
        this_col = ptr[i]
        this_row = i
        idx - this_row 