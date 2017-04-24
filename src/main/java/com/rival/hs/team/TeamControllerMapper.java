package com.rival.hs.team;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Minwoo on 2017. 4. 24..
 */
public interface TeamControllerMapper {


    @RequestMapping("/team/board/{id}")
    String getTeamDetail(Model model, @PathVariable String id);


}
