import sys
input = sys.stdin.readline

password = list(map(int,input().strip()))
k = int(input()) - 1

lst = []
for idx, value in enumerate(password):
    if value == 1 or value == 2 or value == 7 or value == 6:
        lst.append(idx)
lst.reverse()

if 2**len(lst)> k:
    for i in range(len(lst)):
        if k & (1<<i):
            if password[lst[i]] == 1:
                password[lst[i]] = 6
            elif password[lst[i]] == 2:
                password[lst[i]] = 7
        else:
            if password[lst[i]] == 6:
                password[lst[i]] = 1
            elif password[lst[i]] == 7:
                password[lst[i]] = 2
    for i in password:
        print(i,end='')
else:
    print(-1)

        