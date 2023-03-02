package escape.rule;

import escape.required.Rule;

public class RuleImpl implements Rule{
    RuleID ruleID;
    int value = 0;

    /**
     * Default contstructor
     * @param ruleID
     * @param value
     */
    public RuleImpl(RuleID ruleID, int value) {
        this.ruleID = ruleID;
        this.value = value;
    }
    
    @Override
    public RuleID getId() {
        return ruleID;
    }
    
    @Override
    public int getIntValue() {
        return value;
    }
}
