package com.crio.jukebox.commands;

import java.io.FileNotFoundException;
import java.util.List;

public interface ICommand {
    public void execute(List<String> tokens) throws FileNotFoundException;
}
