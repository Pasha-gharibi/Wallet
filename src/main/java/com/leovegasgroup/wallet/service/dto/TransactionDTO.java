package com.leovegasgroup.wallet.service.dto;

import com.leovegasgroup.wallet.domain.enumeration.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.leovegasgroup.wallet.domain.Transaction} entity.
 */
public class TransactionDTO implements Serializable {

    private Long id;

    private BigDecimal amount;

    private TransactionType type;

    private Long playerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public TransactionDTO id(Long id) {
        this.id = id;
        return this;
    }

    public TransactionDTO amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionDTO type(TransactionType type) {
        this.type = type;
        return this;
    }

    public TransactionDTO player(Long playerId) {
        this.setPlayerId(playerId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDTO)) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", type='" + getType() + "'" +
                ", playerId=" + getPlayerId() +
                "}";
    }
}
