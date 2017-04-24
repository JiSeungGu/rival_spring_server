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

    String getStadium();

    @RequestMapping("/stadium/board/list")
    String getStadiumList(Model model, Pageable pageable, @RequestParam(value = "location", required = false) String location);

    @RequestMapping("/stadium/board/{id}")
    String getStadiumDetail(Model model, @PathVariable float id);

}