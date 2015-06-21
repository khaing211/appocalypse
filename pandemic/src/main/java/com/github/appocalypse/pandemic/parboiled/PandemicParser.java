package com.github.appocalypse.pandemic.parboiled;

import com.github.appocalypse.pandemic.Cities;
import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree
public class PandemicParser extends BaseParser<Object> {

    public Rule Cities() {
        Rule[] citiesRule = new Rule[Cities.values().length];
        for (int i = 0; i < Cities.values().length; i++) {
            citiesRule[i] = String(Cities.values()[i].getName());
        }
        return FirstOf(citiesRule);
    }
}
