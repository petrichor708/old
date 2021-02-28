package com.lala.yanglao.web;

import com.lala.yanglao.MyException;
import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.NurseService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    OldService oldService;
    @Autowired
    RoomService roomService;
    @Autowired
    NurseService nurseService;
    @Autowired
    CostService costService;

    @RequestMapping("/oldRegister")
    public String oldLogin(Model model,Old old){
        int result=oldService.addOld(old);
        int resultRoom=roomService.roomNumberUpdate(old.getRid());
        if (result==1&&resultRoom==1){
            model.addAttribute("message","注册成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("注册失败");
        }
        model.addAttribute("nurses",nurseService.nurseLists());
        model.addAttribute("rooms",roomService.roomListsNoFull());
        return "oldLogin";
    }

    @RequestMapping("/nurseRegister")
    public String nurseRegister(Model model, Nurse nurse){

        int result=nurseService.addNurse(nurse);
        if (result==1){
            model.addAttribute("message","注册成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("注册失败");
        }
        return "nurseLogin";
    }
    @RequestMapping("/roomRegister")
    public String roomRegister(Model model, Room room){

        int result=roomService.addRoom(room);
        if (result==1){
            model.addAttribute("message","注册成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("添加失败");
        }
        System.out.println(room);
        return "roomLogin";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/admin")
    public String login1(){
        return "login";
    }
}
