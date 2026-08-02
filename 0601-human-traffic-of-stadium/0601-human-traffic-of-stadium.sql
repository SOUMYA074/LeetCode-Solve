# Write your MySQL query statement below
SELECT DISTINCT s.*
FROM Stadium s
JOIN Stadium s1
JOIN Stadium s2
WHERE
(
    s.id = s1.id - 1
    AND s1.id = s2.id - 1
    AND s.people >= 100
    AND s1.people >= 100
    AND s2.people >= 100
)
OR
(
    s.id = s1.id + 1
    AND s.id = s2.id - 1
    AND s.people >= 100
    AND s1.people >= 100
    AND s2.people >= 100
)
OR
(
    s.id = s1.id + 2
    AND s.id = s2.id + 1
    AND s.people >= 100
    AND s1.people >= 100
    AND s2.people >= 100
)
ORDER BY s.visit_date;