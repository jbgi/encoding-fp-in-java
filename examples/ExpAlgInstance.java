class EvalExp implements ExpAlg<Integer> {
    @Override
    public final Integer Lit(int lit) {
        return lit;
    }

    @Override
    public final Integer Add(Integer e1, Integer e2) {
        return e1 + e2;
    }
}

class EvalExpMul extends EvalExp implements ExpMulAlg<Integer> {
    @Override
    public final Integer Mul(Integer e1, Integer e2) {
        return e1 * e2;
    }
}