package com.jk.model;

public class Type {
    private Integer kctypeid;

    private String kctypename;

    public Integer getKctypeid() {
        return kctypeid;
    }

    public void setKctypeid(Integer kctypeid) {
        this.kctypeid = kctypeid;
    }

    public String getKctypename() {
        return kctypename;
    }

    public void setKctypename(String kctypename) {
        this.kctypename = kctypename == null ? null : kctypename.trim();
    }
}