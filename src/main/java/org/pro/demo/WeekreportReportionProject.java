package org.pro.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author afan
 * @date 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
public class WeekreportReportionProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String projectName;


    /**
     * 项目所处阶段
     */
    private String projectNode;

    /**
     * 项目状态
     */
    private Integer projectStatus;

    /**
     * 总体预算金额（万元）
     */
    private String budget;

    /**
     * 我司参与部分金额（万元）
     */
    private String budgetPartake;

    /**
     * 项目推进计划时间节点
     */
    private String advanceDate;

    /**
     * 进展详情或无进展原因
     */
    private String progressInfo;



}
