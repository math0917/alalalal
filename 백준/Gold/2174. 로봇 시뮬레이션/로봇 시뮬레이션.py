import sys
input = sys.stdin.readline
import collections

dx = [0,1,0,-1]
dy = [1,0,-1,0]

robotLoc = collections.defaultdict(list)

a, b = map(int, input().split())
n, m = map(int, input().split())

robot = [[0] * b for _ in range(a)]

for robotNum in range(1,n + 1):
	row, col, dir = input().split()
	if dir == 'E':
		dir = 1
	elif dir == 'S':
		dir = 2
	elif dir == 'W':
		dir = 3
	else:
		dir = 0
	robotLoc[robotNum].append(int(row) - 1)
	robotLoc[robotNum].append(int(col) - 1)
	robotLoc[robotNum].append(dir)
	robot[int(row) - 1][int(col) - 1] = robotNum
for _ in range(m):
	robotNum, proc, count = input().split()
	
	for _ in range(int(count)):
		if proc == 'L':
			robotLoc[int(robotNum)][2] = (robotLoc[int(robotNum)][2] -1 + 4) %4 
		elif proc == 'R':
			robotLoc[int(robotNum)][2] = (robotLoc[int(robotNum)][2] +1) %4 
		else:
			thisRow = robotLoc[int(robotNum)][0]
			thisCol = robotLoc[int(robotNum)][1]
			dir = robotLoc[int(robotNum)][2]

			row = thisRow + dx[dir]
			col = thisCol + dy[dir]
			if 0 <= row < a and 0 <= col < b:
				
				if robot[row][col] == 0:
					robot[thisRow][thisCol] = 0
					robot[row][col] = int(robotNum)	
					robotLoc[int(robotNum)][0] = row
					robotLoc[int(robotNum)][1] = col
				else:
					print('Robot',robotNum, 'crashes into robot',robot[row][col] )
					sys.exit(0)
			else:
				print('Robot', robotNum, 'crashes into the wall' )
				sys.exit(0)
print('OK')
