SELECT 
    n.id AS id_nganh,
    n.name AS ten_nganh,
    l.id AS id_lop,
    l.name AS ten_lop,
    b.id AS id_bo,
    b.name AS ten_bo,
    h.id AS id_ho,
    h.name AS ten_ho,
    loai.*
FROM 
    loai
    INNER JOIN ho h ON loai.idHo = h.id
    INNER JOIN bo b ON h.idBo = b.id
    INNER JOIN lop l ON b.idLop = l.id
    INNER JOIN nganh n ON l.idNganh = n.id
WHERE 
    n.id = 6
    AND l.id = 13
    AND b.id = 100
    AND h.id = 157
    AND loai.mucDoBaoTonIUCN = '0'
    AND loai.mucDoBaoTonSDVN = '0'
    AND loai.mucDoBaoTonNDCP = '0'
    AND loai.mucDoBaoTonND64CP = '0'
    AND loai.name LIKE '%test%'
    AND loai.nameLatinh LIKE '%D%'
    AND loai.rph = FALSE;