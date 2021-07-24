import sys

class pack:
    def __init__(self,weight,price):
        self.weight = weight
        self.price = price

n,m = map(int,sys.stdin.readline().split())

arr = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

inf = dict()

for i in arr:
    inf[i[0]]= i[1]
print(inf)

result=[pack(arr[0][0],arr[0][1])*n]
print(result[0])
