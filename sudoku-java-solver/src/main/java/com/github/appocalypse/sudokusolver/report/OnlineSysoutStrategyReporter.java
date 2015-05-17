package com.github.appocalypse.sudokusolver.report;

public class OnlineSysoutStrategyReporter implements StrategyReporter {
    @Override
    public void onUpdate(StrategyEvent event) {
        System.out.println(event);
    }
}
