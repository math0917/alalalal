import sys
input = sys.stdin.readline

def bin_search(start,finish,aim):
    if start == finish:
        if memory[start] > aim:
            memory[start] = aim
        return
    mid = (start+finish)//2
    if memory[mid] > aim:
        bin_search(start,mid,aim)
    elif memory[mid] < aim:
        bin_search(mid+1,finish,aim)
    else:
        if memory[mid] > aim:
            memory[mid] = aim
        return

n = int(input())

lst = list(map(int,input().split()))

memory=[lst[0]]

max_length = 1

for i in lst:
    if memory[-1] < i:
        memory.append(i)
        max_length+=1
    else:
        bin_search(0,max_length-1,i)
print(len(memory))