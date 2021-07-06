import sys


N = int(sys.stdin.readline())

real =list(map(int,sys.stdin.readline().split()))

set_real = set(real)

list_real = list(set_real)

real1 = sorted(list_real)

dictionary = dict()

count = 0
for i in real1:
    dictionary[i] = count
    count += 1

for i in real:
    print(dictionary[i],'',end='')