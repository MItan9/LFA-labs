package org.example.FA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
    public String name;
    public Map<Character, Set<State>> transitions = new HashMap<>();

    public State(String name) {
        this.name = name;
    }

    public void addTransition(char symbol, State state) {
        transitions.computeIfAbsent(symbol, k -> new HashSet<>()).add(state);
    }

    @Override
    public String toString() {
        return name;
    }
}
