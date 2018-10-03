# 数据库文档

## 目录

- 账户
  - [1.用户表](#test)
- 兼职
- 其他

## 账户

#### 1.用户表

- 表名

  - user

- 表字段

  | 字段名          | 类型    | 长度 | 默认值   | 备注                       |
  | --------------- | ------- | ---- | -------- | -------------------------- |
  | id              | int     | 11   | Not Null | 主键, 自增                 |
  | username        | varchar | 20   | Not Null | 用户名`需要唯一约束unique` |
  | password        | varchar | 100  | Not Null | 密码                       |
  | email           | varchar | 50   | Null     | 邮箱`预留字段`             |
  | phone           | varchar | 20   | Null     | 手机号`预留字段`           |
  | status          | int     | 1    | 0        | 状态`预留字段`             |
  | last_sign_in_at | date    |      | Not Null | 最后一次登陆时间           |
  | created_at      | date    |      | Not Null | 记录创建日期               |
  | updated_at      | date    |      | Not Null | 记录最后一次修改日期       |


