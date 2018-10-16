private static final class Lazy<A> implements List<A> {
    private volatile F0<List<A>> expression;
    private List<A> evaluation;

    Lazy(F0<List<A>> list) {
      this.expression = list;
    }

    private synchronized List<A> _evaluate() {
      Lazy<A> lazy = this;
      while (true) {
        F0<List<A>> expr = lazy.expression;
        if (expr == null) {
          evaluation = lazy.evaluation;
          break;
        }
        else {
          List<A> eval = expr.f();
          if (eval instanceof Lazy)
            lazy = (Lazy<A>) eval;
          else {
            evaluation = eval;
            break;
          }
        }
      }
      expression = null;
      return evaluation;
    }

    public <X> X match(F0<X> Nil, F2<A, List<A>, X> Cons) {
      return (this.expression == null ? this.evaluation : _evaluate()).match(Nil, Cons);
    }
  }