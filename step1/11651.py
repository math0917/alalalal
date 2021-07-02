import sys
arr=[]
N = int(sys.stdin.readline())

for i in range(N):
    arr.append(list(map(int,sys.stdin.readline().split())))

arr = sorted(arr, key = lambda x :(x[1],x[0]))
for i in range(len(arr)):
    print(arr[i][0],arr[i][1])