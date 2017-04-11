package com.rival.hs.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 11..
 */

@RestController
public class MatchRestController {

    @Autowired
    MatchMongoRepository matchMongoRepository;


    @RequestMapping(value="/match/{m_no}", method = RequestMethod.GET)
    public MatchDo getMatch(@PathVariable("bno") String bno) {


        return matchMongoRepository.findOne(bno);
    }

    // 축구, 풋볼 게시판 가져오기 & 지역 검색
    @RequestMapping(value="/match/list", method = RequestMethod.GET)
    public List<MatchDo> getMatchList(){

        List<MatchDo> output = matchMongoRepository.findAll();

        return output;
    }



}
