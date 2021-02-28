package com.lala.yanglao.dao;

import com.lala.yanglao.model.Old;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OldDao {
    List selectAll();
    Old selectByPrimary(Long id);
    List selectByName(String name);
    int insertOld(Old old);
    int selectCountOfOlds();
    List<Old> selectAllOnLimit(int num);
    int updateOldById(Old old);
    int deleteOldById(Long id);
    List<Old> selectOldListByNurseId(Long id);
    List<Old> selectOldListByRoomId(Long id);
}
