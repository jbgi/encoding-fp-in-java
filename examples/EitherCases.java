interface Either<A, B> {

    interface Cases<A, B, X> {
        X Left(A leftValue);
        X Right(B rightValue);
    }
    <X> X match(Cases<A, B, X> cases);

    static <A, B> Either<A, B> Left(A a) {
        return new Either<A, B> {
            <X> X match(Cases<A, B, X> cases) {
                return cases.Left(a);
            }
        }
    }

    static <A, B> Either<A, B> Right(B b) {
        return new Either<A, B> {
            <X> X match(Cases<A, B, X> cases) {
                return cases.Right(b);
            }
        }
    }
}