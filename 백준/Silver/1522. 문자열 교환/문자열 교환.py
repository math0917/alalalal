import sys
input = sys.stdin.readline

string = input().strip()
swnd = string.count('a')
min_count = float('inf')
for i in range(len(string)):
    count = 0
    for j in range(i, i+swnd):
        num = j
        if j >= len(string):
            num = j-len(string)
        if string[num] == 'b':
            count += 1
    min_count = min(count , min_count)
print(min_count)