package com.rival.hs.team.core;

import com.rival.hs.team.domain.TeamDo;
import com.rival.hs.team.dao.TeamMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minwoo on 2017. 3. 18..
 */

@Controller
public class TeamController implements TeamControllerMapper{


    @Autowired
    TeamMongoRepository teamMongoRepository;

    @Override
    public String getTeamCreateView() {
        return "team/teamNew";
    }

    @Override
    public String getTeamDetailView(Model model, String id) {
        TeamDo teamDo = teamMongoRepository.findByName(id);

        model.addAttribute("team", teamDo);
        return "team/team_detail_view";
    }

    @Override
    public String getTeamMenuView(Model model, HttpSession session) {
        List<TeamDo> Buffer = new ArrayList<>();
        List<TeamDo> teamDoList = teamMongoRepository.findAll();
        String kakao_id = "385806550"; //땅현리 id 세션값  일단 이렇게 처리해두겠음
        for(int i=0;i<teamDoList.size();i++)
        {
            for(int j=0;j<teamDoList.get(i).getMember_id().size();j++) // 카카오 ID에 대해 팀 검사
            {
                if(kakao_id.equals(teamDoList.get(i).getMember_id().get(j)))
                {
                    Buffer.add(teamDoList.get(i));
                }
            }
        }
        model.addAttribute("MyteamList",Buffer);

        return "team/team";
    }
    @Override
    public String getTeamListView(Model model, Pageable pageable) {

        Page<TeamDo> teams = teamMongoRepository.findAll(pageable);
        model.addAttribute("teams", teams);

        return "team/teamListView";
    }
    @Override
    public String postTeamCreate(TeamDo form, BindingResult result, Model model, HttpSession session) {
        TeamDo teamdo = new TeamDo();
        teamdo.setName(form.getName());
        teamdo.setCaptain(form.getCaptain());
        teamdo.setCity(form.getCity());
        teamdo.setEmblem(form.getEmblem());
        teamdo.setImage(form.getImage());
        teamdo.setIntroduce(form.getIntroduce());
        teamdo.setType(form.getType());

        String kakao_id = (String) session.getAttribute("id"); //세션값. 수정해야됨 !!
        teamdo.setMember_id(kakao_id);

        teamMongoRepository.save(teamdo);
        return "redirect:/team/team";

    }
}

