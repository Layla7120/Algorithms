-- 코드를 입력하세요
SELECT *
from food_product f
where f.price = (
    SELECT max(p.price)
    from food_product p
)