import sys

n, m = map(int , sys.stdin.readline().split())

arr = [int(sys.stdin.readline()) for _ in range(n)]
count = 0
for i in reversed(arr):
    count += m // i
    m = m%i
print(count)