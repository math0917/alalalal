import sys
N = int(input())

arr = []
num = 666
while(1):
    num = str(num)
    if num.count('6') <3:
        num = int(num)+1
        continue
    count = 0
    for i in num:
        if i=='6':
            count +=1
            if count == 3:
                arr.append(int(num))
                if len(arr) == N:
                    print(arr[-1])
                    sys.exit()
        else:
            count = 0
    num = int(num)+1