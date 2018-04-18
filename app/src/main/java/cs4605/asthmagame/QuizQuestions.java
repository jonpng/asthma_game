package cs4605.asthmagame;

public class QuizQuestions {

    private String _category;
    private String _question;
    private String _answer;
    private String _option2;
    private String _option3;
    private String _explanation;
    private String _answered_correctly;

    public QuizQuestions(String category, String _question, String _answer, String _option2,
                         String _option3, String _explanation, String _answered_correctly) {
        this._category = category;
        this._question = _question;
        this._answer = _answer;
        this._option2 = _option2;
        this._option3 = _option3;
        this._explanation = _explanation;
        this._answered_correctly = _answered_correctly;

    }

    public String get_category() {
        return _category;
    }

    public String get_question() {
        return _question;
    }

    public String get_answer() {
        return _answer;
    }

    public String get_option2() {
        return _option2;
    }

    public String get_option3() {
        return _option3;
    }

    public String get_explanation() {
        return _explanation;
    }

    public String get_answered_correctly() {
        return _answered_correctly;
    }

}
