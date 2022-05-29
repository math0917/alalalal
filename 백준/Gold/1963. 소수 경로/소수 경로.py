import sys
import collections
input = sys.stdin.readline

T = int(input())

test_case = [list(map(int,input().split())) for _ in range(T)]
ten = set([0,1,2,3,4,5,6,7,8,9])
is_prime = [True]*10000

idx = 2
prime = set()
while idx < 10000:
    if is_prime[idx] == True:
        prime.add(idx)
        this_turn_idx = idx + idx
        while this_turn_idx < 10000:
            is_prime[this_turn_idx] = False
            this_turn_idx += idx
    idx += 1
prime = prime-set(range(1000))
for start, aim in test_case:
    visited = collections.defaultdict(lambda : False)
    q = collections.deque([(start,0)])
    visited[start] = True
    while q:
        this_turn_num, depth = q.popleft()
        if this_turn_num == aim:
            print(depth)
            break
        original = str(this_turn_num)
        for i in range(4):
            this_turn_num = original
            for j in ten:
                this_turn_num = this_turn_num[:i]+str(j)+this_turn_num[i+1:]
                if int(this_turn_num) in prime and not visited[int(this_turn_num)]:
                    visited[int(this_turn_num)] = True
                    q.append((int(this_turn_num),depth+1))
        
    else:
        print('Impossible')
