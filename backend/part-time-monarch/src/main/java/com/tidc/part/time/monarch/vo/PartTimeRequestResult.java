package com.tidc.part.time.monarch.vo;

import com.tidc.part.time.monarch.domain.PartTime;
import lombok.Data;

import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/11/1 12:10
 */
@Data
public class PartTimeRequestResult extends RequestResult{

    private List<PartTime> partTime;

    private int pageNumber;

    public RequestResult succeed(List<PartTime> partTime){
        this.partTime = partTime;
        this.succeed();
        return this;
    }

}
