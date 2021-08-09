import sys
def permu(num, howmany, goal,val=1, count=0):
    while(True):
        val *= num
        while(True):
            if val % 10 == 0:
                count += 1
                val //= 10
            else:
                break
        num = num - 1
        if num == goal:
            return val, count
    

def divide(val,aim,count):
    while(True):
        if aim == 1:
            return count
        if val % aim !=0 :
            count -= 1
            val *= 10
        else:
            val //= aim
            aim -= 1
    

n ,m  = map(int,sys.stdin.readline().split())
if m == 0:
    print(0)
    sys.exit()
val, zero_count = permu(n,min(n-m,m), n-min(n-m,m))

print(divide(val, min(n-m, m), zero_count))
