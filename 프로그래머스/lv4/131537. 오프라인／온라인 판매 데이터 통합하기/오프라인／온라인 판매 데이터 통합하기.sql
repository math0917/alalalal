-- 코드를 입력하세요
SELECT date_format(sales_date,"%Y-%m-%d") sales_date, product_id, user_id, sales_amount
from online_sale o
where month(sales_date) = 3
union
select date_format(sales_date,"%Y-%m-%d") sales_date, product_id, null, sales_amount
from offline_sale
where month(sales_date) = 3
order by sales_date asc, product_id asc, user_id asc;