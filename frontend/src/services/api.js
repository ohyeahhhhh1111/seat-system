/**
 * SeatAPI.js
 * 
 * - 與後端 REST API (Spring Boot) 溝通
 * - 提供統一的資料存取介面給 Vue 組件使用
 * 
 */
export const SeatAPI = {
  async getSeats() {
    const res = await fetch("http://localhost:8080/api/seats/grouped");
    if (!res.ok) throw new Error("無法取得座位資料");
    return await res.json();
  },

  async getEmployees() {
    const res = await fetch("http://localhost:8080/api/employees");
    if (!res.ok) throw new Error("無法取得員工資料");
    return await res.json(); // 回傳陣列
  },

  async assignSeat(empId, floorSeatSeq) {
    const res = await fetch("http://localhost:8080/api/employees/assign", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ empId, floorSeatSeq }), // 傳送 JSON 格式資料
    });

    if (!res.ok) throw new Error("座位指派失敗");
  },

};
