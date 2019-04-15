package com.example.crimson.crimson.Command;

import com.example.crimson.crimson.Memento.Caretaker;

public class UndoCommand implements Command
{
    public Undo undo;
    public Caretaker caretaker;

    public UndoCommand(Caretaker caretaker, Undo undo)
    {
        this.caretaker = caretaker;
        this.undo = undo;
    }

    @Override
    public void execute() {
        undo.undoOperation(caretaker);
    }
}
