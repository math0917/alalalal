import sys
input = sys.stdin.readline
import collections

n = int(input())
string = input().strip()
length = len(string)
info = collections.defaultdict(str)
result = set()
for count in range(1,n+1):
	
	newString = ''
	for i in range(0,length,2):
		newString += string[i]
	for i in reversed(range(1, length, 2)):
		newString += string[i]
	if newString not in result:
		result.add(newString)
		info[count]=newString
		string = newString
	
	else:
		if n % (count - 1) == 0:
			print(*info[count-1], sep='')
			sys.exit()
		print(*info[n % (count - 1)], sep = '')
		sys.exit()
print(*string, sep='')
           