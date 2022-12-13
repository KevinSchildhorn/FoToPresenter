package me.kevinschildhorn.common.network.ftps

import me.kevinschildhorn.common.network.LoginInformation


fun TestingLoginInfo(
    host: String = "192.168.1.127",
    port: Int = 33,
    username: String = "fotopresenter",
    password: String = "8btgJg4st!Hq",
) = LoginInformation(host,port,username,password)