package com.lala.yanglao.service.serviceImplement;

import com.lala.yanglao.dao.RoomDao;
import com.lala.yanglao.model.Old;
import com.lala.yanglao.model.Room;
import com.lala.yanglao.service.RoomService;
import com.lala.yanglao.vo.RoomQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomDao roomDao;
    @Override
    public Room roomOneById(Long id) {
        return roomDao.selectByPrimary(id);
    }

    @Override
    public List<Old> oldRoomLists(Long id) {
        return roomDao.selectOldsByPrimary(id);
    }

    @Override
    public List<Room> roomListsOnLimit(int num) {
        return roomDao.selectAllOnLimit(num);
    }

    @Override
    public List<Room> roomListsNoFull() {
        return roomDao.selectRoomNoFull();
    }

    @Transactional
    @Override
    public int roomNumberUpdate(Long id) {
        return roomDao.updateRoomOldNumberById(id);
    }

    @Transactional
    @Override
    public int roomOldNumberDecreaseById(Long id) {
        return roomDao.updateRoomOldNumberDecreaseById(id);
    }

    @Override
    public List<Room> roomLists() {
        return roomDao.selectAll();
    }

    @Override
    public List<Room> roomTypeLists() {
        return roomDao.selectTypeNumber();
    }

    @Override
    public List<Room> roomTypeListsByTypeOrStatus(RoomQuery roomQuery) {
        return roomDao.selectAllByTypeOrStatus(roomQuery);
    }

    @Override
    public List<Room> roomListsByRoomNumber(String query) {
        return roomDao.selectByRoomNumber(query);
    }

    @Transactional
    @Override
    public int addRoom(Room room) {
        return roomDao.insertRoom(room);
    }

    @Override
    public int roomCount() {
        return roomDao.selectCountOfRooms();
    }

    @Transactional
    @Override
    public int setRoom(Room room) {
        return roomDao.updateRoom(room);
    }

    @Transactional
    @Override
    public int delRoom(Long id) {
        return roomDao.deleteRoom(id);
    }
}
