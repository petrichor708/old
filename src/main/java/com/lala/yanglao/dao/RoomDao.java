package com.lala.yanglao.dao;


import com.lala.yanglao.model.Old;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.vo.RoomQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomDao {
    Room selectByPrimary(Long id);
    List<Old> selectOldsByPrimary(Long id);
    List<Room> selectRoomNoFull();
    int updateRoomOldNumberById(Long id);
    int updateRoomOldNumberDecreaseById(Long id);
    List<Room> selectAll();
    List<Room> selectAllOnLimit(int num);
    List<Room> selectTypeNumber();
    List<Room> selectAllByTypeOrStatus(RoomQuery roomQuery);
    List<Room> selectByRoomNumber(String query);
    int insertRoom(Room room);
    int selectCountOfRooms();
    int updateRoom(Room room);
    int deleteRoom(Long id);
}
