package com.thalesr.broker.wallet.error;

import static com.thalesr.broker.wallet.WalletController.SUPPORTED_FIAT_CURRENCIES;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {FiatCurrencyNotSupportedException.class, ExceptionHandler.class})
public class FiatCurrencyNotSupportedExceptionHandler implements ExceptionHandler<FiatCurrencyNotSupportedException, HttpResponse<CustomError>> {

  @Override
  public HttpResponse<CustomError> handle(HttpRequest request, FiatCurrencyNotSupportedException exception) {
    return HttpResponse.badRequest(
      new CustomError(
        HttpStatus.BAD_REQUEST.getCode(), 
        "UNSUPPORTED_FIAT_CURRENCY",
        String.format("Only %s are supported", SUPPORTED_FIAT_CURRENCIES)
      )
    );
  }
  
}
