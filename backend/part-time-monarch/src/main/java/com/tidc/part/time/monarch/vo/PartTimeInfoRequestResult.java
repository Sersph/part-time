package com.tidc.part.time.monarch.vo;

import com.tidc.part.time.monarch.domain.PartTimeInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 22:02
 */
@Data
public class PartTimeInfoRequestResult extends RequestResult {

    private List<PartTimeInfo> partTimeTypes;

    private List<PartTimeInfo> partTimeSpecies;

    private List<PartTimeInfo> calculationTypes;

    private List<PartTimeInfo> settlementTypes;


    public PartTimeInfoRequestResult succeed(List<PartTimeInfo> partTimeTypes,
                                             List<PartTimeInfo> partTimeSpecies,
                                             List<PartTimeInfo> calculationTypes,
                                             List<PartTimeInfo> settlementTypes) {

        super.succeed();

        this.partTimeTypes = partTimeTypes;
        this.partTimeSpecies = partTimeSpecies;
        this.calculationTypes = calculationTypes;
        this.settlementTypes = settlementTypes;

        return this;
    }
}
