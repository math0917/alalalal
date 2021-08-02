import sys

def find(num,result,count):
    if num == 0:
        return count
    
    result *= num
   
    while (True):
        comp = result%10
        if comp == 0:
            result= result//10
            count = count+1
        else:
            break

        
    return find(num-1,result,count)
n = int(sys.stdin.readline())

print(find(n,1,0))