package org.example.FA.impl;

import org.example.FA.IFAConverter;
import org.example.FA.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FAConverter implements IFAConverter {

    public static void convertNDFAtoDFA(List<State> states) {
        System.out.println("Converting NDFA to DFA...");

        // Initial set containing just the start state
        Set<Set<State>> dfaStates = new HashSet<>();
        Map<Set<State>, Map<Character, Set<State>>> dfaTransitions = new HashMap<>();
        Queue<Set<State>> queue = new LinkedList<>();

        Set<State> startState = new HashSet<>();
        startState.add(states.get(0)); // Assuming q0 as start state
        // Optionally, additional start states could be added here

        queue.add(startState);
        dfaStates.add(startState);

        while (!queue.isEmpty()) {
            Set<State> currentState = queue.poll();

            for (char symbol : new char[]{'a', 'b', 'c'}) { // Assuming 'a', 'b', 'c' as the alphabet
                Set<State> newState = new HashSet<>();

                for (State state : currentState) {
                    if (state.transitions.containsKey(symbol)) {
                        newState.addAll(state.transitions.get(symbol));
                    }
                }

                if (!newState.isEmpty() && dfaStates.add(newState)) {
                    queue.add(newState);
                }

                dfaTransitions.computeIfAbsent(currentState, k -> new HashMap<>()).put(symbol, newState);
            }
        }

        for (Map.Entry<Set<State>, Map<Character, Set<State>>> entry : dfaTransitions.entrySet()) {
            for (Map.Entry<Character, Set<State>> trans : entry.getValue().entrySet()) {
                System.out.println(entry.getKey() + " --" + trans.getKey() + "--> " + trans.getValue());
            }
        }
    }

    public static void convertToRegularGrammar(List<State> states) {
        System.out.println("Regular Grammar Productions:");
        for (State state : states) {
            for (Map.Entry<Character, Set<State>> entry : state.transitions.entrySet()) {
                char symbol = entry.getKey();
                for (State nextState : entry.getValue()) {
                    System.out.println(state.name + " -> " + symbol + nextState.name);
                }
            }
        }
        // Example lacks explicit final state handling, so adding manually
        System.out.println("q1 -> λ"); // Assuming q1 is a final state for demonstration
    }

    public static void determineFA(List<State> states) {
        boolean isDFA = true;
        for (State state : states) {
            for (Map.Entry<Character, Set<State>> transition : state.transitions.entrySet()) {
                if (transition.getValue().size() > 1) {
                    isDFA = false;
                    break;
                }
            }
            if (!isDFA) {
                break;
            }
        }
        System.out.println("The FA is " + (isDFA ? "DFA" : "NDFA"));
    }
}
