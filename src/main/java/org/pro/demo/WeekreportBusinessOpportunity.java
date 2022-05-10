package org.pro.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 销售周报关联商机线索表
 * </p>
 *
 * @author afan
 * @date 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
public class WeekreportBusinessOpportunity implements Serializable {

    private static final long serialVersionUID = 1L;


    private String projectName;

    /**
     * 项目所处阶段
     */
    private Integer projectStage;

    /**
     * 项目状态
     */
    private Integer projectStatus;

    /**
     * 预算金额
     */
    private String budgetAmount;

    /**
     * 目标签约金额
     */
    private String  estimateSignAmount;

    /**
     * 进展详情或无进展原因
     */
    private String progressInfo;



}
