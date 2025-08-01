package com.spring.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Accounts {

    @Column(name = "customer_id")
    private long customerId;

    @Id
    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private Date createDt;

}

