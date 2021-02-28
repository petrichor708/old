package com.lala.yanglao.service.serviceImplement;

import com.lala.yanglao.dao.OldDao;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.service.OldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OldServiceImpl implements OldService {
    @Autowired
    OldDao oldDao;
    @Override
    public List<Old> oldLists() {
        List<Old> olds=oldDao.selectAll();
        return olds;
    }

    @Override
    public List<Old> oldListsByNurseId(Long id) {
        return oldDao.selectOldListByNurseId(id);
    }

    @Override
    public List<Old> oldListsByRoomId(Long id) {
        return oldDao.selectOldListByRoomId(id);
    }

    @Override
    public List<Old> oldListsOnPage(int num) {
        return oldDao.selectAllOnLimit(num);
    }

    @Override
    public Old oldOneById(Long id) {
        return oldDao.selectByPrimary(id);
    }

    @Override
    public List<Old> oldListsByName(String name) {
        return oldDao.selectByName(name);
    }


    @Override
    public int addOld(Old old) {
        return oldDao.insertOld(old);
    }

    @Override
    public int oldCount() {
        return oldDao.selectCountOfOlds();
    }

    @Transactional
    @Override
    public int oldEdit(Old old) {
        return oldDao.updateOldById(old);
    }

    @Transactional
    @Override
    public int deOldById(Long id) {
        return oldDao.deleteOldById(id);
    }

}
