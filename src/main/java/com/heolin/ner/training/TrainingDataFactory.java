package com.heolin.ner.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.util.CoreMap;

public class TrainingDataFactory {

	private static TrainingDataFactory instance;
	
	private static PTBTokenizerAnnotator tokenizerAnntator;
	private static WordsToSentencesAnnotator sentencesAnnotator;

	public TrainingDataFactory()
	{
		tokenizerAnntator = new PTBTokenizerAnnotator();
		sentencesAnnotator = new WordsToSentencesAnnotator();
	}

	public static TrainingDataFactory get()
	{
		if (instance == null)
			instance = new TrainingDataFactory();
		return instance;
	}


	public String getTrainingData(String inputSentence)
	{
	    Annotation document = new Annotation(inputSentence);

		tokenizerAnntator.annotate(document);
		sentencesAnnotator.annotate(document);

		String result = "";

	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		
	    for(CoreMap sentence: sentences) {
    		String ner = "O";
	    	for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
	    		String word = token.get(TextAnnotation.class);
	    		if (word.contains("/")) {
	    			ner = "O";
	    		} else if (word.contains("<")) {
	    			ner = word.substring(1, word.length()-1);
	    		} else {
		    		result += word +"\t" + ner +"\n";
	    		}
	    	}
	    }

	    return result;
	}

}
