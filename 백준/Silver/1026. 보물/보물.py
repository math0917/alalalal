import sys
input = sys.stdin.readline

n = int(input())

arr1 = sorted(list(map(int,input().split())))
arr2 = sorted(list(map(int,input().split())),key=lambda x: -x)
sum = 0

for i in range(n): 
    sum += arr1[i]*arr2[i]
print(sum)