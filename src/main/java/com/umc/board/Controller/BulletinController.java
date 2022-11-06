package com.umc.board.Controller;

import com.umc.board.model.Bulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board")
public class BulletinController {
    private BulletinService bulletinService;

    @Autowired
    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @GetMapping(path="/bulletin")
    public Object bulletinListInfo() {
        List<Bulletin> bulletinList = bulletinService.getBulletinList();

        return bulletinList;
    }

    @GetMapping("/bulletin/{idx}")
    public Object bulletinInfo(@PathVariable("idx") Integer idx) {
        Bulletin bulletin = bulletinService.getBulletinInfo(idx);

        return bulletin;
    }

    @GetMapping(path="/bulletin", params="writer")
    public Object bulletinListByWriter(@RequestParam("writer") String writer) {
        List<Bulletin> bulletinList = bulletinService.getBulletinListByWriter(writer);

        return bulletinList;
    }

    @PostMapping("/bulletin/write")
    public ResponseEntity<Bulletin> bulletinWrite(@RequestBody Bulletin bulletin) {
        try {
            return new ResponseEntity<>(bulletinService.writeBulletin(bulletin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bulletin/edit")
    public ResponseEntity<String> bulletinUpdate(@RequestBody Bulletin bulletin) {
        try {
            Integer updateCnt = bulletinService.updateBulletin(bulletin);

            return new ResponseEntity<>(String.format("%d updated", updateCnt), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bulletin/delete")
    public ResponseEntity<String> bulletinDelete(@RequestParam(value="idx") Integer idx) {
        try {
            int deletedCnt = bulletinService.deletebulletin(idx);
            return new ResponseEntity<>("DELETE OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
