package com.rival.hs.match;

import com.rival.hs.match.domain.MatchDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URLEncoder;

/**
 * Created by Minwoo on 2017. 3. 16..
 */

@Controller
public class MatchController implements MatchControllerMapper {

    @Autowired
    MatchMongoRepository matchMongoRepository;

    @Override
    public String getMatchDetail(String id, Model model) {

        MatchDo matchDo = matchMongoRepository.findOne(id);
        model.addAttribute("match", matchDo);


        return "match/match_detail_view";
    }

    @Override
    @RequestMapping(value="/match", method = RequestMethod.GET)
    public String match() {

        return "match/match";

    }
    /*축구, 풋볼 게시판 가져오기 & 지역 검색*/
    @RequestMapping(value="/match/board/list", method = RequestMethod.GET)
    public String SportBoard(Model model, @RequestParam(value="type", required = false) String type, @RequestParam(value="city", required = false)String city, Pageable pageable){
        Page<MatchDo> board = matchMongoRepository.findByType(type, city, pageable);

        model.addAttribute("board",board);
        model.addAttribute("title",type);
        model.addAttribute("city",city);

        return "match/matching";

    }
     /*matchCreateView 이동*/
    @RequestMapping(value="/match/new", method = RequestMethod.GET)
    public String matchCreateView() {
        return "match/matchCreateView";
    }

    /*경기 만들기*/
    @RequestMapping(value="/match/new", method = RequestMethod.POST)
    public String matchCreate(@Validated MatchDo form, BindingResult result, Model model) {
        MatchDo board = new MatchDo();
        board.setTitle(form.getTitle());
        board.setTeam(form.getTeam());
        board.setType(form.getType());
        board.setCity(form.getCity());
        board.setStadium(form.getStadium());
        board.setContents(form.getContents());

        matchMongoRepository.save(board);

        String UrlType=null;
        try {
            UrlType = new String("redirect:/match/board/list?type=" + URLEncoder.encode(form.getType(),"UTF-8") + "&page=0&size=10&city=" + URLEncoder.encode(form.getCity(),"UTF-8"));
            System.out.println(UrlType);
        }catch (Exception e){
            System.out.println(e);
        }
        return UrlType;
    }
}
