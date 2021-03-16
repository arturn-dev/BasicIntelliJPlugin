package pl.zzpj;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final int HISTORY_CAPACITY = 20;
    public enum Operation {
        OP_NONE,
        OP_ADD,
        OP_SUB,
        OP_MUL,
        OP_DIV,
        OP_EQU,
        OP_PER,
        OP_NEG
    }
    private List<Float> history;
    private Float accumulator;
    private Float register;
    private Operation currentOp;

    public Calculator() {
        history = new ArrayList<>();
        reset();
    }

    public void reset() {
        accumulator = 0f;
        currentOp = Operation.OP_ADD;
    }


    public void performOperation(Operation operation) {
        if (operation == Operation.OP_NEG) {
            accumulator *= -1f;
            currentOp = Operation.OP_NONE;
            return;
        } else if (operation == Operation.OP_PER) {
            accumulator /= 100f;
            currentOp = Operation.OP_NONE;
            return;
        }

        switch (currentOp) {
            case OP_ADD: {
                accumulator += register;
            }
            break;
            case OP_SUB: {
                accumulator -= register;
            }
            break;
            case OP_MUL: {
                accumulator *= register;
            }
            break;
            case OP_DIV: {
                accumulator /= register;
            }
            break;
        }
        if (operation == Operation.OP_EQU) {
            addToHistory(accumulator);
        }
        currentOp = operation;
    }

    private void addToHistory(float value) {
        history.add(0, value);

        if (history.size() > HISTORY_CAPACITY) {
            history.remove(history.size() - 1);
        }
    }

    public Operation getCurrentOp() {
        return currentOp;
    }

    public Float getAccumulator() {
        return accumulator;
    }

    public Float getRegister() {
        return register;
    }

    public void setRegister(Float register) {
        this.register = register;
    }

    public List<Float> getHistory() {
        return history;
    }

    public void clearHistory() {
        history.clear();
    }
}
