# InvoicingAPI

執行後

# 管理員身分
可透過
http://localhost:8080/oauth/token?grant_type=password&username=admin&password=admin
Postman 請選擇 Authiruzation/Type(Basic Auth)/Username:my-trusted-client/Password:secret
拿到管理員的 access_token
再執行管理端的商品Service

# 進貨api
http://localhost:8080/purchase/{id}?access_token=[管理員的access_token]

# 列表api
http://localhost:8080/list?access_token=[管理員的access_token]

# 客戶身分
可透過
http://localhost:8080/oauth/token?grant_type=password&username=client&password=client
Postman 請選擇 Authiruzation/Type(Basic Auth)/Username:my-trusted-client/Password:secret
拿到客戶的 access_token
再執行客戶Service

# 列出可訂購商品清單API
http://localhost:8080/client/getOrderableProductsList?access_token=[客戶的access_token]

# 下單API (支援多筆)
http://localhost:8080/client/orderProducts?access_token=[客戶的access_token]

# 列出目前所有訂單(管理員/客戶共用API)
http://127.0.0.1:8080/share/getOrderList?access_token=[管理員/客戶的access_token]
