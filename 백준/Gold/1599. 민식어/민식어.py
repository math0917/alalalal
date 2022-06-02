import sys
input = sys.stdin.readline

n = int(input())

list = []

for _ in range(n):
    list.append(input().strip())

order = ['a' ,'b' ,'k' ,'d' ,'e' ,'g' ,'h' ,'i' ,'l' ,'m' ,
'n', 'ng', 'o' ,'p' ,'r', 's' ,'t' ,'u' ,'w', 'y']
# t
alphabet = ['a','b','c','d','e', 'f', 'g','h','i','j','k','l','m','n','o','p','q','r','s','t']
new_list = []
for i in range(n):
    string = ''
    idx = 0
    flag = 0
    while idx < len(list[i]):
        if flag: 
            if list[i][idx] == 'g':
                flag = 0
                string += alphabet[11]
                idx += 1
                continue
            else:
                flag = 0
                string += alphabet[10]
                continue
        if order.index(list[i][idx]) == 10:
            flag = 1
            idx += 1
            continue
        else:
            string += alphabet[order.index(list[i][idx])]
            idx+=1
            continue
    if flag :
        string += alphabet[10]
    new_list.append(string)

sort_new_list = sorted(new_list)
for i in range(n):
    print(list[new_list.index(sort_new_list[i])])
            
        