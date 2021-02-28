package com.lala.yanglao.service;

import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Old;

import java.util.List;

public interface NurseService {

    Nurse nurseOneById(Long id);
    List<Old> oldNurseLists(Long id);
    List<Nurse> nurseLists();
    List<Nurse> nurseListsOnLimit(int num);
    List<Nurse> nurseListsByGrade(String nurseGrad);
    List<Nurse> gradeLists();
    List<Nurse> nurseListsByNameOrNumber(String query);
    int addNurse(Nurse nurse);
    int nurseCount();
    int editNurse(Nurse nurse);
    int delNurse(Long id);
}
