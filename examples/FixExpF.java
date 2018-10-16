@Data
interface Exp {
    <X> X match(ExpF.Cases<Exp, X> cases);
}

@Data
interface ExpMul {
    <X> X match(ExpMulF.Cases<ExpMulF, X> cases);

    static F<Exp, ExpMul> fromExp() {
        
        ExpMulF.Cases<ExpMul, ExpMul> factory = ExpMuls.factory();

        F<F0<ExpMul>, ExpMul> delay = ExpMuls::lazy;

        return Exps.cata(factory, delay);
    }
}

static <S> Exp ana(F<S, ExpF<S>> coAlg, S seed) { 
    // left as an excercise to the reader ;-)
}