# InvoicingAPI

# 如果要執行UT
- 還沒時間修改，所以請先將ClientController/StoreController內的@PreAuthorize("hasRole('XXX')")註解掉
- 修改面向為可在測試程式執行時，先要一組Token，然後才開始進行UT測試

執行後，先於DB執行以下SQL，建立腳色與權限
(參考網址 : http://javasampleapproach.com/spring-framework/spring-security/use-spring-security-jdbc-authentication-postgresql-spring-boot)

> INSERT INTO users(username,password,enabled) VALUES ('admin','admin', true);
> INSERT INTO users(username,password,enabled) VALUES ('client','client', true);

> INSERT INTO user_roles (user_role_id, username, role) VALUES (1, 'admin', 'ROLE_ADMIN');
> INSERT INTO user_roles (user_role_id, username, role) VALUES (2, 'client', 'ROLE_USER');
> -- INSERT INTO user_roles (user_role_id, username, role) VALUES (3, 'admin', 'ROLE_USER');

# 管理員身分
可透過
- http://localhost:8080/oauth/token
> Postman 請在 body 內選擇 x-www-form-urlencoded 的模式 (https://tools.ietf.org/html/rfc6749#section-4.1.3)
![管理端body參數](https://i.imgur.com/ubGbgde.jpg)
- 拿到管理員的 access_token
- 再執行管理端的商品Service

# 進貨api
http://localhost:8080/purchase/{id}
![](https://i.imgur.com/9SJPxiJ.jpg)

# 列表api
http://localhost:8080/list

# 客戶身分
可透過
http://localhost:8080/oauth/token
> Postman 請在 body 內選擇 x-www-form-urlencoded 的模式
![客戶端body參數](https://i.imgur.com/HI3p4vP.jpg)
- 拿到客戶的 access_token
- 再執行客戶Service

# 列出可訂購商品清單API
http://localhost:8080/client/getOrderableProductsList

# 下單API (支援多筆)
http://localhost:8080/client/orderProducts

# 列出目前所有訂單(管理員/客戶共用API)
http://127.0.0.1:8080/share/getOrderList
