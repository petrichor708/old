package com.lala.yanglao.service.serviceImplement;

import com.lala.yanglao.dao.NurseDao;
import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    NurseDao nurseDao;

    @Override
    public Nurse nurseOneById(Long id) {
        return nurseDao.selectByPrimary(id);
    }

    @Override
    public List<Old> oldNurseLists(Long id) {
        return nurseDao.selectOldsByPrimary(id);
    }

    @Override
    public List<Nurse> nurseLists( ) {
        return nurseDao.selectAll();
    }

    @Override
    public List<Nurse> nurseListsOnLimit(int num) {
        return nurseDao.selectAllOnLimit(num);
    }

    @Override
    public List<Nurse> nurseListsByGrade(String nurseGrad) {
        return nurseDao.selectAllByGrade(nurseGrad);
    }

    @Override
    public List<Nurse> gradeLists() {
        return nurseDao.selectGrades();
    }

    @Override
    public List<Nurse> nurseListsByNameOrNumber(String query) {
        return nurseDao.selectByNameOrNurseId(query);
    }

    @Transactional
    @Override
    public int addNurse(Nurse nurse) {
        return nurseDao.insertNurse(nurse);
    }

    @Override
    public int nurseCount() {
        return nurseDao.selectCountOfNurses();
    }

    @Transactional
    @Override
    public int editNurse(Nurse nurse) {
        return nurseDao.updateNurse(nurse);
    }

    @Transactional
    @Override
    public int delNurse(Long id) {
        return nurseDao.deleteNurse(id);
    }

}
