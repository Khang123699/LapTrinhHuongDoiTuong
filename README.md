Vào MySQL Workbench, sau đó chọn data import, chọn Import from self-contained file, chọn file trên rồi chọn schemas cần import rồi chọn Import data là ok
Sau đó trên eclipse kiếm file application.properties chỉnh như sau:

spring.application.name=tên schemas của bạn
spring.datasource.url=jdbc:mysql://localhost:3306/shop_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=tên loginname
spring.datasource.password= Mật khẩu
Sau đó tìm file ClothesShopApplication.java, nhấn vào tên file rồi nhấn Run
Chạy thành công thì lên google nhập link http://localhost:8080/ để test
