interface Algebra<R, X> extends Pure<R, X> {
  X Read(Long fromSeq, Fold<Event, R> streamFold);

  X Write(Long expectedSeq, List<Event> events,
          TypeEq<WriteResult>, R> resultType);

  <Q> X Bind(StreamAction<Q> q,
             F<Q, StreamAction<R>> f);
}
public interface StreamAction<R> {
  <X> X eval(Algebra<R, X> interpreter);
}
