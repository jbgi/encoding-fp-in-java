@Data(flavour=Flavour.FJ)
@Derive(@Instances({ Equal.class, Ord.class, Hash.class, Show.class}))
interface Either<A, B> {
    <X> X match(F<A, X> Left, F<B, X> Right);

    public static void main() {
        Either<Integer, Boolean> l = Eithers.Left(1);
        Either<Integer, Boolean> r = Eithers.Right(true);
    
        Function<Either<Integer, Boolean>, String> toString =
            Eithers.<Integer, String>cases()
                .Left(i -> String.valueOf(i))
                .Right(b -> b ? "true" : "false");
    
        int i = caseOf(l)
                    .Left(identity())
                    .otherwise_(0);
    }

    static <A, B, C> F<Either<A, B>, Either<A, C>> map(F<B, C> f) {
        return Eithers.modRight(f)
    }
}