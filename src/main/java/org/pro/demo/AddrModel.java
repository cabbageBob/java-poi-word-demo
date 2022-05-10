package org.pro.demo;

import com.deepoove.poi.data.NumberingRenderData;

public class AddrModel {

    private String addr;
    private NumberingRenderData feature;

    public AddrModel(String addr,NumberingRenderData feature) {
        this.addr = addr;
        this.feature = feature;
    }


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    public NumberingRenderData getFeature() {
        return feature;
    }

    public void setFeature(NumberingRenderData feature) {
        this.feature = feature;
    }
}
