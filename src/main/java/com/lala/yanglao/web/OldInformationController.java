package com.lala.yanglao.web;

import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.NurseService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OldInformationController {

    @Autowired
    OldService oldService;
    @Autowired
    NurseService nurseService;
    @Autowired
    RoomService roomService;

    @RequestMapping("/oldCondition/{id}")
    public String oldCondition(Model model,@PathVariable Long id){
        model.addAttribute("old",oldService.oldOneById(id));
        return "oldCondition";
    }
    @PostMapping("/oldSearch")
    public String oldSearch(Model model,String query){
        if (query.equals("")||query==null){
            model.addAttribute("message","请输入查找的关键字！");
            model.addAttribute("message1",0);
            return "oldInformation";
        }
        List<Old> list=oldService.oldListsByName(query);
        model.addAttribute("query",query);
        model.addAttribute("olds",list);
        if (list.isEmpty()){
            model.addAttribute("message","查无此人！");
        }else{
            model.addAttribute("message","查找成功！");
        }
        model.addAttribute("message1",list.size());
        model.addAttribute("nurseGrads",nurseService.gradeLists());
        return "oldInformation";
    }
    @RequestMapping("/nurseCondition/{id}")
    public String nurseCondition(Model model, @PathVariable Long id){
        model.addAttribute("nurse",nurseService.nurseOneById(id));
        List<Old> list=nurseService.oldNurseLists(id);
            if (list.get(0)==null){
                List olds=new ArrayList();
                Old old=new Old();
                old.setName("");
                olds.add(old);
                model.addAttribute("olds",olds);
                return "nurseCondition";
            }
        model.addAttribute("olds",list);
        return "nurseCondition";
    }
    @RequestMapping("/roomCondition/{id}")
    public String roomCondition(Model model, @PathVariable Long id){
        model.addAttribute("room",roomService.roomOneById(id));
        model.addAttribute("olds",roomService.oldRoomLists(id));
        return "roomCondition";
    }
}
