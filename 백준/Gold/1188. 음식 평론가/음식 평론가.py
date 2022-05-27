import sys

def gcd(n1,n2):
    if n1%n2 == 0:
        return n2
    return gcd(n2, n1%n2)

n, m = map(int,sys.stdin.readline().split())

print(m-gcd(n,m))