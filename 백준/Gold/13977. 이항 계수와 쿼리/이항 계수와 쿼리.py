import sys
input = sys.stdin.readline
MOD = 1000000007

def pow(num, power):
    if power == 1:
        return num
    a = pow(num, power//2)
    if power % 2 == 1:
        return a * a % MOD * num % MOD
    return a * a % MOD

T = int(input())

factorial = [1] * 4000001

for i in range(1, 4000001):
    factorial[i] = (factorial[i-1] * i) % MOD


for _ in range(T):
    n, r = map(int,input().split())
    A = factorial[n]
    B = factorial[r]
    B = (B * factorial[n-r]) % MOD
    print(A * pow(B,MOD-2) %MOD)