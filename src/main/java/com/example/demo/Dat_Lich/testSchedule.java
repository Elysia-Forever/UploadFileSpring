package com.example.demo.Dat_Lich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling // tầng chạy ngầm
public class testSchedule {
    @Autowired
    public testService testSer;

    // đặt lịch : không cần con người tác động mà nó sẽ được tự động thực hiện
    // đặt lịch cron job ==> là 1 mệnh đề
    //Spring boot schedule --> @Schedule
//    @Scheduled(fixedDelay = 2000)  2000mls chạy 1 lần
//    @Scheduled(cron = "0 9 25 * *") //9 giờ 0p ngày 25 hàng tháng
    public void datLich(){
        try {
            testSer.baoThuc();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testSchedule test = new testSchedule();
        test.datLich();
    }
}
