package com.rival.hs.stadium;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Minwoo on 2017. 4. 11..
 */
public interface StadiumControllerMapper {

    String stadium();

    @RequestMapping("/stadium/list")
    String stadiumList(Model model, Pageable pageable);

    @RequestMapping("/stadium/{id}")
    String stadiumDetail(Model model, @PathVariable float id);

}