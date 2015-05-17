package com.github.appocalypse.sudokusolver.report;

import com.github.appocalypse.sudokusolver.Cell;

public class BoardEvent {
    private final Type type;
    private final Cell oldCell;
    private final Cell newCell;

    public BoardEvent(Type type, Cell oldCell, Cell newCell) {
        this.type = type;
        this.oldCell = oldCell;
        this.newCell = newCell;
    }

    public Type getType() {
        return type;
    }

    public Cell getOldCell() {
        return oldCell;
    }

    public Cell getNewCell() {
        return newCell;
    }

    @Override
    public String toString() {
        return "BoardEvent{" +
                "type=" + type +
                ", oldCell=" + oldCell +
                ", newCell=" + newCell +
                '}';
    }

    public enum Type {
        FILL_EVENT,
        UPDATE_CANDIDATES_EVENT,
        ;
    }
}
