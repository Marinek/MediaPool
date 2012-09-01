SELECT 
  *
FROM
   entities p,
   entities m,
   relationships r
WHERE
    p.id = r.parentid
    and m.id = r.childid