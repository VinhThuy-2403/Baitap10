

##Truy cập ứng dụng
- Mở trình duyệt: http://localhost:8092
- Sẽ tự động chuyển đến trang đăng nhập

##Đăng nhập với tài khoản mẫu

### Tài khoản Admin:
- **Username**: admin
- **Password**: admin123
- **Sau khi đăng nhập**: Sẽ vào trang admin với quyền quản lý danh mục và sản phẩm

### Tài khoản User:
- **Username**: user  
- **Password**: user123
- **Sau khi đăng nhập**: Sẽ vào trang user thông thường

##Kiểm tra phân quyền
1. **Đăng nhập với admin**: Có thể truy cập `/admin/dashboard`, `/admin/categories`, `/admin/products`
2. **Đăng nhập với user**: Chỉ có thể truy cập `/user/dashboard`
3. **Chưa đăng nhập**: Truy cập `/admin/**` hoặc `/user/**` sẽ bị chuyển về `/login`

## Bước 5: Kiểm tra Validator
1. Thử đăng ký tài khoản mới với thông tin không hợp lệ
2. Thử đăng nhập với thông tin sai
3. Xem các thông báo lỗi validation

## Bước 6: Kiểm tra H2 Database
- Truy cập: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`
- Xem các bảng: USERS, CATEGORIES, PRODUCTS
