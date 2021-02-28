package com.lala.yanglao.web.admin;

import com.lala.yanglao.MyException;
import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.CostService;
import com.lala.yanglao.service.NurseService;
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
@RequestMapping("/admin")
public class NurseManageController {

    @Autowired
    NurseService nurseService;
    @Autowired
    OldService oldService;
    @Autowired
    CostService costService;

    @RequestMapping("/nurse/pageNext/{page}")
    public String pageNext(Model model, @PathVariable int page){
        model.addAttribute("nurseCount",nurseService.nurseCount());
        model.addAttribute("nurses",nurseService.nurseListsOnLimit(page+10));
        model.addAttribute("page",page+10);
        model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
        return "admin/nurseManage";
    }
    @RequestMapping("/nurse/pageLast/{page}")
    public String pageLast(Model model,@PathVariable int page){
        model.addAttribute("nurseCount",nurseService.nurseCount());
        model.addAttribute("nurses",nurseService.nurseListsOnLimit(page-10));
        model.addAttribute("page",page-10);
        model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
        return "admin/nurseManage";
    }
    @RequestMapping("/nurseSearch")
    public String oldSearch(Model model,String query){
        if (query.equals("")){
            model.addAttribute("message","请输入查找的关键字！");
            model.addAttribute("message1",0);
            return "admin/nurseManage";
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
        model.addAttribute("oldCount",list.size());
        model.addAttribute("page",0);
        model.addAttribute("end",0);
        return "admin/nurseManage";
    }
    @RequestMapping("/nurseEdit/{id}")
    public String nurseEdit(Model model,@PathVariable Long id){
        Nurse nurse=nurseService.nurseOneById(id);
        if (nurse==null){
            model.addAttribute("message","恰好他被别人删除了，你手慢了");
            model.addAttribute("nurses",nurseService.nurseListsOnLimit(0));
            model.addAttribute("nurseCount",nurseService.nurseCount());
            model.addAttribute("page",0);
            model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
            return "admin/nurseManage";
        }
        model.addAttribute("nurse",nurse);
        return "admin/nurseEdit";
    }
    @Transactional
    @RequestMapping("/nurseUpdate")
    public String oldUpdate(Model model, Nurse nurse){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String nowDate=simpleDateFormat.format(new Date());
        double charge = 0;
        int result=0,result4=0,result2=0,result3=0;
        Nurse nurse1=nurseService.nurseOneById((Long) nurse.getId());
        if (nurse.getPrice()!=nurse1.getPrice()){
            List<Old> olds=oldService.oldListsByNurseId(nurse.getId());
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
        }
        result=nurseService.editNurse(nurse);
        if (result==1){
            model.addAttribute("message","修改成功,请关掉此消息再进行其余操作，立刻刷新会再次提交！");
        }else{
            throw new MyException("修改失败");
        }
        return "admin/nurseEdit";
    }
    @RequestMapping("/nurseDelete/{id}")
    public String nurseDelete(Model model,@PathVariable Long id){
        if (nurseService.nurseOneById(id)==null){
            model.addAttribute("message","删除失败！已被别人删除");
            model.addAttribute("nurses",nurseService.nurseListsOnLimit(0));
            model.addAttribute("nurseCount",nurseService.nurseCount());
            model.addAttribute("page",0);
            model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
            return "admin/nurseManage";
        }
        if(nurseService.oldNurseLists(id).get(0)!=null){
            model.addAttribute("message","删除失败，该护理存在老人依赖，清将其负责的老人交接完后再执行此操作！");
            model.addAttribute("nurses",nurseService.nurseListsOnLimit(0));
            model.addAttribute("nurseCount",nurseService.nurseCount());
            model.addAttribute("page",0);
            model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
            return "admin/nurseManage";
        }
        int result=nurseService.delNurse(id);
        if (result==1){
            model.addAttribute("message","删除成功！");
            model.addAttribute("nurses",nurseService.nurseListsOnLimit(0));
            model.addAttribute("nurseCount",nurseService.nurseCount());
            model.addAttribute("page",0);
            model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
            return "admin/nurseManage";
        }else {
            model.addAttribute("message","删除失败！");
            model.addAttribute("nurses",nurseService.nurseListsOnLimit(0));
            model.addAttribute("nurseCount",nurseService.nurseCount());
            model.addAttribute("page",0);
            model.addAttribute("end",nurseService.nurseLists().size()%10==0 ? nurseService.nurseLists().size()-10 : nurseService.nurseLists().size()/10*10);
            return "admin/nurseManage";
        }
    }
}
