package com.lala.yanglao.web;


import com.lala.yanglao.model.Room;
import com.lala.yanglao.service.RoomService;
import com.lala.yanglao.vo.RoomQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RoomInformationController {

    @Autowired
    RoomService roomService;

    @RequestMapping("/RoomInformationFilter")
    public String filter(Model model, RoomQuery roomQuery){
        RoomQuery roomQuery1=new RoomQuery(null,false);
        if (!roomQuery.getRoomType().equals("")){
            roomQuery1.setRoomType(roomQuery.getRoomType());
        }
        roomQuery1.setCheckbox(roomQuery.isCheckbox());
        List<Room> list=roomService.roomTypeListsByTypeOrStatus(roomQuery1);
        model.addAttribute("rooms",list);
        return "roomInformation :: roomList";
}
    @RequestMapping("/roomSearch")
    public String roomSearch(Model model,String query){
        if (query.equals("")||query==null){
            model.addAttribute("message","请输入查找的关键字！");
            model.addAttribute("message1",0);
            return "roomInformation";
        }
        List<Room> list=roomService.roomListsByRoomNumber(query);
        model.addAttribute("query",query);
        model.addAttribute("rooms",list);
        if (list.isEmpty()){
            model.addAttribute("message","查无此房！");
        }else{
            model.addAttribute("message","查找成功！");
        }
        model.addAttribute("message1",list.size());
        return "roomInformation";
    }
}
