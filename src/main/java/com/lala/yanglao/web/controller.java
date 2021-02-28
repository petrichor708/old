package com.lala.yanglao.web;

import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.NurseService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller{

    @Autowired
    OldService oldService;
    @Autowired
    NurseService nurseService;
    @Autowired
    RoomService roomService;
    @Autowired
    CostService costService;

    @RequestMapping("/")
    public String index(){//首页
        System.out.println("访问主页");
        return "index";
    }

    @RequestMapping("/oldLogin")
    public  String oldLogin(Model model){
        System.out.println("访问老人注册");
        model.addAttribute("nurses",nurseService.nurseLists());
        model.addAttribute("rooms",roomService.roomListsNoFull());
        return "oldLogin";
    }
    @RequestMapping("/oldInformation")
    public  String oldInformation(Model model){
        System.out.println("访问老人信息");
        model.addAttribute("olds",oldService.oldLists());
        return "oldInformation";
    }
    @RequestMapping("/roomInformation")
    public  String roomInformation(Model model){
        System.out.println("访问房间信息");
        model.addAttribute("rooms",roomService.roomLists());
        model.addAttribute("roomTypes",roomService.roomTypeLists());
        return "roomInformation";
    }
    @RequestMapping("/nurseInformation")
    public  String nurseInformation(Model model){
        System.out.println("访问护理人员信息");
        model.addAttribute("nurses",nurseService.nurseLists());
        model.addAttribute("nurseGrads",nurseService.gradeLists());
        return "nurseInformation";
    }
    @RequestMapping("/costInformation")
    public  String costInformation(Model model){
        System.out.println("访问费用信息");
        model.addAttribute("costs",costService.costLists(0));
        model.addAttribute("page",0);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ?
                oldService.oldLists().size()-10 :
                oldService.oldLists().size()/10*10);
        return "costInformation";
    }
    @RequestMapping("/nurseLogin")
    public  String nurseLogin(Model model){
        System.out.println("访问护理注册");
        return "nurseLogin";
    }
    @RequestMapping("/roomLogin")
    public  String roomLogin(Model model){
        System.out.println("访问房间注册");
        return "roomLogin";
    }
}
