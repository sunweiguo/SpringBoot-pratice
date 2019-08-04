package com.tygq.demo04.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author swg.
 * @Date 2019/6/26 10:04
 * @CONTACT 317758022@qq.com
 * @DESC 生成32位work_id
 */
public class SequenceGenUtil {
    //线程安全：atomicInteger
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    /**8位系统标识+6位地区编码+13位时间戳+自增序列与一万取余（不足4位自动补零） 一共最多31位(区别在于中心地区码只有两位)
     * @param systemid 系统标识
     * @param areano 地区编码
     * @return atomicInteger从1开始递增，与十万进行取余
     */
    public static String getWorkId(String systemid,String areano){
        int inc = atomicInteger.getAndIncrement()%10000;
        String incStr = String.format("%04d",inc);
        return systemid + areano + System.currentTimeMillis() + incStr;
    }

    /**
     * 生成指定长度的纯数字字符串
     * @param length
     * @return
     */
    public static String getLengthNumberString(int length){
        // 指定位数字数组
        int[] number = new int[length];
        // 循环变量
        int i = 0;
        //最后返回的数字
        String endNumber = "";
        // 生成指定位随机数算法
        for (i = 0; i < length; i++) {
            if (number[i] == 0) {
                // 产生0-10之间的随机小数，强制转换成正数
                number[i] = (int) (Math.random() * 10);
            }
            // 输出数字
            endNumber += number[i]+"";
        }

        return endNumber;
    }

    public static void main(String[] args) {
        for(long i=0;i<100001;i++){
            System.out.println(getWorkId("99000001","320000"));
        }
    }
}
