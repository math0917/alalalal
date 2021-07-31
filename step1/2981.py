import sys
def print_cd(num1):
    for i in range(2,num1):
        if num1%i == 0:
            print(i,'', end='')
    print(num1)
def find_gcd(num1,num2):
    if num2 == 0:
        return num1 
    val = num1%num2
    num1, num2 = num2, val
    return find_gcd(num1,num2)



def gcd (arr):
    while( True ):
        try:
            first_val = arr.pop(-1)
            second_val = arr.pop(-1)
        except:
            break
        arr.append(find_gcd(min(first_val,second_val), max(first_val,second_val)))
    print_cd(first_val)
n = int(sys.stdin.readline())

arr = [int(sys.stdin.readline()) for _ in range(n)]

arr = [abs(j- arr[-1]) for j in arr]

arr.pop(-1)

gcd(arr)


