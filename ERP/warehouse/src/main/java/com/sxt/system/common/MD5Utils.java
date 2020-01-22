package com.sxt.system.common;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @author song
 * @data 2020/1/20
 */
public class MD5Utils {
    /**
     * 生成uuid
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 加密
     * @param soures 明文加密
     * @param slat 盐
     * @param hashIterations 散列次数
     * @return
     */
    public static String md5(String soures,String slat,Integer hashIterations){
        Md5Hash hash=new Md5Hash(soures,slat,hashIterations);
        return hash.toString();
    }
}
