package com.tidc.parttimemonarch.vo;

import com.tidc.parttimemonarch.pojo.CalculationType;
import com.tidc.parttimemonarch.pojo.PartTimeSpecies;
import com.tidc.parttimemonarch.pojo.PartTimeType;
import com.tidc.parttimemonarch.pojo.SettlementType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/10/12 17:06
 */
@Component
public class PartTimeResult extends RequestResult {

    //兼职类型
    private List<PartTimeType> partTimeTypes;
    //兼职种类
    private List<PartTimeSpecies> partTimeSpecies;
    //结算方式
    private List<SettlementType> settlementTypes;
    //计算方式
    private List<CalculationType> calculationTypes;


    public List<PartTimeType> getPartTimeTypes() {
        return partTimeTypes;
    }

    public void setPartTimeTypes(List<PartTimeType> partTimeTypes) {
        this.partTimeTypes = partTimeTypes;
    }

    public List<PartTimeSpecies> getPartTimeSpecies() {
        return partTimeSpecies;
    }

    public void setPartTimeSpecies(List<PartTimeSpecies> partTimeSpecies) {
        this.partTimeSpecies = partTimeSpecies;
    }

    public List<SettlementType> getSettlementTypes() {
        return settlementTypes;
    }

    public void setSettlementTypes(List<SettlementType> settlementTypes) {
        this.settlementTypes = settlementTypes;
    }

    public List<CalculationType> getCalculationTypes() {
        return calculationTypes;
    }

    public void setCalculationTypes(List<CalculationType> calculationTypes) {
        this.calculationTypes = calculationTypes;
    }

    @Override
    public String toString() {
        return "PartTimeResult{" +
                "partTimeTypes=" + partTimeTypes +
                ", partTimeSpecies=" + partTimeSpecies +
                ", settlementTypes=" + settlementTypes +
                ", calculationTypes=" + calculationTypes +
                '}';
    }
}
