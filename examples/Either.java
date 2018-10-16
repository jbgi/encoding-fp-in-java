
interface Either<A, B> {
    
    <X> X match(F<A, X> left, F<B, X> right);

    static <A, B> Either<A, B> Left(A a) {
        return new Either<A, B> {
            <X> X match(F<A, X> left, F<B, X> right) {
                return left.f(a);
            }
        }
    }

    static <A, B> Either<A, B> Right(B b) {
        return new Either<A, B> {
            <X> X match(F<A, X> left, F<B, X> right) {
                return right.f(b);
            }
        }
    }
}