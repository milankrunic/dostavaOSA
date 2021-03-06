package ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.analysers;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;

import ftn.dostavaOSA2021.DostavaOSA2021.lucene.indexing.filters.CyrilicToLatinFilter;

public class SerbianAnalyzer extends Analyzer {

    /**
     * An array containing some common English words
     * that are usually not useful for searching.
     */
    public static final String[] STOP_WORDS =
    {
        "i","a","ili","ali","pa","te","da","u","po","na"
    };

    /**
     * Builds an analyzer.
     */
    public SerbianAnalyzer()
    {
    }


	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		Tokenizer source = new StandardTokenizer();
		TokenStream result = new CyrilicToLatinFilter(source);
	    result = new LowerCaseFilter(result);
	    result = new StopFilter(result,StopFilter.makeStopSet(STOP_WORDS));
	    result = new ASCIIFoldingFilter(result);  //za srpska slova osim za dj njega vidi kao d
		return new TokenStreamComponents(source, result);
//		{
//		      @Override
//		      protected void setReader(final Reader reader) {
//		        super.setReader(reader);
//		      }
//		   };
	}

}
