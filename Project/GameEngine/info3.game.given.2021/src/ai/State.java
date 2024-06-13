package ai;

public class State {

    String state;

    public State(String state) {
        this.state = state;
    }

    public boolean equals(State s) {
        return state.toUpperCase().equals(s.state.toUpperCase());
    }

}
