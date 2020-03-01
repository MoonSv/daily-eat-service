# daily-eat-service

#### `POST /auth/register`

功能: 用户注册

提交参数

- 参数类型:`Content-Type: application/json;charset=UTF-8`
- 参数字段
  - `username` : 用户名, 长度1到15个字符，只能是字母数字下划线中文
  - `password` : 密码, 长度6到16个任意字符

返回数据

- 失败

  - 返回格式

     ```json
    {
        "status": "fail",
        "msg": "username already exists",
        "user": null,
        "logIn": false
    }
     ```

    

     或者

     ```json
    {
        "status": "fail",
        "msg": "username'length should between 5 and 20",
        "user": null,
        "logIn": false
    }
     ```

    

    或者

    ```json
    {
        "status": "fail",
        "msg": "password'length should between 6 and 20",
        "user": null,
        "logIn": false
    }
    ```

    

- 成功

  - 返回格式

```javascript
{
    "status": "ok",
    "msg": "register success",
    "user": {
        "id": "4",
        "name": "zoegxy",
        "gender": null
    },
    "logIn": true
}
```

测试命令

```
# -i 可以展示响应头
# 会发现响应头里有 setCookie 信息，得到 cookie

curl -H "content-type:application/json" -d '{"username":"zoegxy","password"
:"777777"}' "http://localhost:8080/auth/register" -i
```

#### `POST /auth/login`

功能: 用户登录

提交参数

- 参数类型:`Content-Type: application/json;charset=UTF-8`
- 参数字段
  - `username` : 用户名, 长度1到15个字符，只能是字母数字下划线中文
  - `password` : 密码, 长度6到16个任意字符

返回数据

- 失败
  - 返回格式 `{"status": "fail", "msg": "Username does not exist"}` 或者 `{"status": "fail", "msg": "Wrong password"}`
- 成功
  - 返回格式

```javascript
{
    "status": "ok",
    "msg": "login success",
    "user": {
        "id": "1",
        "name": "77",
        "gender": "male"
    },
    "logIn": true
}
```

测试命令

```
# -i 可以展示响应头
# 会发现响应头里有 setCookie 信息，得到 cookie

curl -H "content-type:application/json" -d '{"username":"zoegxy","password"
:"password"}' "http://localhost:8080/auth/login" -i
```