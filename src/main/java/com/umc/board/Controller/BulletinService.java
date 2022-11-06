package com.umc.board.Controller;

import com.umc.board.model.Bulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.umc.board.repository.BulletinRepository;


import java.util.List;

@Service
public class BulletinService {
    private final BulletinRepository bulletinRepository;

    @Autowired
    public BulletinService(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }


    public List<Bulletin> getBulletinList() {
        return this.bulletinRepository.findList();
    }

    public Bulletin getBulletinInfo(Integer idx) {
        return this.bulletinRepository.findBulletin(idx);
    }

    public List<Bulletin> getBulletinListByWriter(String writer) {
        return this.bulletinRepository.findBulletinByWriter(writer);
    }

    public Bulletin writeBulletin(Bulletin bulletin) {
        return this.bulletinRepository.write(bulletin);
    }

    public Integer updateBulletin(Bulletin bulletin) {
        return this.bulletinRepository.update(bulletin);
    }

    public Integer deletebulletin(Integer idx) {
        return this.bulletinRepository.delete(idx);
    }
}
