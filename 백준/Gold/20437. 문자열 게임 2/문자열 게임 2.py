import sys
import collections
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    sentence = input().strip()
    aim = int(input())
    dic = collections.defaultdict(list)
    min_val = float('inf')
    max_val = 0
    for idx, value in enumerate(sentence):
        dic[value].append(idx)

    for idx_lst in dic.values():
        if len(idx_lst)>=aim:
            for j in range(len(idx_lst)-aim+1):
                temp = idx_lst[j+aim-1] - idx_lst[j] + 1

                if temp < min_val:
                    min_val = temp

                if temp > max_val:
                    max_val  = temp

    if max_val == 0:
        print(-1)
    else:
        print(min_val, max_val)
        
    
