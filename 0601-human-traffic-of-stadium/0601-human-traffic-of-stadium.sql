WITH cte AS (
    SELECT *,
           LAG(id,1) OVER(ORDER BY id) AS prev1,
           LAG(id,2) OVER(ORDER BY id) AS prev2,
           LEAD(id,1) OVER(ORDER BY id) AS next1,
           LEAD(id,2) OVER(ORDER BY id) AS next2
    FROM Stadium
    WHERE people >= 100
)

SELECT id, visit_date, people
FROM cte
WHERE
    (id = prev1 + 1 AND prev1 = prev2 + 1)
 OR (id = prev1 + 1 AND next1 = id + 1)
 OR (next1 = id + 1 AND next2 = next1 + 1)
ORDER BY visit_date;