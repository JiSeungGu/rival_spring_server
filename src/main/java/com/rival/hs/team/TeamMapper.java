package com.rival.hs.team;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Minwoo on 2017. 4. 23..
 */
public interface TeamMapper {

    public List<TeamDo> name(@RequestParam(required = false) String name);




}
