package com.leovegasgroup.wallet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * A Account.
 */
@Entity
@Table(name = "T_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "balance", precision = 21, scale = 2)
    private BigDecimal balance;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

    @OneToOne
    @JoinColumn(name = "player_id",unique = true)
    private Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account id(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public ZonedDateTime getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Account lastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Account player(Player player) {
        this.setPlayer(player);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        return id != null && id.equals(((Account) o).id);
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + getId() +
                ", balance=" + getBalance() +
                ", lastModified='" + getLastModified() + "'" +
                "}";
    }
}
