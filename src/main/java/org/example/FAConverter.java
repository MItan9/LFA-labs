package org.example;

import java.util.*;

public class FAConverter {
    public static class State {
        String name;
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

    private static void convertNDFAtoDFA(List<State> states) {
        System.out.println("Converting NDFA to DFA...");

        // Initial set containing just the start state
        Set<Set<State>> dfaStates = new HashSet<>();
        Map<Set<State>, Map<Character, Set<State>>> dfaTransitions = new HashMap<>();
        Queue<Set<State>> queue = new LinkedList<>();

        // Инициализация начального состояния DFA
        Set<State> startState = new HashSet<>();
        startState.add(states.get(0)); // q0
        startState.add(states.get(2)); // q2

        queue.add(startState);
        dfaStates.add(startState);// q2 добавляется как начальное состояние



        // Основной цикл обработки состояний NDFA для преобразования в DFA
        while (!queue.isEmpty()) {
            Set<State> currentState = queue.poll();

            for (char symbol : new char[]{'a', 'b', 'c'}) {
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

        // Вывод переходов DFA
        for (Map.Entry<Set<State>, Map<Character, Set<State>>> entry : dfaTransitions.entrySet()) {
            for (Map.Entry<Character, Set<State>> trans : entry.getValue().entrySet()) {
                System.out.println(entry.getKey() + " --" + trans.getKey() + "--> " + trans.getValue());
            }
        }
    }


    public static void main(String[] args) {
        // Define the states
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");

        // Define transitions
        q0.addTransition('a', q0);
        q0.addTransition('b', q1);
        q1.addTransition('c', q1);
        q1.addTransition('c', q2);
        q1.addTransition('a', q1);
        q2.addTransition('a', q0);


        // List of states for easier iteration
        List<State> states = Arrays.asList(q0, q1, q2);



        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Convert FA to Regular Grammar");
            System.out.println("2. Determine if the FA is DFA or NDFA");
            System.out.println("3. Convert NDFA to DFA");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    convertToRegularGrammar(states);
                    break;
                case 2:
                    determineFA(states);
                    break;
                case 3:
                    convertNDFAtoDFA(states);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 4.");
            }
        }
    }

    private static void convertToRegularGrammar(List<State> states) {
        System.out.println("Regular Grammar Productions:");
        for (State state : states) {
            for (Map.Entry<Character, Set<State>> entry : state.transitions.entrySet()) {
                char symbol = entry.getKey();
                for (State nextState : entry.getValue()) {
                    System.out.println(state.name + " -> " + symbol + nextState.name);
                }
            }
        }
        // Adding final state productions manually as example lacks explicit final state handling
        System.out.println("q1 -> c"); // Assuming q2 is final state and reached via q1 with 'c'
    }

    private static void determineFA(List<State> states) {
        boolean isDFA = true;
        for (State state : states) {
            for (char symbol : new char[]{'a', 'b', 'c'}) {
                if (state.transitions.getOrDefault(symbol, Collections.emptySet()).size() > 1) {
                    isDFA = false;
                    break;
                }
            }
            if (!isDFA) break;
        }
        System.out.println("The FA is " + (isDFA ? "DFA" : "NDFA"));
    }
}

