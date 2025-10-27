USE seat_system;

-- 指派座位
DROP PROCEDURE IF EXISTS sp_assignSeat;
DELIMITER //

CREATE PROCEDURE sp_assignSeat(
  IN p_emp_id VARCHAR(5),
  IN p_seat_seq VARCHAR(10)
)
BEGIN
  DECLARE v_cnt INT DEFAULT 0;

  -- 錯誤處理：若發生錯誤則回滾交易
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Seat assignment failed';
  END;

  START TRANSACTION;

  -- 確認座位存在
  SELECT COUNT(*) INTO v_cnt
  FROM seatingchart
  WHERE FLOOR_SEAT_SEQ = p_seat_seq;

  IF v_cnt = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Seat not found';
  END IF;

  -- 清除該座位的舊使用者
  UPDATE employee
     SET FLOOR_SEAT_SEQ = NULL
   WHERE FLOOR_SEAT_SEQ = p_seat_seq;

  -- 清除該員工的舊座位
  UPDATE employee
     SET FLOOR_SEAT_SEQ = NULL
   WHERE EMP_ID = p_emp_id;

  -- 指派新座位
  UPDATE employee
     SET FLOOR_SEAT_SEQ = p_seat_seq
   WHERE EMP_ID = p_emp_id;

  COMMIT;
END;
//
DELIMITER ;


-- 🔹 清除座位
DROP PROCEDURE IF EXISTS sp_clearSeat;
DELIMITER //

CREATE PROCEDURE sp_clearSeat(
  IN p_seat_seq VARCHAR(10)
)
BEGIN
  -- 錯誤處理：若發生錯誤則回滾交易
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Seat clear failed';
  END;

  START TRANSACTION;

  UPDATE employee
     SET FLOOR_SEAT_SEQ = NULL
   WHERE FLOOR_SEAT_SEQ = p_seat_seq;

  COMMIT;
END;
//
DELIMITER ;
