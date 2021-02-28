package com.lala.yanglao.dao;

import com.lala.yanglao.model.Cost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CostDao {
    List<Cost> selectCostLists(int num);
    int updateRepayDateToCurrent(Long oldId);
    List<Cost> selectByNameOrOldId(String query);
    double selectExtraCharges();
    Cost selectByOldId(Long id);
    int updateChargeByOldId(double charge,Long id);
    int updateAllChargeByOldId(double allCharge,Long id);
    int updateUpdateCostTimeToCurrent(Long oldId);
    int updateExtraCharges(double extraCharges);
}
