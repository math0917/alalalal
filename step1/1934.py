import sys
def find_gcd(num1,num2):
    if num2 == 0:
        return num1 
    val = num1%num2
    num1, num2 = num2, val
    return find_gcd(num1,num2)

n = int(sys.stdin.readline())


arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

for i in arr:
    min_val = min(i[0],i[1])
    max_val = max(i[0],i[1]) 
    gcd = find_gcd(min_val,max_val)
    print(min_val // gcd * max_val // gcd * gcd)
