@Data(flavour = Flavour.FJ)
interface Stream<A> {

  <X> X match(
    @FieldNames({ "head", "tail" }) F2<A, Stream<A>, X> cons);

  default A get(int i) {
    Stream<A> s = this;
    while (0 < i--) {
      s = getTail(s);
    }
    return getHead(s);
  }

  default lazyTail() {
    return lazy(() -> getTail(this))
  }

  default <B, C> zipWith(F2<A, B, C> f, Stream<B> bs) {
    return zipWith(f).f(this, bs);
  }

  static Stream<Long> fibs = fix(fibs ->
    cons(0L, cons(1L, fibs.zipWith((i1, i2) -> i1 + i2, fibs.lazyTail()))),
    Streams::lazy);

  static <A, B, C> F2<Stream<A>, Stream<B>, Stream<C>> 
    zipWith(F2<A, B, C> f) {
      return rec2(rec -> (s1, s2) -> s1.match(
          (a, as) -> s2.match(
              (b, bs) -> cons(f.f(a, b), rec.f(as, bs)))),
          Streams::lazy);
  }

  static <A> A fix(F<A, A> f, F<F0<A>, A> delay) {
    return new Object() {
      final A fix = delay.f(() -> f.f(this.fix));
    }.fix;
  }
  
  static <A, B> F<A, B> rec(F<F<A, B>, F<A, B>> f,
    F<F0<B>, B> delay) {
      return new F<A, B>() {
        final F<A, B> fix = f.f(this);
        public B f(A a) {
          return delay.f(() -> fix.f(a));
        }
      };
  }

  static <A, B, C> F2<A, B, C> rec2(F<F2<A, B, C>, F2<A, B, C>> f,
    F<F0<C>, C> delay) {
      return new F2<A, B, C>() {
        final F2<A, B, C> fix = f.f(this);
        public C f(A a, B b) {
          return delay.f(() -> fix.f(a, b));
        }
      };
  }
}