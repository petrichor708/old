package com.lala.yanglao.web.admin;

import com.lala.yanglao.MyException;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.unitl.days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCostController {
    @Autowired
    CostService costService;
    @Autowired
    OldService oldService;

    @RequestMapping("/costManage")
    public String costManage(Model model){
        System.out.println("访问后台费用管理");
        model.addAttribute("extraCost",costService.extraCharges());
        return "/admin/costManage";
    }

    @RequestMapping("extraCostUpdate")
    public String extraCostUpdate(Model model,double extraCharges){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowDate=simpleDateFormat.format(new Date());
        double charge = 0;
        int result=0,result4=0,result2=0,result3=0;
        if (extraCharges!=costService.extraCharges()){
            List<Old> olds=oldService.oldLists();
            for (Old old:olds){
                try {
                    int day= days.daysBetween(old.getUpdateCostTime(),nowDate);
                    charge=day*(costService.costByOldId(old.getId()).getnCost()+costService.costByOldId(old.getId()).getrCost()+costService.costByOldId(old.getId()).getExtraCharges())/365;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                result4=costService.setChargeByOldId(charge+old.getCharge(),old.getId());
                result2=costService.setUpdateCostTimeToCurrent(old.getId());
                result3=costService.setAllChargeByOldId(charge+old.getAllCharge(),old.getId());
                if (result2!=1||result3!=1||result4!=1){
                    throw new MyException("修改失败");
                }
            }
        }else {
            model.addAttribute("extraCost",costService.extraCharges());
            model.addAttribute("message","请修改费用数据再提交！");
            return "/admin/costManage";
        }
        result=costService.setExtraCharges(extraCharges);
        if (result==1){
            model.addAttribute("extraCost",costService.extraCharges());
            model.addAttribute("message","修改成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("修改失败");
        }
        return "/admin/costManage";
    }
}
