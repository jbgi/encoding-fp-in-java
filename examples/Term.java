abstract class TypeEq<A, B> {
  private TypeEq() {}

  abstract A coerce(B b);

  static <A> TypeEq<A, A> refl() {
        return new TypeEq<A, A>() {
            A coerce(A a) {
                return a;
            }
        };
    }
}

@Data
interface Term<A> {
  interface Cases<A, X> {
    X Lit(int lit, TypeEq<Integer, A> id);

    X Add(Term<Integer> e1, Term<Integer> e2, TypeEq<Integer, A> id);

    X IsZero(Term<Integer> e, TypeEq<Boolean, A> id);

    X If(Term<Boolean> cond, Term<T> then, Term<T> otherwise);
  }

  <X> X match(Cases<A, X> cases);

  public static <T> T eval(final Term<T> term) {
    return Terms.caseOf(term)
        .Lit((i, id) -> id.coerce(i))
        .Add((e1, e2, id) -> id.coerce(eval(e1) + eval(e1)))
        .IsZero((e, id) -> id.coerce(eval(t) == 0))
        .If((cond, then, otherwise) -> eval(cond) ? eval(then) : eval(otherwise));
  }

  static Term<Integer> Lit(int i) {
    return new Term<Integer>() {
        public <X> X match(Cases<Integer, X> cases) {
            return cases.Lit(i, TypeEq.refl());
        }
    };
  }
}