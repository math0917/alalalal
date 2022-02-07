import sys
import collections
aim = int(sys.stdin.readline())


this_turn_count = int(sys.stdin.readline())
arr = list(map(int,sys.stdin.readline().split()))
dictionary = collections.defaultdict(int)
stack = collections.deque([])

for count, i in enumerate(arr):
   
    for _ in range(count):
        this_turn = stack.popleft()
        dictionary[this_turn + i] += 1
        stack.append(this_turn+i)
    dictionary[i] += 1
    stack.append(i)
    

this_turn_count_2 = int(sys.stdin.readline())
arr_2 = list(map(int,sys.stdin.readline().split()))
dictionary_2 = collections.defaultdict(int)
stack_2 = collections.deque([])

for count, i in enumerate(arr_2):
    for _ in range(count):
        this_turn = stack_2.popleft()
        dictionary_2[this_turn + i] += 1
        stack_2.append(this_turn+i)
    dictionary_2[i]+=1
    stack_2.append(i)    
result = 0

for i in dictionary.keys():
    result += dictionary[i] * dictionary_2[aim-i]
print(result)