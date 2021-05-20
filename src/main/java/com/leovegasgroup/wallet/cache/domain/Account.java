package com.leovegasgroup.wallet.cache.domain;

import com.leovegasgroup.wallet.domain.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Account implements Serializable {

   private static final long serialVersionUID = 1L;

//    @Column(name = "balance", precision = 21, scale = 2)
    private BigDecimal balance;

//    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

//    @OneToOne
//    @JoinColumn(unique = true)
    private Long playerId;

    public Account(BigDecimal balance, ZonedDateTime lastModified, Long playerId) {
        this.balance = balance;
        this.lastModified = lastModified;
        this.playerId = playerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
