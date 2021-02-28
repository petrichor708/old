package com.lala.yanglao.web;


import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.service.NurseService;
import com.lala.yanglao.service.RoomService;
import com.lala.yanglao.vo.RoomQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NurseInformationController {

    @Autowired
    NurseService nurseService;

    @RequestMapping("/nurseInformationFilter")
    public String filter(Model model,String nurseGrad){
        System.out.println(nurseGrad);
        if (nurseGrad==""){
            nurseGrad=null;
        }
        List<Nurse> list=nurseService.nurseListsByGrade(nurseGrad);
        model.addAttribute("nurses",list);
        return "nurseInformation :: nurseList";
    }
    @RequestMapping("/nurseSearch")
    public String nurseSearch(Model model,String query){
        if (query.equals("")||query==null){
            model.addAttribute("message","请输入查找的关键字！");
            model.addAttribute("message1",0);
            return "nurseInformation";
        }
        List<Nurse> list=nurseService.nurseListsByNameOrNumber(query);
        model.addAttribute("query",query);
        model.addAttribute("nurses",list);
        if (list.isEmpty()){
            model.addAttribute("message","查无此人！");
        }else{
            model.addAttribute("message","查找成功！");
        }
        model.addAttribute("message1",list.size());
        return "nurseInformation";
    }
}
