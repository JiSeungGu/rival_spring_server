package com.rival.hs.match;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Minwoo on 2017. 4. 11..
 */
public interface MatchControllerMapper {

    String match();

    @RequestMapping("/match/board/{id}")
    String getMatchDetail(@PathVariable String id, Model model);



}
