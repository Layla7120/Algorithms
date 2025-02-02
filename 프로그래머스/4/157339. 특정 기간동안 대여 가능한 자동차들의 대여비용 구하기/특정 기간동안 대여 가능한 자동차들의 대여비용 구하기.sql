SELECT C.CAR_ID, C.CAR_TYPE, FLOOR(C.DAILY_FEE * 30 * (1 - D.DISCOUNT_RATE / 100))  AS FEE
FROM CAR_RENTAL_COMPANY_CAR C
LEFT OUTER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY R
ON C.CAR_ID = R. CAR_ID
LEFT OUTER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN D
ON C.CAR_TYPE = D.CAR_TYPE AND
    D.DURATION_TYPE = '30일 이상'
WHERE (C.CAR_TYPE = '세단' OR C.CAR_TYPE = 'SUV') 
    AND C.CAR_ID NOT IN (SELECT CAR_ID 
                                 FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                                WHERE NOT (END_DATE < '2022-11-01' OR 
                                           '2022-11-30' < START_DATE))
    AND ((C.DAILY_FEE * 30 * (100 - D.DISCOUNT_RATE) / 100) BETWEEN 500000 AND 2000000 OR 
         (C.DAILY_FEE * 30) BETWEEN 500000 AND 2000000)
GROUP BY C.CAR_ID
ORDER BY 
    FEE DESC, 
    C.CAR_TYPE ASC, 
    C.CAR_ID DESC;