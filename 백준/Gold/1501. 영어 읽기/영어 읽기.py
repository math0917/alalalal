import sys
input = sys.stdin.readline
import collections

N = int(input())

dictionary = [input().strip() for _ in range(N)]

M = int(input())

words = [input().strip() for _ in range(M)]

dict = collections.defaultdict(list)

for i in dictionary:
    if len(i) == 1:
        index = i[0]
        dict[index].append(collections.defaultdict(int))
        continue
    index = i[0]+i[-1]
    this_turn_dict = collections.defaultdict(int)
    for j in i[1:-1]:
        this_turn_dict[j] += 1
    dict[index].append(this_turn_dict)
    

for i in words:
    real_count = 1
    for real_word in i.split():
        count = 0
        if len(real_word) == 1:
            index = real_word[0]
        else:
            index = real_word[0]+real_word[-1]
            word = real_word[1:-1]
        for j in dict[index]:
            dict_length = 0
            for key, value in j.items():
                if word.count(key) != value:
                    break
                dict_length += value
            else:
                if len(real_word) == 1:
                    count += 1
                    continue
                if dict_length == len(word):
                    count+=1
        real_count *= count
    print(real_count)