# 🪑 員工座位安排系統（Seat System）

> 一套以 **Vue 3 + Spring Boot** 開發的員工座位管理平台  
> Made by **蔡瀞萱**

---

## 📘 系統介紹

本系統為一套 **員工座位管理平台**，  
讓管理者能快速查看各員工的座位分配情況，並可進行座位指派或清除操作。

- 前端：使用 **Vue 3 (Composition API)** 建構互動式操作介面  
- 後端：採用 **Spring Boot + MySQL** 提供 RESTful API 資料服務  
- 系統介面直覺、操作簡單，適用於公司、學校或共用辦公空間等座位管理需求。

---

## 🏗️ 系統架構

```text
使用者操作介面 (Vue.js)
        │
        ▼
前端 API 模組 (SeatAPI.js)
        │
        ▼
後端 RESTful API (Spring Boot)
        │
        ▼
資料庫 (MySQL)
```

---

## ⚙️ 技術重點

| 項目 | 技術 |
|------|------|
| 前端框架 | Vue 3 (Composition API) |
| 後端框架 | Spring Boot 3.5 |
| 資料存取 | Spring Data JPA |
| 資料庫 | MySQL 8 |
| API 格式 | JSON |
| 跨域設定 | `@CrossOrigin` + `CorsConfig` |
| 錯誤處理 | `GlobalExceptionHandler` |
| 單元測試 | JUnit 5 |

---

## 🚀 系統啟動說明

### 🔹 後端
```bash
mvn clean install
mvn spring-boot:run
```

### 🔹 前端
```bash
cd seat-system-frontend
npm install
npm run dev
```

> 預設運行位置：  
> - 後端：`http://localhost:8080`  
> - 前端：`http://localhost:5173`

---

## 🧩 主要功能

- 👤 **員工與座位管理**  
  可指派或清除特定員工座位。

- 🗺️ **座位地圖展示**  
  以圖形化方式顯示各座位狀態（空位、已佔用、選取中）。

- 🔍 **資料查詢與篩選**  
  支援依樓層或座位號快速查詢。

- ⚠️ **錯誤處理與提示機制**  
  操作不當會有錯誤提示，避免資料異常。

---

## 🔒 系統特色與資安考量

- **操作簡單**：以圖形化介面顯示座位狀態，一目了然  
- **即時互動**：指派座位後畫面立即更新  
- **RESTful API 權限隔離**：後端僅開放必要端點  
- **CORS 安全設定**：限制允許的前端來源  
- **輸入驗證**：防止 SQL Injection 與惡意請求  
- **前端防護**：避免 XSS（跨站腳本攻擊）  
- **架構清晰**：前後端分離、模組化設計

---

## 🔧 未來改進方向

- 🔎 增加員工搜尋與篩選功能  
- 🤖 新增「自動分配」演算法  
- 🕒 提供座位異動歷史紀錄查詢  
- 🔐 加入登入驗證與權限控管（LDAP / JWT）  
- 📱 優化響應式設計，支援手機版介面

---

## 🧠 補充說明

- 不需手動啟用 MySQL，Spring Boot 會自動建立連線。  
- 前端與後端皆可獨立啟動，也可整合部署。  
- 適合用於教學、面試展示或內部座位管理實作。

---

© 2025 Made by **蔡瀞萱** | 員工座位安排系統 Seat System
