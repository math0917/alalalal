import sys
input = sys.stdin.readline
n = int(input())

ans = n
idx = 2
while idx * idx <= n:
    if n % idx == 0:
        ans -= ans//idx
        while n % idx == 0:
            n //= idx
    idx += 1

if n != 1:
    ans -= ans//n
print(ans)





