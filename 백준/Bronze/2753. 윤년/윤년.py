import sys
input = sys.stdin.readline
n = int(input())

if n%4 == 0:
    if n %400 == 0 or n % 100 !=0:
        print(1)
        sys.exit()
print(0)