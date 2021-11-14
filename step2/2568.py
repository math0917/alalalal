import sys

n = int(sys.stdin.readline())

status = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]
status.sort()

result = [float('inf')] * n
dictionary = dict()

for i in range(len(status)):
    this_turn = status[i]
    for i in range(n):
        if type(result[i]) == float:
            result[i] = this_turn[1]
            dictionary[this_turn[0]] = i
            break
        else:
            if result[i] > this_turn[1]:
                result[i] = this_turn[1]
                dictionary[this_turn[0]] = i
                break
count = 0
for i in range(n):
    if type(result[i]) == float:
        break
    else:
        count += 1
print(n-count)
count -= 1

find = []
for i in reversed(status):
    if dictionary[i[0]] == count:
        count-= 1
    else:
        find.append(i[0])
for i in reversed(find):
    print(i)
