package com.thalesr.broker.watchlist;

import com.thalesr.broker.data.InMemoryAccountStore;
import static com.thalesr.broker.data.InMemoryAccountStore.ACCOUNT_ID;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;

@Controller("/account/watchlist")
public record WatchListController(InMemoryAccountStore store) { // <- same as a normal class with constructor passing the parameter

  @Get(produces = MediaType.APPLICATION_JSON)
  public WatchList get() { return store.getWatchList(store.ACCOUNT_ID); }

  @Put(
    consumes = MediaType.APPLICATION_JSON,
    produces = MediaType.APPLICATION_JSON
  )
  public WatchList update(@Body WatchList watchList) {
    return store.updateWatchList(ACCOUNT_ID, watchList);
  }

  @Delete
  public void delete() {
    store.deleteWatchList(ACCOUNT_ID);
  }
}
