import sys
input = sys.stdin.readline

n = int(input())

count = 0

for i in range(7):
    if n & 1<<i:
        count += 1
print(count)