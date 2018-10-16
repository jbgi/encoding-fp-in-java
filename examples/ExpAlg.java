interface ExpAlg<E> {
    E Lit(int lit);
    E Add(E e1, E e2);
}

static <E> E twoPlus3(ExpAlg<E> f) {
    return f.Add(f.Lit(2), f.Lit(3));
}

interface ExpMulAlg<E> extends ExpAlg<E> {
    E Mul(E e1, E e2);
}

static <E> E twoPlus3times5(ExpMulAlg<E> f) {
    return f.Add(f.Lit(2), f.Mul(f.Lit(3), f.Lit(5)));
}

interface ExpMul {
    <E> E withInterpreter(ExpMulAlg<E> f);
}
