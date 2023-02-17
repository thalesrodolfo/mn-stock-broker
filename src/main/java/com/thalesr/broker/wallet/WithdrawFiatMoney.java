package com.thalesr.broker.wallet;

import java.math.BigDecimal;
import java.util.UUID;

import com.thalesr.broker.Symbol;

public record WithdrawFiatMoney(
  UUID accountId,
  UUID walletId,
  Symbol symbol,
  BigDecimal amount
) {
  
}
