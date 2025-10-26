USE seat_system;

-- 樓層座位資料
INSERT INTO seatingchart (FLOOR_SEAT_SEQ, FLOOR_NO, SEAT_NO) VALUES
('A01', 1, 1), ('A02', 1, 2), ('A03', 1, 3), ('A04', 1, 4),
('B01', 2, 1), ('B02', 2, 2), ('B03', 2, 3), ('B04', 2, 4),
('C01', 3, 1), ('C02', 3, 2), ('C03', 3, 3), ('C04', 3, 4),
('D01', 4, 1), ('D02', 4, 2), ('D03', 4, 3), ('D04', 4, 4);

-- 員工資料
INSERT INTO employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES
('12006', 'Alice Chen', 'alice@example.com', 'A03'),
('16142', 'Bob Lin', 'bob@example.com', 'B03'),
('11221', 'Cathy Wu', 'cathy@example.com', 'C04'),
('13040', 'Amber Wu', 'amber@example.com', 'C01'),
('17081', 'May Chen', 'may@example.com', 'C02'),
('16722', 'Tom Wung', 'tom@example.com', 'D03');

