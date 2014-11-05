package com.heolin.ner;

import java.io.*;
import java.util.*;

import javax.swing.text.Segment;

import edu.stanford.nlp.ie.NERClassifierCombiner;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.optimization.QNMinimizer.eLineSearch;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

public class Annotator {

	private NERRegexAnnotator tokenRegexAnnotator;
	private WordsToSentencesAnnotator sentencesAnnotator;
	private PTBTokenizerAnnotator tokenizerAnntator;
	private POSTaggerAnnotator posTaggerAnnotator;
	private NERAnnotator nerAnnotator;
	
	public Annotator()
	{
		initialize();
	}
	
	private void initialize()
	{
		sentencesAnnotator = new WordsToSentencesAnnotator();
		tokenizerAnntator = new PTBTokenizerAnnotator();
		posTaggerAnnotator = new POSTaggerAnnotator();
		nerAnnotator = new NERAnnotator("ner-model.ser.gz");
		tokenRegexAnnotator = new NERRegexAnnotator();
	}
	
	private void annotationPipeline(Annotation document, Boolean statistical)
	{
		tokenizerAnntator.annotate(document);
		sentencesAnnotator.annotate(document);
		posTaggerAnnotator.annotate(document);
		if (statistical) {
			nerAnnotator.annotate(document);
		} else {
			tokenRegexAnnotator.annotate(document);
		}
	}

	public String annotate(String inputString) {
		return annotate(inputString, false);
	}
	
	public String annotate(String inputString, Boolean useStatistical)
	{
	    Annotation document = new Annotation(inputString);
	    
	    annotationPipeline(document, useStatistical);
	    
	    String result = "";
	    
	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
	
	    for(CoreMap sentence: sentences) {
	      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
	
	        String word = token.get(TextAnnotation.class);
	
	        String pos = token.get(PartOfSpeechAnnotation.class);

	        String ner = "O";
	        if (token.has(NamedEntityTagAnnotation.class)) {
	        	ner = token.get(NamedEntityTagAnnotation.class);
	        }
	        
	        result += word +"_"+ner +" ";
	      }
	      result += "\n";
	    }
	    
	    return result;
	}
	

}