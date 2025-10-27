<template>
  <main class="container">
    <h2>● 員工座位安排系統</h2>

    <!-- 員工選擇下拉元件 -->
    <EmployeeSelect v-model:employeeId="selectedEmpId" />

    <!-- seatmap元件 -->
    <SeatMap
      :seats="seats"
      :selectedSeat="selectedSeat"
      :selectedEmpId="selectedEmpId"
      @select-seat="selectSeat"
    />

    <!-- 狀態 -->
    <div class="legend">
      <span><span class="dot empty"></span> 空位</span>
      <span><span class="dot occupied"></span> 已佔用</span>
      <span><span class="dot selected"></span> 請選擇</span>
    </div>

    <button class="btn" @click="submit" :disabled="!selectedEmpId || !selectedSeat">
      送出
    </button>

    <p v-if="message" class="msg">{{ message }}</p>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import EmployeeSelect from '@/components/EmployeeSelect.vue'
import SeatMap from '@/components/SeatMap.vue'
import { SeatAPI } from '@/services/api'

const seats = ref([]); // 所有座位(依樓層分組)
const selectedEmpId = ref(''); // 選中的員工編號
const selectedSeat = ref(null); // 選中的座位序號
const message = ref(''); // 操作回饋訊息

onMounted(async () => {
  seats.value = await SeatAPI.getSeats();
});

const selectSeat = (seat) => {
  // 若選中員工，將舊座位變空
  if (!selectedEmpId.value) {
    message.value = '請先選擇員工';
    return;
  }

  // 更新座位狀態
  for (const floor in seats.value) {
    seats.value[floor] = seats.value[floor].map(s => {
      // 若該座位原屬於該員工 → 釋放座位
      if (s.empId === selectedEmpId.value)
        return { ...s, empId: null, occupied: false };
      // 若點選的新座位 → 標示已佔用
      if (s.floorSeatSeq === seat.floorSeatSeq)
        return { ...s, empId: selectedEmpId.value, occupied: true };
      return s;
    });
  }

  selectedSeat.value = seat.floorSeatSeq;
};

const submit = async () => {
  await SeatAPI.assignSeat(selectedEmpId.value, selectedSeat.value);
  seats.value = await SeatAPI.getSeats();
  selectedSeat.value = null;
  message.value = '座位更新成功';
};
</script>

<style scoped>
.container { padding: 20px; font-family: "Microsoft JhengHei", Arial; }
.legend { margin: 12px 0; display: flex; gap: 16px; }
.dot { width: 12px; height: 12px; display: inline-block; border-radius: 50%; border: 1px solid #000; margin-right: 4px; }
.empty { background: #fff; }
.occupied { background: #ff0000; }
.selected { background: #00ff00; }
.btn { padding: 8px 16px; background: #0033cc; color: #fff; border: none; border-radius: 6px; cursor: pointer; }
.msg { margin-top: 12px; color: #16a34a; }
</style>
