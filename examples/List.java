public interface List<A> {

  <X> X match(F0<X> Nil,
    @FieldNames({ "head", "tail" }) F2<A, List<A>, X> Cons);
  
  public static <A> F<List<A>, List<A>> filter(F<A, Boolean> p) {
    return Lists.cata(
      Lists::Nil,
      (a, as) -> p.f(a) ? Lists.Cons(a, as) : as, Lists::lazy);
  }
  
  public static <A, B> F<List<A>, List<B>> bind(F<A, List<B>> f) {
    return Lists.cata(
      Lists::Nil,
      (a, as) -> append(as).f(f.f(a)), Lists::lazy);
  }
  
  public static <A> F<List<A>, List<A>> append(List<A> list) {
    return Lists.cata(() -> list, Lists::Cons, Lists::lazy);
  }
  
  public static <A, X> F<List<A>, X> cata(F0<X> Nil, F2<A, X, X> Cons,
    F<F0<X>, X> delay) {
      return new F<List<A>, X>() {
        public X f(List<A> list) {
          return delay.f(() -> list.match(
            Nil, 
            (head, tail) -> Cons.f(head, f(tail))));
        }
      };
    }
  }