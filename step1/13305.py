import sys



n = int(sys.stdin.readline())

length = list(map(int,sys.stdin.readline().split()))

cost = list(map(int,sys.stdin.readline().split()))

result =[]

result.append([(0,length[0]*cost[0])])

for i in range(1,n-1):
    result.append([])
    for j in result[i-1]:
        from_s = j[0]
        cost_s = j[1]
        if cost_s + cost[i] * length[i] < cost_s + cost[from_s] * length[i] :
            result[i].append((i, cost_s+cost[i]*length[i]))
        else:
            result[i].append((from_s, cost_s+ cost[from_s]*length[i]))
min_S = result[-1][0][1]
for i in result[-1]:
    if i[1] < min_S:
        min_S = i[1]
print(min_S)