import sys
import collections
input = sys.stdin.readline

n, m = map(int,input().split())

aim = list(map(int,input().split()))
deque = collections.deque([])
for i in range(1,n+1):
    deque.append(i)
count = 0

for i in aim:
    count_1 = 0
    deque_1 = collections.deque([])
    count_2 = 0
    deque_2 = collections.deque([])
    while True:
        this_turn = deque.popleft()
        if this_turn == i:
            deque.appendleft(i)
            
            break
        else:
            deque_1.append(this_turn)
            count_1 += 1
    while True:
        this_turn = deque.pop()
        if this_turn == i:
            count_2 += 1
            break
        else:
            deque_2.append(this_turn)
            count_2 +=1
    if count_1 > count_2:
        count += count_2
        while deque_1:
            deque.append(deque_1.popleft())
        while deque_2:
            deque.appendleft(deque_2.popleft())
    else:
        count += count_1
        while deque_2:
            deque.append(deque_2.pop())
        while deque_1:
            deque.append(deque_1.popleft())
    
print(count)
