package com.lala.yanglao.web.admin;

import com.lala.yanglao.service.NurseService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManageController {

    @Autowired
    OldService oldService;
    @Autowired
    NurseService nurseService;
    @Autowired
    RoomService roomService;

    @RequestMapping("/")
    public String index(){
        System.out.println("访问后台主页");
        return "admin/index";
    }

    @RequestMapping("/oldManage")
    public String oldManage(Model model){
        System.out.println("访问后台老人管理");
        model.addAttribute("olds",oldService.oldListsOnPage(0));
        model.addAttribute("oldCount",oldService.oldCount());
        model.addAttribute("page",0);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        return "admin/oldManage";
    }

    @RequestMapping("/nurseManage")
    public String nurseManage(Model model){
        System.out.println("访问后台护理管理");
        model.addAttribute("nurses",nurseService.nurseListsOnLimit(0));
        model.addAttribute("nurseCount",nurseService.nurseCount());
        model.addAttribute("page",0);
        model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
        return "admin/nurseManage";
    }
    @RequestMapping("/roomManage")
    public String roomManage(Model model){
        System.out.println("访问后台房间管理");
        model.addAttribute("rooms",roomService.roomListsOnLimit(0));
        model.addAttribute("roomCount",roomService.roomCount());
        model.addAttribute("page",0);
        model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
        return "admin/roomManage";
    }
}
