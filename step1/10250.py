import sys

n = int(sys.stdin.readline())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

for i in arr:
    count = 1
    height = i[0]
    width = i[1]
    client = i[2]
    while(client > height):
        client = client - height
        count +=1
    floor = str(client)
    if count<10:
        ho = '0'+str(count)
    else:
        ho = str(count)
    print(floor+ho)