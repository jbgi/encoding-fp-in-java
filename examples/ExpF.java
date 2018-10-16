@Data
interface ExpF<E> {
    interface Cases<E, X> {
        X Lit(int lit);
        X Add(E e1, E e2);
    }
    <X> X match(Cases<E, X> cases);
}

interface ExpAlg<E> extends ExpF.Cases<E, E> {}

@Data
interface ExpMulF<E> {
    interface Cases<E, X> extends ExpF.Cases<E, X> {
        X Mul(E e1, E e2);
    }
    <X> X match(Cases<E, X> cases);
}

interface ExpMulAlg<E> extends ExpF.Cases<E, E> {}