package com.lala.yanglao.service;

import com.lala.yanglao.model.Old;

import java.util.List;

public interface OldService {

    List<Old> oldLists();
    List<Old> oldListsByNurseId(Long id);
    List<Old> oldListsByRoomId(Long id);
    List<Old> oldListsOnPage(int num);
    Old oldOneById(Long id);
    List<Old> oldListsByName(String name);
    int addOld(Old old);
    int oldCount();
    int oldEdit(Old old);
    int deOldById(Long id);
}
