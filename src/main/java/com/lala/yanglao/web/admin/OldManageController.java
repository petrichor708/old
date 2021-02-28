package com.lala.yanglao.web.admin;

import com.lala.yanglao.MyException;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.NurseService;
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

@RequestMapping("/admin")
@Controller
public class OldManageController {
    @Autowired
    OldService oldService;
    @Autowired
    NurseService nurseService;
    @Autowired
    RoomService roomService;
    @Autowired
    CostService costService;

    @RequestMapping("/old/pageNext/{page}")
    public String pageNext(Model model, @PathVariable int page){
        model.addAttribute("oldCount",oldService.oldCount());
        model.addAttribute("olds",oldService.oldListsOnPage(page+10));
        model.addAttribute("page",page+10);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        return "admin/oldManage";
    }
    @RequestMapping("old/pageLast/{page}")
    public String pageLast(Model model,@PathVariable int page){
        model.addAttribute("oldCount",oldService.oldCount());
        model.addAttribute("olds",oldService.oldListsOnPage(page-10));
        model.addAttribute("page",page-10);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        return "admin/oldManage";
    }
    @RequestMapping("/oldSearch")
    public String oldSearch(Model model,String query){
        if (query.equals("")){
            model.addAttribute("message","请输入查找的关键字！");
            model.addAttribute("message1",0);
            return "admin/oldManage";
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
        model.addAttribute("oldCount",list.size());
        model.addAttribute("page",0);
        model.addAttribute("end",0);
        return "admin/oldManage";
    }
    @RequestMapping("/oldEdit/{id}")
    public String oldEdit(Model model,@PathVariable("id")Long oldId){
        model.addAttribute("old",oldService.oldOneById(oldId));
        Room room=roomService.roomOneById(oldService.oldOneById(oldId).getRid());
        List<Room> rooms=roomService.roomListsNoFull();
        rooms.add(room);
        model.addAttribute("nurses",nurseService.nurseLists());
        model.addAttribute("rooms",rooms);
        return "admin/oldEdit";
    }
    @Transactional
    @RequestMapping("/oldUpdate")
    public String oldUpdate(Model model,Old old){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowDate=simpleDateFormat.format(new Date());
        double charge = 0;
        int result=0,result4=0,result2=0,resultRoom=0,resultRoom1=0,result3=0;
        Old oldOld=oldService.oldOneById((Long) old.getId());
        if (old.getRid()!=oldOld.getRid()||old.getNid()!=oldOld.getNid()){
            try {
                int day= days.daysBetween(oldOld.getUpdateCostTime(),nowDate);
                charge=day*(costService.costByOldId(oldOld.getId()).getnCost()+costService.costByOldId(oldOld.getId()).getrCost()+costService.costByOldId(oldOld.getId()).getExtraCharges())/365;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result4=costService.setChargeByOldId(charge+oldOld.getCharge(),old.getId());
            result2=costService.setUpdateCostTimeToCurrent(old.getId());
            resultRoom=roomService.roomNumberUpdate(old.getRid());
            resultRoom1=roomService.roomOldNumberDecreaseById(oldOld.getRid());
            result3=costService.setAllChargeByOldId(charge+oldOld.getAllCharge(),old.getId());
            if (result2!=1||result3!=1||result4!=1||resultRoom!=1||resultRoom1!=1){
                throw new MyException("修改失败");
            }
        }
        result=oldService.oldEdit(old);
        if (result==1){
            model.addAttribute("message","修改成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("修改失败");
        }
        model.addAttribute("nurses",nurseService.nurseLists());
        model.addAttribute("rooms",roomService.roomListsNoFull());
        return "admin/oldEdit";
    }
    @Transactional
    @RequestMapping("/oldDelete/{id}")
    public String oldDelete(Model model,@PathVariable Long id){
        if(oldService.oldOneById(id)==null){
            model.addAttribute("message","删除失败！已被别人删除");
            model.addAttribute("olds",oldService.oldListsOnPage(0));
            model.addAttribute("oldCount",oldService.oldCount());
            model.addAttribute("page",0);
            model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
            return "admin/oldManage";
        }
        int resultRoom=roomService.roomOldNumberDecreaseById(oldService.oldOneById(id).getRid());
        int result=oldService.deOldById(id);
        if (result!=1||resultRoom!=1){
            throw new MyException("删除失败");
        }
        model.addAttribute("message","删除成功！");
        model.addAttribute("olds",oldService.oldListsOnPage(0));
        model.addAttribute("oldCount",oldService.oldCount());
        model.addAttribute("page",0);
        model.addAttribute("end",oldService.oldLists().size()%10==0 ? oldService.oldLists().size()-10 : oldService.oldLists().size()/10*10);
        return "admin/oldManage";
    }
}
