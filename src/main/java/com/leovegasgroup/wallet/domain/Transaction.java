package com.leovegasgroup.wallet.domain;

import com.leovegasgroup.wallet.domain.enumeration.TransactionType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * A Transaction.
 */
@Entity
@Table(name = "t_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", insertable = true, updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "balance", precision = 21, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction id(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Transaction balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public TransactionType getType() {
        return this.type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Transaction type(TransactionType type) {
        this.type = type;
        return this;
    }

    public ZonedDateTime getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Transaction lastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Transaction player(Player player) {
        this.setPlayer(player);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", balance=" + getBalance() +
                ", type='" + getType() + "'" +
                "}";
    }

}
