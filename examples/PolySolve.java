        interface Id {
          // ∀ a. a -> a
          <A> A f(A a); }

        interface Foo<A, B> {
          // ∀ x. ((a -> x), (b -> x)) -> x
          // ∀ x. (a + b) -> x -> x
          // a + b 
          <X> X f(F<A, X> fa, F<B, X> fb); }

          interface Bar {
            // f :: ∀ a. (a, Either (a, Bool) (b, Bool)) -> (a, a)
            // f a1 Left (a2, b) -> (_, _)
            // f a1 Right (_, b) -> (a1, a1)
            // 16 * 1 = 16.
            <A> P2<A, A> f(A a, Either<P2<A, Boolean>, P2<B, Boolean>> e); }