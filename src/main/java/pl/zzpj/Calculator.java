package pl.zzpj;

public class Calculator {
    public enum Operation {
        OP_NONE,
        OP_ADD,
        OP_SUB,
        OP_MUL,
        OP_DIV,
        OP_EQU
    }

    private Float accumulator;
    private Float register;
    private Operation currentOp;

    public Calculator() {
        reset();
    }

    public void reset() {
        accumulator = 0f;
        currentOp = Operation.OP_NONE;
    }


    public void setOperation(Operation operation) {
        switch (currentOp) {
            case OP_EQU:
            case OP_NONE: {
                accumulator = register;
                currentOp = operation;
            }
                break;
            case OP_ADD: {
                accumulator += register;
                currentOp = operation;
            }
                break;
            case OP_SUB: {
                accumulator -= register;
                currentOp = operation;
            }
                break;
            case OP_MUL: {
                accumulator *= register;
                currentOp = operation;
            }
                break;
            case OP_DIV: {
                accumulator /= register;
                currentOp = operation;
            }
                break;
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
}
