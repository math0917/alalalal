N = int(input())

arr = str(N)
arr1=[]
for i in range(len(arr)):
    arr1.append(int(arr[i]))
arr1.sort()

for i in reversed(arr1):
    print(i,end='')