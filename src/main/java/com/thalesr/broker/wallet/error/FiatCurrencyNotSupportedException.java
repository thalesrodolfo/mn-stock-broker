package com.thalesr.broker.wallet.error;

public class FiatCurrencyNotSupportedException extends RuntimeException {

  public FiatCurrencyNotSupportedException(String message) {
    super(message);
  }

}
