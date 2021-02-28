package com.lala.yanglao.dao;
import com.lala.yanglao.model.Nurse;
import com.lala.yanglao.model.Old;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface NurseDao {

    Nurse selectByPrimary(Long id);
    List<Old> selectOldsByPrimary(Long id);
    List<Nurse> selectAll();
    List<Nurse> selectAllOnLimit(int num);
    List<Nurse> selectAllByGrade(String nurseGrade);
    List<Nurse> selectGrades();
    List<Nurse> selectByNameOrNurseId(String query);
    int insertNurse(Nurse nurse);
    int selectCountOfNurses();
    int updateNurse(Nurse nurse);
    int deleteNurse(Long id);
}
