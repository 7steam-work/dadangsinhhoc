# RUN API
1. Đóng gói api

```cmd
mvn clean package
```

2. Build lên docker

```cmd
docker-compose up --build -d
```


## For dev:
Cưỡng chế lấy code trên remote (bỏ qua hết thay đổi trên local):
```cmd
git fetch --all
git reset --hard origin/<branch_name>
```

Thông mạng:
```cmd
ssh -i key.pem -L 80:localhost:80 username@ip-addrest
```
