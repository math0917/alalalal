import sys
input = sys.stdin.readline

n = int(input())

arr = [list(map(str,input().strip())) for _ in range(n)]
count = 0
for word in arr:
    this_turn_dict = set()
    before = word[0]
    this_turn_dict.add(word[0])
    for alpha in word:
        if before == alpha:
            continue
        else:
            if alpha in this_turn_dict:
                break
            else:
                before = alpha
                this_turn_dict.add(alpha)
    else:
        
        count+=1
print(count)
