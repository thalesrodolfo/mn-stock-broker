package com.thalesr.broker.wallet.error;

import com.thalesr.broker.wallet.api.RestApiResponse;

public record CustomError(
  int status,
  String error,
  String message
) implements RestApiResponse {

}
