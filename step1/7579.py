import sys



n, m = map(int,sys.stdin.readline().split())

memory = list(map(int,sys.stdin.readline().split()))
bytes = list(map(int,sys.stdin.readline().split()))

dp = []
ever_since_bytes = 0
dp.append([])

for _ in range(n):
    dp[0].append(0)

while True:
    print(dp)
    dp.append([])
    this_turn_row = ever_since_bytes + 1
    if ever_since_bytes - bytes[0] >= 0:
        dp[this_turn_row].append(dp[ever_since_bytes- bytes[0]][0] + memory[0])
    else:
        dp[this_turn_row].append(0)
   
    for i in range(1,n):
        dp[this_turn_row].append(dp[this_turn_row][i-1])

        if ever_since_bytes - bytes[i] >= 0:
            dp[this_turn_row][i] = max(dp[this_turn_row][i], dp[ever_since_bytes-bytes[i]][i] + memory[i])
        if dp[this_turn_row][i] >= m:
            print(dp)
            print(ever_since_bytes)
           
            sys.exit()
    ever_since_bytes += 1
