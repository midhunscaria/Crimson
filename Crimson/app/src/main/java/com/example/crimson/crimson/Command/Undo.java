package com.example.crimson.crimson.Command;

import com.example.crimson.crimson.Memento.Caretaker;

public class Undo
{
    public void undoOperation(Caretaker caretaker)
    {
        caretaker.undoOperation();
    }
}
