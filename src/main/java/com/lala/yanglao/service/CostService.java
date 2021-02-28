package com.lala.yanglao.service;

import com.lala.yanglao.model.Cost;

import java.util.List;

public interface CostService {
    List<Cost> costLists(int num);
    int setRepayDateToZero(Long oldId);
    List<Cost> costQuery(String query);
    double extraCharges();
    Cost costByOldId(Long id);
    int setChargeByOldId(double charge,Long id);
    int setAllChargeByOldId(double charge,Long id);
    int setUpdateCostTimeToCurrent(Long id);
    int setExtraCharges(double extraCharges);
}
