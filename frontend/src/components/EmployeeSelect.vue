<script setup>
import { ref, computed, onMounted } from 'vue';
import { SeatAPI } from '@/services/api';

const emit = defineEmits(['update:employeeId']);
const employees = ref([]);
const selected = ref('');
const showInfo = ref(false);
const loading = ref(true);
const error = ref('');

onMounted(async () => {
  try {
    employees.value = await SeatAPI.getEmployees();
  } catch (err) {
    error.value = '無法載入員工資料';
    console.error(err);
  } finally {
    loading.value = false;
  }
});

const currentEmp = computed(() => employees.value.find(e => e.empId === selected.value));

const onChange = () => {
  emit('update:employeeId', selected.value);
  showInfo.value = false;
};
const toggleInfo = () => (showInfo.value = !showInfo.value);
</script>

<template>
  <div class="employee-select">
    <label>選擇員工：</label>

    <div v-if="loading">載入中...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="dropdown">
      <select v-model="selected" @change="onChange" class="select">
        <option value="">-- 選擇員工編號 --</option>
        <option v-for="e in employees" :key="e.empId" :value="e.empId">
          {{ e.empId }}（座位：{{ e.floorSeatSeq || '未分配' }}）
        </option>
      </select>
      <button v-if="selected" class="info-btn" @click="toggleInfo">
        {{ showInfo ? '▲' : '▼' }}
      </button>
    </div>

    <div v-if="showInfo && currentEmp" class="info">
      <p>姓名：{{ currentEmp.name }}</p>
      <p>Email：{{ currentEmp.email }}</p>
    </div>
  </div>
</template>

<style scoped>
.error { color: red; margin-top: 5px; }
</style>
