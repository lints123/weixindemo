20190927
1、定义菜单管理平台，新建菜单，删除菜单。
2、规范代码
参考网址：创建菜单-https://blog.csdn.net/weixin_39220472/article/details/83932231

20190929
文件夹定义
menu：定义菜单的实体类
wxsend：定义微信发送过来的xml模板的实体类
wxevent：定义微信的事件
clisend：定义客户端发送给微信服务器的实体类

遇到问题：
toUserName和FromUserName没有理清，导致一直发送不成功，以为是实体类循序问题导致。后面才发现存反了。
微信公众号发送给客户端：toUserName -> 公众号的账号； FromUserName -> 你的openId
客户端发送给微信公众号：toUserName -> 你的openId； FromUserName -> 公众号的账号；
