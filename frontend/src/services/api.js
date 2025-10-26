export const SeatAPI = {
  async getSeats() {
    const res = await fetch("http://localhost:8080/api/seats/grouped");
    if (!res.ok) throw new Error("無法取得座位資料");
    return await res.json();
  },

  async getEmployees() {
    const res = await fetch("http://localhost:8080/api/employees");
    if (!res.ok) throw new Error("無法取得員工資料");
    return await res.json();
  },

  async assignSeat(empId, floorSeatSeq) {
    const res = await fetch("http://localhost:8080/api/employees/assign", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ empId, floorSeatSeq }), // 🔹 key 名稱要對應後端
    });

    if (!res.ok) throw new Error("座位指派失敗");
  },

};
