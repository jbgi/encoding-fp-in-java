public interface List<A> {

  public interface FoldR<A, X> {
    X Nil();
    X Cons(A head, X tail);
  }

  <X> X fold(FoldR<A, X> foldr);

  static List<A> iterate(A seed, F<A, A> op) {
      return new List<A> {
        <X> X fold(FoldR<A, X> foldr) {
          return fold0(seed, foldR)
        }

        <X> X fold0(a A, FoldR<A, X> foldr) {
          return foldR.Cons(a, fold0(op.f(a), foldR))
        }
      }
    }
}