package com.thalesr.broker.watchlist;

import java.util.ArrayList;
import java.util.List;

import com.thalesr.broker.Symbol;

public class WatchList {

  private List<Symbol> symbols;

  public List<Symbol> getSymbols() {
    return symbols;

  }

  public void setSymbols(List<Symbol> symbols) {
    this.symbols = symbols;
  }

  public WatchList() {
    symbols = new ArrayList<>();
  }

  public WatchList(List<Symbol> symbols) {
    this.symbols = symbols;
  }
  
}
