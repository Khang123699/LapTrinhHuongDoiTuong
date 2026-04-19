<meta charset="UTF-8">
# Hướng dẫn chạy project

## 1. Import Database

1. Mở MySQL Workbench  
2. Chọn **Server → Data Import**  
3. Chọn **Import from Self-Contained File**  
4. Chọn file `.sql` đã cung cấp  
5. Chọn schema (database) cần import  
6. Nhấn **Start Import**

---

## 2. Cấu hình project

Mở file `application.properties` và chỉnh sửa:

```properties
spring.application.name=ten_schema_cua_ban
spring.datasource.url=jdbc:mysql://localhost:3306/shop_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=ten_username
spring.datasource.password=mat_khau
```
## 3. Chạy project

1. Mở project bằng Eclipse  
2. Tìm file `ClothesShopApplication.java`  
3. Click chuột phải → **Run As → Java Application**

---

## 4. Kiểm tra

Sau khi chạy thành công, mở trình duyệt và truy cập:

http://localhost:8080/

##Lưu ý
- Đảm bảo MySQL đã chạy trước khi start project  
- Kiểm tra đúng username/password  
- Nếu lỗi port, kiểm tra port 8080 có bị trùng không
