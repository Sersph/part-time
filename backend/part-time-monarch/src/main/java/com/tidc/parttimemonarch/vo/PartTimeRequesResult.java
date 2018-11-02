package com.tidc.parttimemonarch.vo;

import com.tidc.parttimemonarch.domain.PartTimeInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 22:02
 */
@Component
@Data
public class PartTimeRequesResult extends RequestResult {

    private List<PartTimeInfo> partTimeTypes;

    private List<PartTimeInfo> partTimeSpecies;

    private List<PartTimeInfo> calculationTypes;

    private List<PartTimeInfo> settlementTypes;


    public void succeed(List<PartTimeInfo> partTimeTypes,
                        List<PartTimeInfo> partTimeSpecies,
                        List<PartTimeInfo> calculationTypes,
                        List<PartTimeInfo> settlementTypes) {

        super.succeed();

        this.partTimeTypes = partTimeTypes;
        this.partTimeSpecies = partTimeSpecies;
        this.calculationTypes = calculationTypes;
        this.settlementTypes = settlementTypes;

    }
}
