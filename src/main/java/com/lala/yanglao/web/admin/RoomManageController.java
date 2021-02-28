package com.lala.yanglao.web.admin;


import com.lala.yanglao.MyException;
import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.OldService;
import com.lala.yanglao.service.RoomService;
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
@RequestMapping("/admin")
public class RoomManageController {

    @Autowired
    RoomService roomService;
    @Autowired
    OldService oldService;
    @Autowired
    CostService costService;

    @RequestMapping("/room/pageNext/{page}")
    public String pageNext(Model model, @PathVariable int page){
        model.addAttribute("roomCount",roomService.roomCount());
        model.addAttribute("rooms",roomService.roomListsOnLimit(page+10));
        model.addAttribute("page",page+10);
        model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
        return "admin/roomManage";
    }
    @RequestMapping("/room/pageLast/{page}")
    public String pageLast(Model model,@PathVariable int page){
        model.addAttribute("roomCount",roomService.roomCount());
        model.addAttribute("rooms",roomService.roomListsOnLimit(page-10));
        model.addAttribute("page",page-10);
        model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
        return "admin/roomManage";
    }
    @RequestMapping("/roomSearch")
    public String oldSearch(Model model,String query){
        if (query.equals("")){
            model.addAttribute("message","请输入查找的关键字！");
            model.addAttribute("message1",0);
            return "admin/roomManage";
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
        model.addAttribute("roomCount",list.size());
        model.addAttribute("page",0);
        model.addAttribute("end",0);
        return "admin/roomManage";
    }
    @RequestMapping("/roomEdit/{id}")
    public String roomEdit(Model model,@PathVariable Long id){
        Room room=roomService.roomOneById(id);
        if (room==null){
            model.addAttribute("message","恰好被别人删除了，你手慢了");
            model.addAttribute("rooms",roomService.roomListsOnLimit(0));
            model.addAttribute("roomCount",roomService.roomCount());
            model.addAttribute("page",0);
            model.addAttribute("end",roomService.roomLists().size()%10==0 ?
                    roomService.roomLists().size()-10 :
                    roomService.roomLists().size()/10*10);
            return "admin/roomManage";
        }
        model.addAttribute("room",room);
        return "admin/roomEdit";
    }
    @Transactional
    @RequestMapping("/roomUpdate")
    public String oldUpdate(Model model, Room room){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowDate=simpleDateFormat.format(new Date());
        double charge = 0;
        int result=0,result4=0,result2=0,result3=0;
        Room room1=roomService.roomOneById((Long) room.getId());
        if (room.getPrice()!=room1.getPrice()){
            List<Old> olds=oldService.oldListsByRoomId(room.getId());
            for (Old old:olds){
                try {
                    int day= days.daysBetween(old.getUpdateCostTime(),nowDate);
                    charge=day*(costService.costByOldId(old.getId()).getnCost()+
                            costService.costByOldId(old.getId()).getrCost()+
                            costService.costByOldId(old.getId()).getExtraCharges())/365;
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
        }
        result=roomService.setRoom(room);
        if (result==1){
            model.addAttribute("message","修改成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("修改失败");
        }
        return "admin/roomEdit";
    }
    @RequestMapping("/roomDelete/{id}")
    public String nurseDelete(Model model,@PathVariable Long id){
        if (roomService.roomOneById(id)==null){
            model.addAttribute("message","删除失败!已被别人删除");
            model.addAttribute("rooms",roomService.roomListsOnLimit(0));
            model.addAttribute("roomCount",roomService.roomCount());
            model.addAttribute("page",0);
            model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
            return "admin/roomManage";
        }
        if(roomService.oldRoomLists(id).get(0)!=null){
            model.addAttribute("message","删除失败，该房间存在老人依赖，清将其入住的老人交接完后再执行此操作！");
            model.addAttribute("rooms",roomService.roomListsOnLimit(0));
            model.addAttribute("roomCount",roomService.roomCount());
            model.addAttribute("page",0);
            model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
            return "admin/roomManage";
        }
        int result=roomService.delRoom(id);
        if (result==1){
            model.addAttribute("message","删除成功！");
            model.addAttribute("rooms",roomService.roomListsOnLimit(0));
            model.addAttribute("nurseCount",roomService.roomCount());
            model.addAttribute("page",0);
            model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
            return "admin/roomManage";
        }else {
            model.addAttribute("message","删除失败！");
            model.addAttribute("rooms",roomService.roomListsOnLimit(0));
            model.addAttribute("nurseCount",roomService.roomCount());
            model.addAttribute("page",0);
            model.addAttribute("end",roomService.roomLists().size()%10==0 ? roomService.roomLists().size()-10 : roomService.roomLists().size()/10*10);
            return "admin/roomManage";
        }
    }
}
