import sys
import collections

number = int(sys.stdin.readline())

arr = list(map(int,sys.stdin.readline().split()))

count = dict()
result = []
for i in arr:
    result.append(-1)
    try:
        count[i]+=1
    except:
        count[i] = 1

right = collections.deque([])
leftover = collections.deque([])

for idx,i in enumerate(reversed(arr)):
    
    this_turn_count = count[i]
    while right:
        compare = right.pop() #오른쪽원소 빼서
        leftover.append(compare)
        if compare[1] > this_turn_count: #뺀원소가 지금카운트보다 크면
            result[idx] = compare[0] #그 원소를 넣고
            break
    while leftover: #뺀원소 다시넣고
        right.append(leftover.pop())
    right.append((i,count[i])) #지금원소 넣자
for i in reversed(result):
    print(i,'',end='')