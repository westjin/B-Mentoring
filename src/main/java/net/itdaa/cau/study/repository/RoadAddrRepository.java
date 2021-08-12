package net.itdaa.cau.study.repository;

import net.itdaa.cau.study.entity.RoadAddrIntg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadAddrRepository extends JpaRepository<RoadAddrIntg, Integer> {

    @Query("  from RoadAddrIntg " +
            "where (roadName LIKE :keyword OR constBldgName LIKE :keyword) " +
            "  and (bldgMainNo LIKE :bldgMainNo AND bldgSubNo LIKE :bldgSubNo)")
    List<RoadAddrIntg> findRoadAddr(@Param("keyword") String roadName
                                   ,@Param("bldgMainNo") String bldgMainNo
                                   ,@Param("bldgSubNo") String bldgSubNo);


}
