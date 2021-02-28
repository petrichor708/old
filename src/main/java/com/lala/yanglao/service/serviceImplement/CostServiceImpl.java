package com.lala.yanglao.service.serviceImplement;

import com.lala.yanglao.dao.CostDao;
import com.lala.yanglao.model.Cost;
import com.lala.yanglao.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CostServiceImpl implements CostService {

    @Autowired
    CostDao costDao;

    @Override
    public List<Cost> costLists(int num) {
        return costDao.selectCostLists(num);
    }
    @Transactional
    @Override
    public int setRepayDateToZero(Long oldId) {
        return costDao.updateRepayDateToCurrent(oldId);
    }

    @Override
    public List<Cost> costQuery(String query) {
        return costDao.selectByNameOrOldId(query);
    }


    @Override
    public double extraCharges() {
        return costDao.selectExtraCharges();
    }

    @Override
    public Cost costByOldId(Long id) {
        return costDao.selectByOldId(id);
    }

    @Transactional
    @Override
    public int setChargeByOldId(double charge,Long id) {
        return costDao.updateChargeByOldId(charge,id);
    }

    @Transactional
    @Override
    public int setAllChargeByOldId(double charge, Long id) {
        return costDao.updateAllChargeByOldId(charge,id);
    }

    @Transactional
    @Override
    public int setUpdateCostTimeToCurrent(Long id) {
        return costDao.updateUpdateCostTimeToCurrent(id);
    }

    @Transactional
    @Override
    public int setExtraCharges(double extraCharges) {
        return costDao.updateExtraCharges(extraCharges);
    }

}
