package com.thalesr.broker.data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.thalesr.broker.Symbol;
import com.thalesr.broker.wallet.DepositFiatMoney;
import com.thalesr.broker.wallet.Wallet;
import com.thalesr.broker.wallet.WithdrawFiatMoney;
import com.thalesr.broker.watchlist.WatchList;
import jakarta.inject.Singleton;

@Singleton
public class InMemoryAccountStore {

  public static final UUID ACCOUNT_ID = UUID.fromString("f4245629-83df-4ed8-90d9-7401045b5921");
  
  private final HashMap<UUID, WatchList> watchListsPerAccount = new HashMap<>();
  private final HashMap<UUID, Map<UUID, Wallet>> walletsPerAccount = new HashMap<>();

  public WatchList getWatchList(final UUID accountId) {
    return watchListsPerAccount.getOrDefault(accountId, new WatchList());
  }

  public WatchList updateWatchList(final UUID accountId, final WatchList watchList) {
    watchListsPerAccount.put(accountId, watchList);
    return getWatchList(accountId);
  }

  public void deleteWatchList(final UUID accountId) {
    watchListsPerAccount.remove(accountId);
  }

  public Collection<Wallet> getWallets(UUID accountId) {
    return Optional.ofNullable(walletsPerAccount.get(accountId))
    .orElse(new HashMap<>())
    .values();
  }

  public Wallet depositToWallet(DepositFiatMoney deposit) {
    return addAvailableToWallet(deposit.accountId(), deposit.walletId(), deposit.symbol(), deposit.amount());
  }

  public Wallet withdrawFromWallet(WithdrawFiatMoney withdraw) {
    return addAvailableToWallet(withdraw.accountId(), withdraw.walletId(), withdraw.symbol(), withdraw.amount());
  }

  private Wallet addAvailableToWallet(UUID accountId, UUID walletId, Symbol symbol, BigDecimal changeAmount) {
    final var wallets = Optional.ofNullable(
      walletsPerAccount.get(accountId)
    ).orElse(
      new HashMap<>()
    );

    var oldWallet = Optional.ofNullable(
      wallets.get(walletId)
    ).orElse(
      new Wallet(ACCOUNT_ID, walletId, symbol, BigDecimal.ZERO, BigDecimal.ZERO)
    );

    var newWallet = oldWallet.addAvailable(changeAmount);

    // Update wallet in store
    wallets.put(newWallet.walletId(), newWallet);
    walletsPerAccount.put(newWallet.accountId(), wallets);

    return newWallet;
  }

}
