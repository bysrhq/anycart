/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author bysrhq
 */
@Entity
@Table
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    private String id;
    private boolean status;
    private double total;
    private Date date;
    private String customer;
    @OneToMany
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "transaction_id")
    private List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "sales_username")
    private User sales;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "cashier_username")
    private User cashier;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public User getSales() {
        return sales;
    }

    public void setSales(User sales) {
        this.sales = sales;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }
    
}
