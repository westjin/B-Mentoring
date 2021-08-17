package net.itdaa.cau.study.repository;

import net.itdaa.cau.study.entity.RoadAddrIntg;
import net.itdaa.cau.study.entity.RoadAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadAddrRepository extends JpaRepository<RoadAddrIntg, String> {

    List<RoadAddress> findByRoadNameStartingWith(String roadName);

    List<RoadAddress> findByRoadNameStartingWithAndBldgMainNo(String roadName, Integer bldgMainNo);

    List<RoadAddress> findByRoadNameAndBldgMainNoAndBldgSubNo(String roadName, Integer bldgMainNo, Integer bldgSubNo);

}
