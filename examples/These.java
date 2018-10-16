interface These<A, B> {

    interface Cases<A, B, X> extends Either.Cases<A, B, X> {
      X Both(A leftValue, B rightValue);
    }
  
    <X> X match(Cases<A, B, X> cases);
  
    static <A, B> These<A, B> ofEither(Either<A, B> e) {
      return e::match;
    }
}