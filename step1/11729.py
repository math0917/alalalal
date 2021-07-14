import sys

def Hanoi(From,To,By,n,arr):
    if n == 1:
        arr.append([From,To])
        return arr
    Hanoi(From,By,To,n-1,arr)
    arr.append([From,To])
    Hanoi(By,To,From,n-1,arr)
    return arr
n = int(sys.stdin.readline())

ptr = Hanoi(1,3,2,n,[])
print(len(ptr))

for i in ptr:
    print(i[0],i[1])