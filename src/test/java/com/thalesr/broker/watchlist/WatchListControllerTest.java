package com.thalesr.broker.watchlist;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesr.broker.data.InMemoryAccountStore;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class WatchListControllerTest {
  private static final Logger LOG = LoggerFactory.getLogger(WatchListControllerTest.class);
  private static final UUID TEST_ACCOUNT_ID = InMemoryAccountStore.ACCOUNT_ID;

  @Inject
  @Client("/account/watchlist")
  private HttpClient client;

  @Inject
  InMemoryAccountStore inMemoryAccountStore;

  @BeforeEach
  void setup() {
    inMemoryAccountStore.deleteWatchList(TEST_ACCOUNT_ID);
  }

  @Test
  public void checkUpdateWatchList() {
    final WatchList result = client.toBlocking().retrieve("/", WatchList.class);
    assertTrue(result.getSymbols().isEmpty());
    assertTrue(inMemoryAccountStore.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
  }



}
