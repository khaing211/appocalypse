package com.github.appocalypse.sudokusolver.report;

public class OnlineSysoutBoardReporter implements BoardReporter {
    @Override
    public void onUpdate(BoardEvent event) {
        System.out.println(event);
    }
}
