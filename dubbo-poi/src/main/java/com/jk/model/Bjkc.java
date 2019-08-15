package com.jk.model;

public class Bjkc {
    private Integer kcid;

    private String kccoding;

    private String kcname;

    private Integer campusid;

    private Integer classid;

    private Double kcprice;

    private Integer studynumber;

    private Integer statusid;

    private Integer bzxhks;

    private Integer kctypeid;

    public Integer getKcid() {
        return kcid;
    }

    public void setKcid(Integer kcid) {
        this.kcid = kcid;
    }

    public String getKccoding() {
        return kccoding;
    }

    public void setKccoding(String kccoding) {
        this.kccoding = kccoding == null ? null : kccoding.trim();
    }

    public String getKcname() {
        return kcname;
    }

    public void setKcname(String kcname) {
        this.kcname = kcname == null ? null : kcname.trim();
    }

    public Integer getCampusid() {
        return campusid;
    }

    public void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Double getKcprice() {
        return kcprice;
    }

    public void setKcprice(Double kcprice) {
        this.kcprice = kcprice;
    }

    public Integer getStudynumber() {
        return studynumber;
    }

    public void setStudynumber(Integer studynumber) {
        this.studynumber = studynumber;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Integer getBzxhks() {
        return bzxhks;
    }

    public void setBzxhks(Integer bzxhks) {
        this.bzxhks = bzxhks;
    }

    public Integer getKctypeid() {
        return kctypeid;
    }

    public void setKctypeid(Integer kctypeid) {
        this.kctypeid = kctypeid;
    }
}