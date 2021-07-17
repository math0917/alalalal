import sys
import math
def find_per(lst,str,aim,result):
    
    if len(str)+ len(lst) <aim: 
        return result
    if len(str)==aim:
        copy = []
        for i in str:
            copy.append(i)
        result.append(copy)
        return result
    for i in range(len(lst)):
        str.append(lst[i])
       
        result = find_per(lst[i+1:len(lst)+1],str,aim,result)
       
        str.pop(-1)
    
    return result

def find_min(arr,result,min_val):
    n = len(arr[0])
    for i in result:
        set_n = set(range(n))
        diff = list(set_n-set(i))
        gap = 0
        for j in range(len(i)):
            for k in range(len(i)):
                gap += arr[i[j]][i[k]]
                gap -= arr[diff[j]][diff[k]]
        if abs(gap)<=min_val:
            min_val = abs(gap)
    return min_val
        
n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

lst = list(range(n))

result = find_per(lst[1:len(lst)+1],[0],n//2,[])

print(find_min(arr,result,math.inf))