import sys
def find_gcd(num1,num2):
    if num2 == 0:
        return num1 
    val = num1%num2
    num1, num2 = num2, val
    return find_gcd(num1,num2)

n = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))
comp = arr[0]
for i in range(1,len(arr)):
    num2 = arr[i]
    gcd = find_gcd(min(comp,num2), max(comp,num2))
    print('%d/%d'%(comp//gcd, num2//gcd))