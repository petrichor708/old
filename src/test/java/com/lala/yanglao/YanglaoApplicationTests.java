package com.lala.yanglao;

import com.lala.yanglao.dao.OldDao;

import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.NurseService;
import com.lala.yanglao.unitl.days;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class YanglaoApplicationTests {

    @Autowired
    public OldDao oldDao;
    @Autowired
    NurseService nurseService;
    @Test
    void contextLoads() throws ParseException {
//        List<Old> oldList=oldDao.selectAll();
//        for (Old old:oldList){
//            System.out.println(old);
//        }
//        System.out.println(oldDao.selectByPrimary((long) 1));
//        List<Old> list=nurseService.oldNurseLists((long) 3);
//        System.out.println(list.get(0));
//        System.out.println(list.isEmpty());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowDate=simpleDateFormat.format(new Date());
        int day= days.daysBetween("2021-1-14",nowDate);
        System.out.println(day);
    }

}
