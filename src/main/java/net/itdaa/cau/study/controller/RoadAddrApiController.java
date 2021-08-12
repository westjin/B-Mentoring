package net.itdaa.cau.study.controller;

import io.swagger.annotations.ApiOperation;
import net.itdaa.cau.study.entity.RoadAddrIntg;
import net.itdaa.cau.study.repository.RoadAddrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoadAddrApiController {

    static final String retMsg = "resMsg";
    static final String retObj = "retObj";
    static final String retCnt = "retCnt";

    ResponseEntity<?> entity = null;

    @Autowired
    RoadAddrRepository roadAddrRepository;

    @ApiOperation(value="조회할 도로명 주소(전체 or 일부)", notes="(도로명 주소의 일부 정보로) 해당하는 도로명주소를 조회합니다.")
    @GetMapping(value="/roadAddr")
    public ResponseEntity<?> getRoadAddr(@RequestParam("searchKeyword") String searchKeyword
                                        ,@RequestParam("searchNumber") String searchNumber) {

        Map<String,Object> resultMap = new HashMap<>();

        if (searchKeyword.trim().length() == 0) {

            resultMap.put(retMsg,"주소가 올바르게 입력 되지 않았습니다.");
            resultMap.put(retObj,"");
            resultMap.put(retCnt,0);

            entity = new ResponseEntity<>(resultMap, HttpStatus.OK);

            return entity;
        }

        String buildingMainNumber = "";
        String buildingSubNumber = "";

        if (searchNumber != null) {
            String[] bldgNumberArray = searchNumber.trim().split("-");

            if (bldgNumberArray.length == 1) {
                buildingMainNumber = searchNumber.trim();
            } else if (bldgNumberArray.length == 2) {
                buildingMainNumber = bldgNumberArray[0];
                buildingSubNumber = bldgNumberArray[1];
            }
        }

        System.out.println(searchKeyword+","+buildingMainNumber+","+buildingSubNumber);

        List<RoadAddrIntg> list = roadAddrRepository.findRoadAddr(searchKeyword+"%",buildingMainNumber+"%",buildingSubNumber+"%");
        List<String> searchResultList = new ArrayList<>();

        for (RoadAddrIntg rai : list) {
            searchResultList.add(rai.getFullRoadAddr()+" 우편번호:"+rai.getPostCode());
        }

        resultMap.put(retMsg,"정상처리되었습니다.");
        resultMap.put(retObj,searchResultList);
        resultMap.put(retCnt,searchResultList.size());

        entity = new ResponseEntity<>(resultMap, HttpStatus.OK);

        return entity;
    }


}
