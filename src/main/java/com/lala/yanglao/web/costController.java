package com.lala.yanglao.web;


import com.lala.yanglao.model.Cost;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.unitl.days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class costController {

    @Autowired
    CostService costService;
    @Autowired
    OldService oldService;

    @Transactional
    @RequestMapping("/costToZero/{oldId}")
    public String costToZero(Model model,@PathVariable Long oldId){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowDate=simpleDateFormat.format(new Date());
        double charge=0;
        Old old=oldService.oldOneById(oldId);
        try {
            int day= days.daysBetween(old.getUpdateCostTime(),nowDate);
            charge=day*(costService.costByOldId(old.getId()).getnCost()+
                    costService.costByOldId(old.getId()).getrCost()+
                    costService.costByOldId(old.getId()).getExtraCharges())/365;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result3=costService.setAllChargeByOldId(charge+old.getAllCharge(),oldId);
        int result=costService.setRepayDateToZero(oldId);
        int result1=costService.setUpdateCostTimeToCurrent(oldId);
        int result2=costService.setChargeByOldId(0,oldId);
        if (result==0||result1==0||result2==0||result3==0){
            model.addAttribute("message","缴费失败！");
            return "costInformation";
        }
        model.addAttribute("costs",costService.costLists(0));
        model.addAttribute("page",0);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ?
                oldService.oldLists().size()-10 :
                oldService.oldLists().size()/10*10);
        model.addAttribute("message","缴费成功！");
        model.addAttribute("message1",oldId);
        return "costInformation";
    }

    @RequestMapping("/costSearch")
    public String costSearch(Model model,String query){
        if (query.equals("")){
            model.addAttribute("message","请输入查找的关键字！");
            return "costInformation :: costList";
        }
        List<Cost> list=costService.costQuery(query);
        model.addAttribute("costs",list);
        model.addAttribute("page",0);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        if (list.isEmpty()){
            model.addAttribute("message","查无此人！");
        }else{
            model.addAttribute("message","查找成功！");
        }
        return "costInformation :: costList";
    }
    @RequestMapping("/pageNext/{page}")
    public String pageNext(Model model,@PathVariable int page){
        model.addAttribute("costs",costService.costLists(page+10));
        model.addAttribute("page",page+10);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        return "costInformation";
    }
    @RequestMapping("/pageLast/{page}")
    public String pageLast(Model model,@PathVariable int page){
        model.addAttribute("costs",costService.costLists(page-10));
        model.addAttribute("page",page-10);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        return "costInformation";
    }
}
