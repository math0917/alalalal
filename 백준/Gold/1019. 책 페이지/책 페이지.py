import sys
input = sys.stdin.readline
import collections
def find(fr, to):
    dictionary = collections.defaultdict(int)
    if to // 10 == 0 :
        for i in range(fr, to+1):
            dictionary[i] += 1
        return dictionary
    this_turn_dict = find(fr, to//10)
    for i in range(10):
        count = to//10
        dictionary[i] += count
        dictionary[i] += 1 if i else 0
        dictionary[i] += 10 * this_turn_dict[i]
    for i in range(to+1, ((to//10)+1) * 10):
        string_i = str(i)
        for idx in string_i:
            dictionary[int(idx)] -= 1

    return dictionary
        
    

n = int(input())

dict = find(1,n)
for i in range(10):
    print(dict[i],'',end='')
