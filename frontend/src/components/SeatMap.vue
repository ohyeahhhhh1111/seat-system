<template>
  <div class="seatmap">
    <!-- 逐層樓顯示 -->
    <div
      v-for="(floorSeats, floorNo) in seats"
      :key="floorNo"
      class="floor"
    >
      
      <!-- 每層樓的座位格子 -->
      <div class="grid">
        <button
          v-for="s in floorSeats"
          :key="s.floorSeatSeq"
          class="seat"
          :class="{
            occupied: s.occupied, // 座位被佔用
            empty: !s.occupied && selectedSeat !== s.floorSeatSeq, // 空位
            selected: selectedSeat === s.floorSeatSeq, // 使用者選中的座位
          // 不能選取其他人的座位
          }"
          :disabled="s.occupied && s.empId !== selectedEmpId" 
          @click="onClick(s)"
        >
          <!-- 顯示座位基本資訊 -->
          <div><b>{{ s.floorNo }} 樓：</b>座位 {{ s.seatNo }}</div>
          <!-- 已佔用 -->
          <div v-if="s.occupied">[員編：{{ s.empId }}]</div>
          <!-- 正在被選取 -->
          <div v-else-if="selectedSeat === s.floorSeatSeq">請選擇</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  seats: { type: Object, required: true }, // 巢狀物件
  selectedSeat: String,
  selectedEmpId: String,
});

const emit = defineEmits(['select-seat']);

// 點選座位事件
const onClick = (s) => emit('select-seat', s);
</script>

<style scoped>
.floor {
  margin-bottom: 16px;
}

h3 {
  margin-bottom: 8px;
  font-size: 18px;
  color: #333;
}

.grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 8px;
}

.seat {
  border-radius: 8px;
  border: 1px solid #000;
  padding: 12px;
  text-align: center;
  cursor: pointer;
  font-weight: 600;
}

.empty {
  background: #f0f0f0;
  color: #000;
}

.occupied {
  background: #ff0000;
  color: #fff;
}

.selected {
  background: #00ff00;
  color: #000;
}
</style>
