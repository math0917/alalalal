import sys

arr = []
while(True):
    ptr = list(map(int,sys.stdin.readline().split()))
    if ptr == [0,0]:
        break
    arr.append(ptr)
for i in arr:
    first = i[0]
    second = i[1]
    if first%second == 0:
        print('multiple')
    elif second % first == 0:
        print('factor')
    else:
        print('neither')