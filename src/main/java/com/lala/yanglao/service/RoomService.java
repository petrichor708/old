package com.lala.yanglao.service;



import com.lala.yanglao.model.Old;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.vo.RoomQuery;

import java.util.List;

public interface RoomService {
    Room roomOneById(Long id);
    List<Old> oldRoomLists(Long id);
    List<Room> roomListsOnLimit(int num);
    List<Room> roomListsNoFull();
    int roomNumberUpdate(Long id);
    int roomOldNumberDecreaseById(Long id);
    List<Room> roomLists();
    List<Room> roomTypeLists();
    List<Room> roomTypeListsByTypeOrStatus(RoomQuery roomQuery);
    List<Room> roomListsByRoomNumber(String query);
    int addRoom(Room room);
    int roomCount();
    int setRoom(Room room);
    int delRoom(Long id);
}
