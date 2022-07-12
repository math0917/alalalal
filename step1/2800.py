import sys
input= sys.stdin.readline
import collections
import copy
string = list(input().strip())
stack = collections.deque([])
dictionary = collections.defaultdict(list)

idx = 0
dictionary_idx = 0

while idx < len(string):
    if string[idx] == '(':
        stack.append((idx,dictionary_idx))
        dictionary_idx += 1
        idx += 1
    elif string[idx] == ')':
        idx1, idx2 = stack.pop()
        dictionary[idx2].append(idx1)
        dictionary[idx2].append(idx)
        idx += 1
    else:
        idx += 1
length = 2**len(dictionary)
result = [copy.deepcopy(string) for _ in range(length)] 

for count in dictionary.keys():
    period = 2 ** (len(dictionary) - count)

    idx = 0
    replace1, replace2 = dictionary[count]
    for i in range(0, length, period):
        for j in range(period//2):
            
            result[i+j][replace1] = ' '
            result[i+j][replace2] = ' '

for i in range(len(result)):
    result[i] = ''.join(result[i]).replace(' ', '')
result = list(set(result[:-1]))
result = sorted(result)
for i in result:
    print(i)
    
