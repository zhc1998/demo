package com.jk.model;



import java.io.Serializable;
import java.util.Date;




public class Orderone implements Serializable {
    private Integer id;//主键id

    private String ordernumber;//订单编号

    private String consignee;//收货人

    private String tel;//电话

    private String address;//收货地址

    private Double totalmoney;//商品金额

    private Double amountpayable;//应付金额

    private Integer state;//订单状态

    private String buyer;

    private String ordertime;//下单时间

    private Integer amount;//购买数量

    private String artno;//商品编号

    private String commodityname; //商品名称

    private Integer commodityprice; //应付金额

    private String paydate;//付款时间

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber == null ? null : ordernumber.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Double getAmountpayable() {
        return amountpayable;
    }

    public void setAmountpayable(Double amountpayable) {
        this.amountpayable = amountpayable;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getArtno() {
        return artno;
    }

    public void setArtno(String artno) {
        this.artno = artno;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public Integer getCommodityprice() {
        return commodityprice;
    }

    public void setCommodityprice(Integer commodityprice) {
        this.commodityprice = commodityprice;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}