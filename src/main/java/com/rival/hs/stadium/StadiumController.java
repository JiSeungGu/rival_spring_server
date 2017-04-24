package com.rival.hs.stadium;

import com.rival.hs.Holder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 3. 29..
 */

@Controller
public class StadiumController implements StadiumControllerMapper {


    PageRequest pageRequest = new PageRequest(0,10,new Sort(Sort.Direction.DESC, "_id"));

    @Autowired
    Holder holder;

    @Autowired
    StadiumMongoRepository stadiumMongoRepository;


    @RequestMapping("/stadium")
    public String stadium() {

        return "stadium/stadium_view";
    }

    @Override
    public String stadiumList(Model model, Pageable pageable) {

        Page<StadiumDo> stadiums = stadiumMongoRepository.findAll(pageRequest);

        model.addAttribute("stadiums", stadiums);

        return "stadium/stadium_list_view";
    }

    @Override
    public String stadiumDetail(Model model, @PathVariable float id) {

        StadiumDo stadiumDo = stadiumMongoRepository.findById(String.valueOf(id));

        model.addAttribute("stadium", stadiumDo);

        return "stadium/stadium_detail_view";
    }

    @RequestMapping("/stadiumapi")
    public void stadium2() {

        StadiumAPI stadiumAPI = new StadiumAPI();

        StadiumXMLParser stadiumXMLParser = new StadiumXMLParser();

        ArrayList<StadiumDo> arrayList = new ArrayList<>();

        try {
            int i =3465;
            do {

                arrayList = stadiumXMLParser.parser(stadiumAPI.send(i++));
                stadiumMongoRepository.save(arrayList);


            } while(arrayList.size() > 0);


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
