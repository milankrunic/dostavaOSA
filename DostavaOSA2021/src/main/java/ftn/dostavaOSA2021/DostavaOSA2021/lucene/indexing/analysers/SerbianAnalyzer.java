package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers;

import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.filters.CyrilicToLatinFilter;

@SuppressWarnings("SpellCheckingInspection")
@NoArgsConstructor
public class SerbianAnalyzer extends Analyzer {

    /**
     * An array containing some common English words
     * that are usually not useful for searching.
     */
    public static final String[] STOP_WORDS =
    {
		"i", "te", "pa", "ni", "niti", "a", "ali", "nego", "no", "vec", "ili", "da", "u", "po", "na"
    };

	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		Tokenizer source = new StandardTokenizer();
		TokenStream result = new CyrilicToLatinFilter(source);
	    result = new LowerCaseFilter(result);
	    result = new StopFilter(result, StopFilter.makeStopSet(STOP_WORDS));
		return new TokenStreamComponents(source, result);
	}

}


