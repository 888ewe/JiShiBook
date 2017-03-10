package com.chenmo.jizhangbook;

import java.io.Serializable;

/**
 * 作者：沉默
 * 日期：2017/3/9
 * QQ:823925783
 */

public class CostBean implements Serializable {

    public String costTitle;
    public String costDate;
    public String costMoney;

    public CostBean() {
    }

    public CostBean(String costTitle, String costDate, String costMoney) {
        this.costTitle = costTitle;
        this.costDate = costDate;
        this.costMoney = costMoney;
    }

    public String getCostTitle() {
        return costTitle;
    }

    public void setCostTitle(String costTitle) {
        this.costTitle = costTitle;
    }

    public String getCostDate() {
        return costDate;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }
}
