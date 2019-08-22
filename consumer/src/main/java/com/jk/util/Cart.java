package com.jk.util;


import java.io.Serializable;
import java.util.List;


public class Cart implements Serializable {
      private Integer storeid;
      private String storename;
      private List<TbOrderPro> productList;


      @Override
      public String toString() {
            return "Cart{" +
                    "storeid=" + storeid +
                    ", storename='" + storename + '\'' +
                    ", productList=" + productList +
                    '}';
      }

      public Integer getStoreid() {
            return storeid;
      }

      public void setStoreid(Integer storeid) {
            this.storeid = storeid;
      }

      public String getStorename() {
            return storename;
      }

      public void setStorename(String storename) {
            this.storename = storename;
      }

      public List<TbOrderPro> getProductList() {
            return productList;
      }

      public void setProductList(List<TbOrderPro> productList) {
            this.productList = productList;
      }
}
