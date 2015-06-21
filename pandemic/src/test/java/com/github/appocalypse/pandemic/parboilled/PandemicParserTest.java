package com.github.appocalypse.pandemic.parboilled;

import com.github.appocalypse.pandemic.Constant;
import com.github.appocalypse.pandemic.parboiled.PandemicParser;
import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

public class PandemicParserTest {
    @Test
    public void testParseCities() {
        PandemicParser parser = Parboiled.createParser(PandemicParser.class);

        ParsingResult<Object> result = new ReportingParseRunner<Object>(parser.Cities())
                .run(Constant.CityName.ATLANTA);

        String parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
        System.out.println(parseTreePrintOut);
    }
}