import sys

def find_gcd(num1,num2):
    if num2 == 0:
        return num1 
    val = num1%num2
    num1, num2 = num2, val
    return find_gcd(num1,num2)

n ,m = map(int,sys.stdin.readline().split())

gcd = find_gcd(n,m)

print(gcd)
print(n//gcd * m//gcd * gcd)