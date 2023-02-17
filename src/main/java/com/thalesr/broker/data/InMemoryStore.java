package com.thalesr.broker.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;
import com.thalesr.broker.Symbol;
import com.thalesr.broker.watchlist.WatchList;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class InMemoryStore {
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryStore.class);
  private final Map<String, Symbol> symbols = new HashMap<>();
  private final Faker faker = new Faker();

  @PostConstruct
  public void initialize() {
    initializeWith(10);
  }

  public void initializeWith(int numberOfEntries) {
    symbols.clear();
    IntStream.range(0, numberOfEntries).forEach(i ->
      addNewSymbol()
    );
  }

  private void addNewSymbol() {
    var symbol = new Symbol(faker.stock().nsdqSymbol());

    symbols.put(symbol.value(), symbol);

    LOG.info("Added Symbol {}", symbol);
  }

  public Map<String, Symbol> getSymbols() {
    return symbols;
  }

  public void add(Symbol symbol) {
    symbols.put(symbol.value(), symbol);
    LOG.info("Added Symbol {}", symbol);
  }

}
