        interface Id {
          // ∀ a. a -> a
          <A> A f(A a); }

        interface Foo<A, B> {
          // ∀ x. ((a -> x), (b -> x)) -> x
          <X> X f(F<A, X> fa, F<B, X> fb); }

        interface Bar {
          // ∀ a. (a, Either (a, Bool) (b, Bool)) -> (a, a)
          <A> P2<A, A> f(A a, Either<P2<A, Boolean>, P2<B, Boolean>> e); }