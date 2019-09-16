package com.example.weixindemo.pojo;

public class AccessToken {

    // 获取到的token凭证
    private String tokenName;

    // 凭证有效时间，单位：秒
    private int expireSecond;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public int getExpireSecond() {
        return expireSecond;
    }

    public void setExpireSecond(int expireSecond) {
        this.expireSecond = expireSecond;
    }
}
