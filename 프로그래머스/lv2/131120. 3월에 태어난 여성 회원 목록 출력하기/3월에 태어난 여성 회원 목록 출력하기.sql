-- 코드를 입력하세요
SELECT m.member_id, m.member_name, m.gender, DATE_format(m.date_of_birth, "%Y-%m-%d")
from member_profile m
where month(date_of_birth) = 3 and gender = 'w' and tlno is not null order by member_id asc;