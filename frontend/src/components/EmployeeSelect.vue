<script setup>
import { ref, computed, onMounted } from 'vue';
import { SeatAPI } from '@/services/api';

const emit = defineEmits(['update:employeeId']);
const employees = ref([]); // 員工清單
const selected = ref(''); // 當前選取的員工 ID
const showInfo = ref(false); // 是否顯示員工詳細資訊
const loading = ref(true); // 載入中狀態
const error = ref(''); // 錯誤訊息

onMounted(async () => {
  try {
    // 向後端 API 取得所有員工資料
    employees.value = await SeatAPI.getEmployees();
  } catch (err) {
    // 若發生錯誤，顯示錯誤訊息
    error.value = '無法載入員工資料';
    console.error(err);
  } finally {
    // 不論成功與否都結束 loading 狀態
    loading.value = false;
  }
});

const currentEmp = computed(() => employees.value.find(e => e.empId === selected.value));

//下拉選單事件：當使用者選擇新員工
const onChange = () => {
  emit('update:employeeId', selected.value);
  showInfo.value = false;
};

//切換員工詳細資訊顯示
const toggleInfo = () => (showInfo.value = !showInfo.value);
</script>

<template>
  <div class="employee-select">
    <label>選擇員工：</label>

    <!-- 顯示載入狀態 -->
    <div v-if="loading">載入中...</div>

    <!-- 顯示錯誤訊息 -->
    <div v-else-if="error" class="error">{{ error }}</div>

    <!-- 顯示下拉選單 -->
    <div v-else class="dropdown">
      <select v-model="selected" @change="onChange" class="select">
        <option value="">-- 選擇員工編號 --</option>
        <option v-for="e in employees" :key="e.empId" :value="e.empId">
          {{ e.empId }}（座位：{{ e.floorSeatSeq || '未分配' }}）
        </option>
      </select>
      <!-- 展開員工詳細資料按鈕 -->
      <button v-if="selected" class="info-btn" @click="toggleInfo">
        {{ showInfo ? '▲' : '▼' }}
      </button>
    </div>
    
    <!-- 顯示選中員工的詳細資訊 -->
    <div v-if="showInfo && currentEmp" class="info">
      <p>姓名：{{ currentEmp.name }}</p>
      <p>Email：{{ currentEmp.email }}</p>
    </div>
  </div>
</template>

<style scoped>
.error { color: red; margin-top: 5px; }
</style>
