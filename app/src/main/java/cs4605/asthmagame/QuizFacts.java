package cs4605.asthmagame;

public class QuizFacts {

    private String _category;
    private String _fact;

    public QuizFacts (String category, String fact) {
        this._category = category;
        this._fact = fact;
    }

    public String get_category() {
        return _category;
    }

    public String get_fact() {
        return _fact;
    }

}
